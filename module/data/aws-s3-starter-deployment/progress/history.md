# Historial (append-only)

## 2026-08-07 — Scaffold inicial (Cowork)
- Se generó el harness completo (AGENTS.md, CLAUDE.md, CHECKPOINTS.md, docs/,
  .claude/agents/, feature_list.json, init.sh) siguiendo el mismo patrón usado
  en `aws-s3-starter`.
- Se generó el código funcional: pom.xml (Quarkus 3.33.2.1, Java 21,
  quarkus-rest + quarkus-rest-jackson), S3Resource reactivo con las 8
  operaciones, DTOs, ExceptionMapper, application.properties con perfiles
  dev/test apuntando a LocalStack.
- Se generó docker-compose.yml con LocalStack (S3) + script de creación de
  bucket + Dockerfile.jvm para la app.
- Pendiente: test de integración automatizado (feature 5) y ejecución real
  contra Docker (no se pudo levantar Docker/Maven en este entorno de
  generación; se deja para la siguiente sesión con el harness).

## 2026-08-07 — Feature 5 (integration_test) cerrada, verificación real end-to-end OK
- `.template-guard` seguía presente (proyecto sin inicializar pese a lo que
  decía el historial anterior): se corrió `./template-init.sh -y` con
  valores por defecto, resolviendo los placeholders en pom.xml,
  docker-compose.yml, feature_list.json, README.md, init.sh,
  scripts/bootstrap.sh, localstack-init/01-create-bucket.sh y
  application.properties.
- Se corrió `./scripts/bootstrap.sh /ruta/a/saywa` contra el repo real.
  Encontró y se corrigieron 3 problemas reales al ejecutar contra
  LocalStack real (no simulados):
  1. **CDI no encontraba `S3StorageServiceImpl`**: el jar de
     `aws-s3-starter` no trae un índice Jandex, así que ArC no escaneaba
     sus clases. Fix: `quarkus.index-dependency.aws-s3-starter.*` en
     `application.properties` (config del lado del deployment, no toca el
     starter).
  2. **CDI no encontraba `S3AsyncClient`**: `quarkus-amazon-s3` no trae
     una implementación de cliente HTTP async concreta (solo el glue
     interno `quarkus-amazon-*-internal`). Fix: dependencia explícita
     `software.amazon.awssdk:netty-nio-client` en `pom.xml` (versión
     gestionada por `quarkus-amazon-services-bom` ya importado).
  3. **4 aserciones del IT fallaban** por no contemplar
     `compartamos.storage.s3.default-prefix=poc`: el starter antepone
     `poc/` a las claves normalizadas (`S3RequestFactory#normalizeKey`)
     pero NO a un `prefix` de listado explícito
     (`S3RequestFactory#resolvePrefix`) — comportamiento documentado, no
     bug. Fix: se ajustó `S3ResourceIT.java` para esperar las claves con
     prefijo en las respuestas de upload/copy/move y para listar con
     `prefix=poc/it/`.
- `mvn verify -Pintegration-tests` y `./scripts/bootstrap.sh` terminan en
  verde: "🎉 Verificación completa OK", 9/9 tests pasando contra LocalStack
  real (localstack/localstack:3.8, `docker compose ps` healthy).
- Feature 5 (`integration_test`) marcada `done` en `feature_list.json`.
  Con esto, **las 8 features del proyecto quedan en estado `done`** — no
  queda trabajo pendiente en `feature_list.json` en este momento.

## 2026-08-07 — Feature 9 (openapi_and_smoke_curl) agregada e implementada
- Motivo: el usuario no podía acceder al LocalStack Web App
  (app.localstack.cloud, requiere cuenta + `LOCALSTACK_AUTH_TOKEN`) para
  explorar el bucket con una UI. Se agregó la feature 9 a
  `feature_list.json` con dos entregables: documentación OpenAPI
  exportable a Postman, y un script de smoke-test por curl equivalente al
  test de integración.
- `pom.xml`: agregada `io.quarkus:quarkus-smallrye-openapi` (sin versión
  explícita, gestionada por `quarkus-bom`). Genera `/q/openapi` a partir de
  las anotaciones JAX-RS ya existentes en `S3Resource`, sin tocar su
  código.
- `application.properties`: `quarkus.swagger-ui.always-include=true` para
  que `/q/swagger-ui/` esté disponible también en el jar empaquetado (no
  solo en dev), más metadata de `mp.openapi.extensions.smallrye.info.*`.
- `scripts/smoke-test.sh` (nuevo, ejecutable): sube, verifica existencia,
  lista, descarga, prueba 404 en clave inexistente, copia, mueve, genera
  presigned URL y borra — 8 operaciones con asserts de código HTTP y
  contenido, exit code distinto de 0 si algo falla.
- Verificación real contra LocalStack (localstack/localstack:3.8 healthy):
  - `java -Dquarkus.profile=dev -jar target/quarkus-app/quarkus-run.jar` +
    `./scripts/smoke-test.sh` → 11/11 checks OK. (Nota: correrlo sin
    `-Dquarkus.profile=dev` activa `%prod`, que no tiene
    `endpoint-override` a LocalStack — hay que forzar el perfil dev/test.)
  - `curl http://localhost:8080/q/openapi` devuelve OpenAPI 3.1 válido
    documentando las 6 rutas / 8 operaciones de `S3Resource`.
  - `http://localhost:8080/q/swagger-ui/` responde 200.
  - `mvn verify -Pintegration-tests` sigue en verde (9/9) tras agregar la
    dependencia — sin regresiones.
- Se documentó el flujo de importación a Postman en `docs/verification.md`
  (sección 4) y `README.md`.
- Feature 9 marcada `done` en `feature_list.json`. **Las 9 features quedan
  en estado `done`.**

## 2026-08-07 — Feature 9 mejorada: anotaciones OpenAPI explícitas en S3Resource
- El usuario pidió revisar las guías oficiales de Quarkus
  (`openapi-swaggerui`, `rest-client`) y anotar el propio recurso en vez de
  depender solo de la generación automática desde JAX-RS.
- Se corrigió `application.properties`: la clave correcta es
  `quarkus.smallrye-openapi.info-title/info-version/info-description`
  (la guía la documenta así); `mp.openapi.extensions.smallrye.info.*` que
  se había usado antes no es una propiedad real de Quarkus y no tenía
  efecto (no rompía nada, pero tampoco hacía nada).
- `S3Resource.java`: agregadas anotaciones MicroProfile OpenAPI en los 8
  endpoints — `@Tag` a nivel de clase, `@Operation` (summary/description)
  y `@APIResponse`/`@APIResponses` con `@Schema` explícito por método,
  `@Parameter` para cada path/query param con ejemplos, y `@RequestBody`
  para el multipart de `upload`. Sin tocar la lógica de ningún método.
- Se detectaron y corrigieron 2 inconsistencias reales al inspeccionar el
  `/q/openapi` generado: los 404 de `download` y `presigned` quedaban sin
  `content` o con `mediaType=text/plain` cuando `S3ExceptionMappers`
  realmente serializa `ErrorResponseDto` como JSON (por defecto, no hay
  `@Produces` de error explícito) — se corrigió fijando
  `mediaType = MediaType.APPLICATION_JSON` en esos `@Content`.
- Verificación real contra LocalStack: `mvn verify -Pintegration-tests`
  (9/9) y `./scripts/smoke-test.sh` (11/11) siguen en verde tras el
  cambio; se inspeccionó el `/q/openapi` resultante confirmando tags,
  summaries, parámetros con ejemplos, request body del upload y los 4
  responses 404 con `ErrorResponseDto` en `application/json`.

## 2026-08-07 — Logging con Lombok en S3ExceptionMappers
- Se agregó `org.projectlombok:lombok:1.18.38` (última estable en Maven
  Central) como dependencia `provided` en `pom.xml` — solo compile-time,
  nada en el classpath de runtime.
- `S3ExceptionMappers.java`: reemplazado el campo manual
  `private static final Logger LOG = Logger.getLogger(...)` por
  `@Slf4j` de Lombok (campo `log` de tipo `org.slf4j.Logger`), a pedido
  explícito del usuario. (Primer intento usó `@JBossLog` por consistencia
  con el logger nativo de Quarkus/JBoss Logging que ya usaba el proyecto —
  el usuario corrigió: quería `@Slf4j` específicamente.) `slf4j-api` y el
  puente `org.jboss.slf4j:slf4j-jboss-logmanager` ya estaban en el
  classpath transitivamente (los trae Quarkus), no hizo falta agregar
  nada más.
- Verificado con `javap -p` que Lombok generó
  `private static final org.slf4j.Logger log;` en el .class compilado
  (no solo que compiló sin error). `mvn verify -Pintegration-tests`
  sigue en verde (9/9) tras el cambio.

## 2026-08-07 — Lombok centralizado en saywa-parent-bom
- El usuario había validado que `aws-s3-starter/pom.xml` (sibling module,
  ya con `@Slf4j` en su `S3AuditLogger`) declaraba Lombok con la misma
  versión que este módulo, pero cada `pom.xml` fijaba `${lombok.version}`
  por separado — no lo gestionaba `saywa-parent-bom`. A pedido del
  usuario, se centralizó:
  - `module/parent/pom.xml` (`saywa-parent-bom`): agregado
    `<lombok.version>1.18.38</lombok.version>` + entrada
    `org.projectlombok:lombok` (scope `provided`) en
    `<dependencyManagement>`.
  - `module/data/aws-s3-starter/pom.xml`: quitada la property
    `lombok.version` y el `<version>` explícito de la dependencia (hereda
    de `saywa-parent-bom` vía `<parent>`).
  - `pom.xml` (este módulo): este proyecto **no** usa `<parent>` (es un
    template standalone, ver `TEMPLATE.md`), así que en vez de heredar se
    importó `saywa-parent-bom` como BOM (`<type>pom</type><scope>import</scope>`)
    en su propio `dependencyManagement`, y se quitó la property
    `lombok.version` y el `<version>` de la dependencia.
- Reinstalados en `.m2`: `module/parent` y `aws-s3-starter` (`mvn install
  -DskipTests`), en ese orden, antes de recompilar este módulo.
- Verificado con `mvn dependency:tree | grep lombok` que la versión
  resuelta sigue siendo `1.18.38` (vía el BOM importado, no hardcodeada
  acá) y con `javap -p` que el campo `log` se sigue generando igual.
- `mvn verify -Pintegration-tests` en este módulo (9/9) y `mvn test` en
  `aws-s3-starter` (91/91) verdes tras el cambio — sin regresiones en
  ninguno de los dos módulos.
