# CHECKPOINTS — Evaluación del estado final

> En sistemas multi-agente no se evalúa el camino, se evalúa el destino.

## C1 — El harness está completo

- [ ] Existen los archivos base: `AGENTS.md`, `CLAUDE.md`, `init.sh`, `feature_list.json`, `progress/current.md`
- [ ] Existen los 3 docs: `docs/architecture.md`, `docs/conventions.md`, `docs/verification.md`
- [ ] `./init.sh` termina con exit code 0

## C2 — El estado es coherente

- [ ] Como mucho una feature en `in_progress` en `feature_list.json`
- [ ] Toda feature `done` tiene tests asociados que pasan
- [ ] `progress/current.md` describe la sesión activa o está vacío limpio

## C3 — El código respeta la arquitectura del starter

- [ ] `api/` y `domain/` NO importan nada del SDK de AWS (`software.amazon.awssdk`)
- [ ] `S3Presigner` solo aparece en `service/` y en tests — nunca en `api/` ni `domain/`
- [ ] `S3StorageServiceImpl` usa `Uni.createFrom().completionStage(...)` — sin bloqueos
- [ ] `move()` ejecuta `delete` solo si `copy` fue exitoso — lógica en el impl
- [ ] `download()` hace `headObject` previo para validar `contentLength` vs `max-download-size`
- [ ] `presigned()` hace `headObject` previo y audita solo `objectKey` + `ttl` (nunca la URL)
- [ ] `S3ExceptionMapper.map()` preserva el `Throwable` causa en las CUATRO ramas
- [ ] Cada excepción propia tiene `(String message, Throwable cause)` como único constructor público
- [ ] Records en `domain/` no tienen setters — son inmutables
- [ ] No hay `System.out.println()` sueltos ni TODOs sin contexto

## C4 — La verificación es real

- [ ] `src/test/java/unit/` tiene tests con Mockito, sin `@QuarkusTest`
- [ ] Los mocks usan `CompletableFuture.completedFuture()` / `failedFuture()` para simular el SDK
- [ ] Test de `presigned()` verifica que el evento de auditoría NO incluye la URL completa
- [ ] Test de `move()` verifica el caso "copy ok, delete falla"
- [ ] Test de `download()` verifica que falla con `S3ConfigurationException` si excede `max-download-size`
- [ ] `mvn test` muestra > 0 tests y todos verdes

## C5 — La sesión se cerró bien

- [ ] No hay archivos sin trackear sospechosos (`*.class` fuera de `target/`)
- [ ] `progress/history.md` tiene entrada de la última sesión
- [ ] La feature trabajada está en su estado correcto en `feature_list.json`
