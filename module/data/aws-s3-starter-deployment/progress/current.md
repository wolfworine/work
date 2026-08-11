# Sesión activa

Sin trabajo pendiente: las 9 features de `feature_list.json` están `done`
(feature 9, `openapi_and_smoke_curl`, cerrada en esta sesión).

Última verificación real (no simulada) contra LocalStack:
- `mvn verify -Pintegration-tests` → BUILD SUCCESS, 9/9 tests.
- `./scripts/smoke-test.sh` (app corriendo con `-Dquarkus.profile=dev`) →
  11/11 checks OK.
- `/q/openapi` y `/q/swagger-ui/` responden 200.

Ver `progress/history.md` (entradas 2026-08-07) para el detalle de los
fixes de la feature 5 y la implementación de la feature 9.

Si se agregan features nuevas a `feature_list.json`, seguir el protocolo
normal de `CLAUDE.md` (implementer → reviewer) desde el paso 4.
