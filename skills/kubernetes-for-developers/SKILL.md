---
name: kubernetes-for-developers
description: Use whenever writing or reviewing Kubernetes manifests for a backend service (Deployment, Service, ConfigMap, Secret, Ingress, StatefulSet, HorizontalPodAutoscaler), setting liveness/readiness/startup probes, sizing resource requests/limits (including JVM heap vs container memory), planning a rolling update/rollback strategy, routing HTTP traffic to multiple backend services by path/host, deploying a stateful workload (database, broker) that needs per-Pod storage, setting up staging/production namespaces, configuring autoscaling, or debugging a pod that won't start, is stuck Pending, is in CrashLoopBackOff, or was OOMKilled.
---

# Kubernetes for Developers

Operational guidance for shipping a backend service to Kubernetes, distilled from
*Kubernetes for Developers* (William Denniss, Manning). Written for the developer
deploying the app, not the cluster admin. Keep it concrete: every recommendation
below maps to a YAML field or a `kubectl` command you can run right now.

## 1. Minimal Deployment + Service (annotated)

This is the smallest production-viable shape for a stateless backend (e.g., a Java
Spring Boot / Micronaut / Quarkus service listening on port 8080). Start here, then
layer on probes, resources, and config from the sections below.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-service
spec:
  replicas: 3                      # 1 replica has no redundancy during node/pod loss
  selector:
    matchLabels:
      pod: my-service-pod          # must exactly match template.metadata.labels
  strategy:
    type: RollingUpdate            # see section 4 for maxSurge/maxUnavailable
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 0            # production default: never drop below full capacity
  template:
    metadata:
      labels:
        pod: my-service-pod
    spec:
      containers:
      - name: my-service-container
        image: registry.example.com/my-service:1.4.2   # pin a tag, never :latest
        ports:
        - containerPort: 8080
        env:
        - name: JAVA_TOOL_OPTIONS
          value: "-XX:MaxRAMPercentage=75.0"   # see section 3.3 — tie heap to container memory
        readinessProbe:            # section 2 — controls traffic
          httpGet: { path: /readyz, port: 8080 }
          initialDelaySeconds: 15
          periodSeconds: 10
          timeoutSeconds: 2
          failureThreshold: 1
        livenessProbe:             # section 2 — controls restarts
          httpGet: { path: /healthz, port: 8080 }
          initialDelaySeconds: 30
          periodSeconds: 30
          timeoutSeconds: 5
          failureThreshold: 3
        resources:                 # section 3 — never ship without this
          requests:
            cpu: 250m
            memory: 512Mi
          limits:
            memory: 512Mi          # memory limit == request: avoid surprise OOMKills from bursting neighbors
            cpu: "1"                # CPU limit can exceed request: CPU is compressible, just throttles
        envFrom:
        - configMapRef:
            name: my-service-config    # section 5 — non-secret config
        volumeMounts:
        - name: secret-volume
          mountPath: /etc/secrets
          readOnly: true
      volumes:
      - name: secret-volume
        secret:
          secretName: my-service-secrets   # section 5 — secret config
---
apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  selector:
    pod: my-service-pod           # Services select Pods by label, NOT the Deployment by name
  ports:
  - port: 80
    targetPort: 8080
  type: ClusterIP                  # use LoadBalancer only for the edge-facing service
```

Key things developers get wrong here:
- The **Service's `selector` must match the Pod template's `labels`**, not the
  Deployment's name — a Service has no awareness of the Deployment object at all,
  it only ever sees Pods carrying a matching label.
- `replicas` in `kubectl get deploy` reflects Pods *scheduled* for creation, not
  Pods actually serving traffic — check `kubectl get pods` and the `READY` column.
- Never omit `resources` "to keep things simple" — an unspecified Pod gets
  scheduled almost randomly and can starve or evict neighbors (section 3).

## 2. Probes: liveness vs. readiness vs. startup

| | Liveness | Readiness | Startup |
|---|---|---|---|
| Question it answers | Is the container running/healthy? | Can this Pod receive traffic right now? | Has the (slow-booting) app finished starting? |
| Failure action | Pod is **killed and restarted** | Pod is **pulled from the Service**, kept running | Blocks liveness/readiness checks until it passes |
| Default state at boot | Passing (assumed live) | **Failing** (assumed not ready) | Failing |
| Recovery cost | Slow — full reschedule/boot | Fast — added back the moment it passes | N/A, one-shot gate |
| Should it check dependencies (DB, downstream API)? | **No** — only check the process itself | **Yes** — test the actual dependency (e.g. `SELECT 1`) | No — just "have I finished booting" |

Why this matters in practice:

- **No readiness probe -> traffic sent to a not-yet-ready pod.** Without one,
  Kubernetes assumes a container is ready the instant it starts, and a Service adds
  it to the load-balancing rotation immediately — even though a JVM app might take
  10-30+ seconds to warm up. Result: a burst of failed requests on every rollout or
  scale-up.
- **Liveness probe testing an external dependency -> restart loops that don't fix
  anything.** If your liveness check pings the database and the database is down,
  Kubernetes will kill and restart a perfectly healthy app container repeatedly.
  This creates churn and can cascade — restarting your app does nothing to fix the
  database, and you've now added reschedule load on top of an outage. Put
  dependency checks in the **readiness** probe only; the pod just gets quietly
  pulled from rotation until the dependency recovers.
- **Liveness probe too aggressive (short `failureThreshold`/`timeoutSeconds`) on a
  slow-starting app -> boot-time restart loop that looks like CrashLoopBackOff**
  even though the app was going to come up fine. For JVM apps with real startup
  cost (Spring context, connection pools), either give liveness a generous
  `initialDelaySeconds`/`failureThreshold`, or add a `startupProbe` so liveness
  and readiness don't even begin evaluating until startup completes:

```yaml
startupProbe:
  httpGet: { path: /healthz, port: 8080 }
  periodSeconds: 5
  failureThreshold: 30     # allows up to 150s to boot before liveness kicks in
```

- **Rolling updates depend entirely on a correct readiness probe.** The rollout
  controller uses readiness — not liveness, not "container running" — to decide
  when a new Pod is safe to receive traffic and safe to terminate the old one. A
  readiness probe that always returns 200 (or is missing) means "zero-downtime"
  deploys silently send requests to Pods that are still booting.
- Probe types available: `httpGet` (any HTTP service), `tcpSocket` (non-HTTP TCP
  services, e.g. SMTP — succeeds if the port opens), and `exec` (a command with a
  non-zero exit = failure; used for batch/non-network workloads).
- Keep liveness checks trivially cheap (just "is the HTTP server responding," no
  body needed) — expensive liveness logic is a self-inflicted reliability risk.

## 3. Resource requests/limits and OOMKill risk

**Requests** are what the scheduler uses to place your Pod on a node — they are a
*reservation*. **Limits** are enforced at runtime by the kubelet and are not
considered by the scheduler at all.

- Exceed your **memory limit** -> container is OOMKilled and restarted, no
  exceptions (memory is non-compressible).
- Exceed your **CPU limit** -> container is throttled, not killed (CPU is
  compressible — it just runs slower).
- Exceed your **memory request** under node-wide pressure -> your Pod is a
  candidate for eviction even if under its limit.
- No `resources` block at all -> your Pod is scheduled almost arbitrarily and
  competes for whatever is left, with no guarantee and no protection.

### 3.1 Quality-of-Service classes

| QoS class | Condition | Behavior |
|---|---|---|
| Guaranteed | `limits == requests` for CPU and memory, on every container | Most stable; never throttled/evicted ahead of others; you're only ever getting exactly what you asked for |
| Burstable | `requests` set, `limits` higher (or only one of the two set) | Can use spare node capacity but is first in line for eviction under pressure |
| BestEffort | No requests/limits set at all | First to be evicted/OOMKilled under any pressure |

### 3.2 How to size requests/limits

1. Deploy with generously overestimated requests, then load-test.
2. Run `kubectl top pod` under normal and peak load, and again after the app has
   been running for a while (memory leaks or cache growth only show up over time).
3. Set **memory request** = highest sustained usage you observed x ~1.1 buffer.
   Setting it lower risks OOMKill even when the node has spare capacity, because
   under contention you're only guaranteed the request.
4. Set **memory limit** equal to the request (Guaranteed QoS) unless you have a
   specific reason to allow bursting — with memory, an OOMKill is a hard crash, so
   unpredictable headroom is rarely worth it.
5. Set **CPU request** to the stable/steady-state usage; CPU limit can be set
   higher than the request to let the app burst into spare node capacity during
   traffic spikes, since CPU only throttles rather than crashing.
6. Give the container enough resources to even *boot* — a Pod sized only for
   steady-state can hit its memory limit during startup (class loading, connection
   pool warm-up, cache priming) and enter an OOMKill crash loop before it ever
   serves a request.

### 3.3 JVM apps specifically

The container memory limit is a hard ceiling on *everything* in the container —
heap, metaspace, thread stacks, direct/native buffers, JIT code cache, GC
bookkeeping — not just `-Xmx`. A common and costly mistake is setting
`-Xmx=512m` inside a container whose memory **limit** is also 512Mi: the JVM heap
alone can hit the ceiling before accounting for metaspace and thread stacks,
producing an OOMKill that looks like an unexplained crash rather than a sizing
problem.

Practical approach:
- Prefer `-XX:MaxRAMPercentage=<N>` (modern JVMs are cgroup-aware) over a fixed
  `-Xmx`, so heap sizing automatically scales if you change the container's
  memory limit later. Leave real headroom — `MaxRAMPercentage=70-75` for a
  typical service — for metaspace, threads, and native memory outside the heap.
- Whatever number you land on, validate it the same way as section 3.2: watch
  `kubectl top pod` under real load and after a warm-up period, not just at boot.
- Set memory `limit == request` (Guaranteed QoS) for JVM services in particular —
  a JVM that gets throttled on burstable memory has no graceful degradation path
  the way a compressible-CPU throttle does; it just OOMKills.

## 4. Config and secrets: keep them out of the image

Never bake environment-specific config or credentials into the container image —
promote the same image unchanged from staging to production and inject config at
deploy time instead.

**Non-secret config -> ConfigMap**, consumed as env vars or mounted files:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: my-service-config
data:
  LOG_LEVEL: "INFO"
  FEATURE_FLAG_X: "true"
```
```yaml
        envFrom:
        - configMapRef:
            name: my-service-config
```

**Secret values (passwords, API keys, TLS keys) -> Secret**, never a plain
ConfigMap or a plain env var value committed to git:

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: my-service-secrets
type: Opaque
stringData:
  DB_PASSWORD: "correct-horse-battery-staple"
```

Consume as a single env var:
```yaml
        env:
        - name: DB_PASSWORD
          valueFrom:
            secretKeyRef:
              name: my-service-secrets
              key: DB_PASSWORD
```

Or mount as a file when the app expects a file (e.g., a TLS private key) rather
than an env var:
```yaml
        volumeMounts:
        - name: secret-volume
          mountPath: /etc/secrets
          readOnly: true
      volumes:
      - name: secret-volume
        secret:
          secretName: my-service-secrets
```

Generate a Secret manifest from a local file without hand-writing base64:
```bash
kubectl create secret generic my-service-secrets \
  --from-file=tls.key=./tls.key --dry-run=client -o yaml > secret.yaml
```

Notes:
- `data:` values are base64 (an encoding, not encryption — don't treat it as
  security); `stringData:` accepts plain text and is preferred for readability.
- Don't commit real Secret manifests to the same git repo as the rest of your
  config-as-code — store them separately (a restricted repo, a secrets manager,
  or a sealed-secrets/external-secrets operator) so cloning the config repo
  doesn't leak credentials.
- Changing a ConfigMap/Secret does not automatically restart Pods that already
  read it into an env var at boot — force a rollout (`kubectl rollout restart
  deploy/<name>`) after updating one if your app doesn't hot-reload it.

## 5. Rolling updates and rollback

Default, safe update flow for a running Deployment:

```bash
# 1. Ship a new version — update the image tag in your manifest, then:
kubectl apply -f deploy.yaml

# 2. Watch the rollout
kubectl rollout status deployment/my-service

# 3. Force a rollout with no manifest change (e.g. to pick up a ConfigMap update)
kubectl rollout restart deployment/my-service

# 4. If something is wrong, roll back to the previous revision
kubectl rollout undo deployment/my-service

# 5. Or roll back to a specific earlier revision
kubectl rollout history deployment/my-service
kubectl rollout undo deployment/my-service --to-revision=<N>
```

Tuning the rollout via `strategy.rollingUpdate`:
- `maxSurge`: how many extra Pods above `replicas` may be created during the
  rollout — higher means faster rollout, more temporary resource usage.
- `maxUnavailable`: how many Pods may be missing from service during the
  rollout — for production, set this to **0** so capacity never dips below
  `replicas`; if you set it to 0, `maxSurge` must be at least 1 (the new Pods
  have to exist somewhere before old ones can be removed).
- The whole strategy depends on a correct readiness probe (section 2) — it's what
  the rollout controller uses to know a new Pod is safe to route traffic to and an
  old Pod is safe to remove. Without one, "zero-downtime" rollouts aren't.
- Old and new versions run simultaneously during a rolling update — your app,
  API contracts, and DB schema must tolerate both versions being live at once.
- If a rollback needs to happen fast and your rollout has many replicas, remember
  a rollback is itself a rolling update in reverse — it isn't instant.
- In a GitOps setup, prefer `git revert` on the manifest change over an ad hoc
  `kubectl rollout undo` so the cluster state and the config repo don't diverge —
  reserve `rollout undo` for an immediate incident-response fix.

## 6. Horizontal Pod autoscaling (HPA)

Horizontal scaling (more replicas) is almost always what you want for a stateless
service under load — vertical scaling (bigger Pods) doesn't parallelize a request
queue the way adding replicas does.

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: my-service
spec:
  minReplicas: 1
  maxReplicas: 10
  metrics:
  - resource:
      name: cpu
      target:
        averageUtilization: 20   # scale out once average CPU exceeds 20% of the *request*, not of a core
        type: Utilization
    type: Resource
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: my-service
```
Equivalent imperative form (prefer the YAML above for anything you keep in git):
```bash
kubectl autoscale deployment my-service --cpu-percent=20 --min=1 --max=10
```

- The percentage target is relative to the **CPU resource *request*** (section 3), not an absolute core count or the node's CPU — this is why setting `resources.requests.cpu` correctly (section 3.2) is a prerequisite for a meaningful HPA, not optional.
- HPA only adds/removes **Pods**, subject to `minReplicas`/`maxReplicas` and a cooldown to avoid thrashing — it never resizes an existing Pod's `resources` field itself (that's Vertical Pod Autoscaling, a separate, less commonly used add-on: same problem space as "should I size this container bigger," but VPA rewrites `resources` on running Pods instead of changing replica count).
- Scaling on custom/external metrics (queue depth, requests-per-second from an Ingress) instead of CPU is possible via the same `metrics` list with a different metric `type` — reach for this when CPU utilization doesn't actually track your load (e.g., an I/O-bound service).
- HPA needs somewhere to scale *to* — at the node level, pair it with cluster/node autoscaling (or you'll cap out at whatever capacity the cluster already has and new Pods sit `Pending`, see section 6 troubleshooting).

## 7. Ingress: routing HTTP by path/host

A `Service` of `type: LoadBalancer` is **layer-4** (TCP/UDP) — one external IP maps to one Service, no awareness of HTTP paths or hostnames, and you'd have to terminate TLS yourself. **Ingress** is **layer-7** (HTTP-aware): one external IP/load balancer, routing to *multiple* backend Services by URL path and/or hostname, with TLS termination handled for you.

Use Ingress instead of one `LoadBalancer` Service per microservice when: you want several services under one domain (`api.example.com/users` and `api.example.com/orders` routed to different backends), you want to add/split services later without changing public-facing URLs, or you just want to stop paying for/managing one external IP per service.

```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: my-service-ingress
spec:
  rules:
  - http:
      paths:
      - path: /                 # root path -> one backend
        pathType: Prefix
        backend:
          service:
            name: my-service-internal
            port:
              number: 80
      - path: /reports          # a different path -> a different backend/team
        pathType: Prefix
        backend:
          service:
            name: reports-service-internal
            port:
              number: 80
```
Host-based routing (multiple domains behind one Ingress) uses the same shape with a `host:` key per rule instead of/alongside `path:`.

The Services an Ingress points at are typically `type: NodePort` (or `ClusterIP` with some controllers) rather than `LoadBalancer` — the Ingress itself is what gets the external IP; the backend Services just need to be reachable from the Ingress controller, not from the internet directly.

**Don't over-apply when:** you have one single HTTP service and no plan to split it — a plain `Service` of `type: LoadBalancer` is simpler and one less object to reason about. Reach for Ingress at the point you actually have (or are about to have) more than one HTTP backend sharing a domain.

## 8. StatefulSet: Pods that need stable identity and their own disk

A `Deployment`'s Pods are interchangeable clones sharing one Pod template — including one `PersistentVolumeClaim` reference. That breaks the moment you need more than one replica each with **its own** disk (a database, a queue with per-node storage): every replica would try to mount the *same* `ReadWriteOnce` volume, and all but the first fail to schedule.

`StatefulSet` solves this: instead of one shared volume reference, it takes a `volumeClaimTemplates` block and creates a distinct `PersistentVolumeClaim` **per Pod**, plus gives each Pod a stable, ordinal identity (`my-db-0`, `my-db-1`, ...) that survives rescheduling — the replacement for `my-db-0` comes back as `my-db-0` again, reattached to the *same* PVC, not a fresh anonymous Pod.

```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mariadb
spec:
  selector:
    matchLabels:
      app: mariadb-sts
  serviceName: mariadb-service     # must point at a headless Service (clusterIP: None) — see below
  replicas: 1
  template:
    metadata:
      labels:
        app: mariadb-sts
    spec:
      terminationGracePeriodSeconds: 10   # StatefulSet requires an explicit grace period for clean shutdown
      containers:
      - name: mariadb-container
        image: mariadb:latest
        volumeMounts:
        - name: mariadb-pvc
          mountPath: /var/lib/mysql
        resources:
          requests: { cpu: "1", memory: 4Gi }
        env:
        - name: MARIADB_ROOT_PASSWORD
          value: "your database password"    # use a Secret (section 4) in anything beyond a local test
  volumeClaimTemplates:            # this is the key difference vs. Deployment: a template, not one shared PVC
  - metadata:
      name: mariadb-pvc
    spec:
      accessModes: ["ReadWriteOnce"]
      resources:
        requests:
          storage: 2Gi
---
apiVersion: v1
kind: Service
metadata:
  name: mariadb-service
spec:
  clusterIP: None                  # headless Service — required for StatefulSet's stable per-Pod DNS names
  ports:
  - port: 3306
  selector:
    app: mariadb-sts
```

- `serviceName` on the StatefulSet must reference a **headless Service** (`clusterIP: None`) — this is what gives each Pod its own resolvable DNS name (`mariadb-0.mariadb-service`), not just a load-balanced single name.
- Scaling a StatefulSet up/down is ordered (Pod `N+1` isn't created until `N` is Running/Ready; scale-down removes the highest ordinal first) — this ordering guarantee is the other half of what StatefulSet buys you beyond Deployment, useful for anything with a leader/replica or quorum relationship between instances.

**Decision: Deployment vs. StatefulSet**

| | Deployment | StatefulSet |
|---|---|---|
| Pod identity | Interchangeable, random suffix | Stable, ordinal (`-0`, `-1`, ...) |
| Storage | One shared volume reference (fine for stateless, or a single-replica stateful Pod) | One PVC per Pod via `volumeClaimTemplates` |
| Scaling order | Parallel, unordered | Sequential, ordered |
| Use for | Stateless services (the default choice) | Databases, brokers, anything where each replica needs its own disk and/or a stable per-instance identity |

**Don't over-apply when:** you're running a managed/cloud database service instead of self-hosting one in-cluster — most teams should prefer a managed DB over operating StatefulSet-based stateful storage themselves unless there's a specific reason (cost, data locality, on-prem constraint) to self-host.

## 9. Namespaces: separating environments in one cluster

A namespace is a name-scoping boundary, not a security boundary by itself: you can have a `my-service` Deployment in both `staging` and `production` namespaces without collision, and `kubectl` commands only touch the currently active namespace by default — this is what prevents "I meant to update staging and updated prod" as an easy accident.

```bash
kubectl create namespace staging
kubectl config set-context --current --namespace=staging   # switch the active context, safer than typing -n every time
kubectl get namespace                                        # list them (kube-system is Kubernetes' own, leave it alone; default is fine but prefer your own named ones)
```
Deploying into a namespace once the context is set is just the normal `kubectl apply -f .` — nothing about the manifests themselves has to change, provided they don't hardcode `metadata.namespace` (leaving it unset lets the same YAML deploy cleanly into whichever namespace is current, which is what you want for promoting the identical config from staging to production).

- Prefer switching context (`set-context --current --namespace=...`) over remembering `-n <namespace>` on every command — a forgotten `-n` silently runs against whatever namespace was last active, which is a worse failure mode than a typo'd namespace flag.
- What differs between a staging and production namespace is typically just `replicas` count and any external credentials — not the shape of the manifests.
- At larger scale (many environments, many teams), reach for a templating layer (Kustomize or Helm) on top of namespaces rather than hand-maintaining near-duplicate YAML per environment.

## 10. Troubleshooting: pod won't start / keeps restarting

Standard diagnostic toolkit, in the order to reach for them:

```bash
kubectl get pods                          # STATUS and RESTARTS columns first
kubectl get pods --selector=pod=<label>   # scope to one Deployment's pods
kubectl describe pod <pod-name>           # Events section — read this first, always
kubectl logs <pod-name>                   # current container's stdout/stderr
kubectl logs <pod-name> --previous        # logs from BEFORE the last crash/restart
kubectl logs -f deploy/<name>             # follow logs from one pod in a Deployment
kubectl exec -it <pod-name> -- bash       # get a shell inside the running container
kubectl top pod                           # live CPU/memory usage
```

### Pod stuck in `Pending`
Usually means the scheduler can't find a node that satisfies the Pod's resource
requests (or other placement constraints).
1. `kubectl describe pod <name>` -> check the Events section for `FailedScheduling`,
   e.g. `0/2 nodes are available: 2 Insufficient cpu.`
2. If resources are the issue: lower the Pod's requests, or add cluster capacity.
3. If at least one replica of the Deployment is `Running`, the Service still works
   — this is degraded, not down; if all replicas are `Pending`, it's down.

### `ErrImagePull` / `ImagePullBackOff`
1. Check the image name/tag for typos in the manifest.
2. Confirm the image actually exists in the registry you pointed to.
3. Confirm the cluster has pull credentials for a private registry
   (`imagePullSecrets`).
4. As a sanity check, swap in a known-public image temporarily to confirm the
   Deployment mechanics are otherwise correct.

### `CrashLoopBackOff`
A container that exits (even cleanly) shortly after starting gets restarted with
exponential backoff (10s, 20s, 40s, ...); after repeated crashes the status
becomes `CrashLoopBackOff`. Diagnosis order:
1. `kubectl get pods` — confirm the status and how many `RESTARTS` have occurred.
2. `kubectl describe pod <name>` — read Events for the container's last exit
   reason (`RunContainerError` = failed to start at all; `Error`/`Completed` with
   a non-zero exit code = crashed after starting; `OOMKilled` = killed for memory,
   go straight to section 3).
3. `kubectl logs <name> --previous` — this is the single most useful command
   here: it shows the stack trace/error from the crash itself, not from the
   fresh, possibly-not-yet-crashed replacement container.
4. Common root causes to check off: missing/misspelled env var or Secret key the
   app requires at boot, an unreachable dependency (DB, message broker) the app
   fails hard on instead of retrying, a permission error (e.g., binding to a
   privileged port `<1024` after switching to a non-root user), or a liveness
   probe with too tight a `failureThreshold`/`initialDelaySeconds` killing an app
   that just needed more time to boot (see section 2 and consider a
   `startupProbe`).
5. If the container depends on an external service that's currently down, that's
   often "working as intended" from Kubernetes' perspective — fix by making the
   app retry/backoff internally rather than crash-on-connect-failure, or at least
   don't let the liveness probe compound the outage (see section 2).

### OOMKilled
`kubectl describe pod` shows `Last State: Terminated, Reason: OOMKilled`. This is
a memory **limit** problem, not a crash bug: either the limit is set too low for
real usage (raise it per section 3.2) or the app has an actual leak (check via
`kubectl top pod` trending upward over time). For JVM apps, check that the heap
setting (`-Xmx` or `MaxRAMPercentage`) leaves headroom under the container's
memory limit per section 3.3 before assuming it's a leak.
