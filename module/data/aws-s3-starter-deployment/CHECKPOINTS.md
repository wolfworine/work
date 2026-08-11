# CHECKPOINTS — Evaluación del estado final

> En sistemas multi-agente no se evalúa el camino, se evalúa el destino.

## C1 — El harness está completo

- [ ] Existen los archivos base: `AGENTS.md`, `CLAUDE.md`, `init.sh`, `feature_list.json`, `progress/current.md`
- [ ] Existen los 3 docs: `docs/architecture.md`, `docs/conventions.md`, `docs/verification.md`
- [ ] `./init.sh` termina con exit code 0

## C2 — El estado es coherente

- [ ] Como mucho una feature en `in_progress` en `feature_list.json`
- [ ] Toda feature `done` tiene una verificación real asociada (curl o test de integración), no solo compilación
- [ ] `progress/current.md` describe la sesión activa o está vacío limpio

## C3 — El código respeta la arquitectura de la app deployment

- [ ] `S3Resource` (y cualquier otro recurso) solo usa `@Inject S3StorageService` — nunca `S3Client`/`S3AsyncClient`
- [ ] No hay lógica de negocio S3 duplicada (validación de tamaño, normalización de prefijo, auditoría) — eso vive en el starter
- [ ] Los endpoints devuelven `Uni<...>` — sin `.await().indefinitely()` en el hilo de request
- [ ] El `ExceptionMapper` traduce la jerarquía `S3StorageException` a códigos HTTP coherentes (404/403/400/500)
- [ ] `application.properties` en `%dev`/`%test` apunta a LocalStack (`http://localhost:4566`), nunca a AWS real
- [ ] `quarkus.s3.path-style-access=true` está presente (obligatorio para LocalStack)
- [ ] No hay `System.out.println()` sueltos ni TODOs sin contexto

## C4 — La verificación es real (contra LocalStack, no mocks)

- [ ] `docker compose up -d` levanta LocalStack sano (`/_localstack/health` responde S3 `available`)
- [ ] El bucket configurado existe en LocalStack tras `localstack-init/01-create-bucket.sh`
- [ ] Cada endpoint del `feature_list.json` tiene al menos un `curl` documentado en `docs/verification.md` que se ejecutó y funcionó
- [ ] El test de integración (`src/test/java/integration/`) pasa con `mvn verify -Pintegration-tests` contra LocalStack real
- [ ] Se validó el ciclo completo: upload -> list -> download -> copy -> move -> exists -> presigned -> delete

## C5 — La sesión se cerró bien

- [ ] No hay archivos sin trackear sospechosos (`*.class` fuera de `target/`)
- [ ] `progress/history.md` tiene entrada de la última sesión
- [ ] La feature trabajada está en su estado correcto en `feature_list.json`
- [ ] `docker compose down` no deja contenedores huérfanos (verificar con `docker ps -a`)
