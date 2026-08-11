# CLAUDE.md — Instrucciones para Claude Code (orquestador)

## Tu rol

Eres el **orquestador**. Divides el trabajo en subagentes. Nunca implementas directamente.

## Protocolo de arranque

0. Verifica que `.template-guard` NO existe (si existe → ejecuta
   `./template-init.sh` primero; ver `TEMPLATE.md`). Sin esto, `pom.xml`,
   `docker-compose.yml`, etc. todavía tienen `{{placeholders}}` sin resolver.
1. **Primera vez en esta máquina / entorno**: corre
   `./scripts/bootstrap.sh /ruta/al/repo/saywa` (la ruta es la raíz del repo
   que contiene `module/data/aws-s3-starter` — pregúntala al usuario si no
   la sabes, o usa `find / -maxdepth 6 -type d -name aws-s3-starter
   2>/dev/null` para localizarla). Este script instala el starter en `.m2`
   si falta, levanta LocalStack, espera el healthcheck, compila esta app y
   corre el test de integración end-to-end. Si termina con
   "🎉 Verificación completa OK", marca la feature 5 (`integration_test`)
   como `done` en `feature_list.json` y mueve la entrada de
   `progress/current.md` a `progress/history.md` antes de seguir.
   Si `bootstrap.sh` falla, lee el error, corrígelo (no lo ignores) y
   vuelve a correrlo — no continúes al resto del protocolo hasta que pase.
2. En sesiones siguientes: verifica que `docker compose ps` muestre
   `localstack` healthy (si no, `docker compose up -d` y espera al
   healthcheck).
3. Verifica que `~/.m2/repository/com/saywa/framework/<starter>/<version>/` exista (si no, vuelve al paso 1).
4. Lee `feature_list.json` → toma la primera feature `pending`.
5. Lee `progress/current.md` → entiende el estado de la sesión anterior.
6. Lanza subagente **implementer** con la feature.
7. Lanza subagente **reviewer** para validar.
8. Si aprueba → marca `done` + actualiza `progress/history.md`.
9. Repite desde paso 4.

## Prompt para IMPLEMENTER

```
Implementa la feature "{{feature.title}}" (ID {{feature.id}}) del harness-aws-s3-starter-deployment.

Contexto:
- App Quarkus REST reactiva (RESTEasy Reactive / quarkus-rest + Mutiny), NO librería
- Consume S3StorageService del starter aws-s3-starter vía @Inject — nunca reimplementa su lógica
- Paquete base: com.saywa.framework.deployment.s3
- Reactivo puro: Uni<T> — NUNCA join(), get(), await().indefinitely()
- Corre contra LocalStack (docker-compose.yml en la raíz), nunca AWS real
- DTOs propios en dto/ para (de)serialización JSON — no se exponen los records
  de domain/ del starter directamente si requieren adaptación (p.ej. byte[] -> base64)

Acceptance criteria:
{{feature.acceptance}}

Pasos:
1. Leer docs/architecture.md y docs/conventions.md
2. Verificar LocalStack arriba (docker compose ps)
3. Crear código en src/main/
4. Ejecutar mvn compile — debe estar verde
5. Probar el endpoint con curl contra LocalStack real (ver docs/verification.md)
6. Reportar archivos creados y comandos curl usados en progress/current.md
NO marques done — solo implementa y reporta.
```

## Prompt para REVIEWER

```
Revisa la implementación de "{{feature.title}}" (ID {{feature.id}}).
Verifica contra CHECKPOINTS.md (C1-C5) y docs/verification.md.
Ejecuta ./init.sh y los curl de docs/verification.md — deben pasar contra LocalStack real.
Reporta APPROVED o CHANGES_REQUESTED en progress/review.md con archivo:línea exacto.
```

## Reglas

- Una feature a la vez.
- Si reviewer rechaza 3 veces → para y pide ayuda humana.
- Actualiza progress/ constantemente.
- La verificación de "done" para este proyecto SIEMPRE implica LocalStack real corriendo,
  nunca mocks (los mocks ya se hicieron en el starter base).
