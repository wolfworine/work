# AGENTS.md — Mapa de navegación para agentes de IA

> Punto de entrada para cualquier agente. NO es una biblia: es un **mapa**.
> Lee solo lo que necesites cuando lo necesites.

---

## 0. Qué es este proyecto

`aws-s3-starter-deployment` es la **Fase 3 (PoC de consumo)** del plan Saywa
para `aws-s3-starter`: una aplicación Quarkus REST reactiva que **consume**
el starter (no lo reimplementa) y expone endpoints HTTP para validar sus
8 operaciones contra LocalStack en Docker Compose, en un entorno controlado
y sin acoplar el starter base a la pila de pruebas.

## 1. Antes de empezar (obligatorio)

0. Verifica que `.template-guard` NO existe. Si existe, este proyecto
   todavía es una plantilla sin inicializar — corre `./template-init.sh`
   (ver `TEMPLATE.md`) antes de tocar nada más.
1. Ejecuta `./init.sh` — si falla, **para** y resuelve el entorno (o corre
   `./scripts/bootstrap.sh /ruta/al/repo/saywa` la primera vez).
2. Verifica que LocalStack esté arriba: `docker compose ps` (si no, `docker compose up -d`).
3. Lee `progress/current.md` para entender el estado de la última sesión.
4. Lee `feature_list.json` y elige **una** tarea `pending`. No mezcles features.

## 2. Mapa del repositorio

| Archivo / carpeta | Qué contiene | Cuándo leerlo |
|---|---|---|
| `feature_list.json` | Cola de trabajo con acceptance criteria | Siempre, al empezar |
| `progress/current.md` | Estado de la sesión activa | Siempre, al empezar |
| `progress/history.md` | Bitácora append-only | Si necesitas contexto histórico |
| `docs/architecture.md` | Arquitectura de la app deployment + relación con el starter | Antes de implementar |
| `docs/conventions.md` | Reglas de código: naming, Uni<T>, DTOs vs domain/ | Antes de escribir código |
| `docs/verification.md` | Cómo verificar que el trabajo funciona (curl, docker compose, mvn) | Antes de declarar done |
| `CHECKPOINTS.md` | Criterios objetivos de estado final | Para auto-evaluarte |
| `.claude/agents/` | Definiciones de subagentes | Si orquestas trabajo |
| `docker-compose.yml` | LocalStack (+ la app opcionalmente, perfil `app`) | Al levantar el entorno |
| `localstack-init/` | Scripts que crean el bucket al arrancar LocalStack | Si cambias el nombre del bucket |
| `src/main/java/.../resource/` | `S3Resource` — endpoints HTTP reactivos | Contrato HTTP |
| `src/main/java/.../dto/` | DTOs de request/response JSON | Serialización |
| `src/main/java/.../mapper/` | Excepciones del starter -> respuestas HTTP | Manejo de errores |
| `src/main/resources/application.properties` | Config Quarkus + LocalStack | Configuración |
| `src/test/java/integration/` | Tests end-to-end contra LocalStack real | Verificación |

## 3. Reglas duras (no negociables)

- **Una sola feature a la vez.**
- **No declares done sin verificación real** (`./init.sh` + `docker compose up -d` + curl o test verde).
- **Nunca reimplementes lógica S3**: todo pasa por `@Inject S3StorageService`.
- **Nunca `S3Client`/`S3AsyncClient` directo en este módulo** — eso vive en el starter.
- **Nunca `.join()`, `.get()`, `.await().indefinitely()`** en el código de request de un endpoint.
- **LocalStack, nunca AWS real** — si ves credenciales que no sean `test`/`test` en `%dev`/`%test`, es un bug.

## 4. Relación con `aws-s3-starter`

Este proyecto **no** copia código del starter. Depende de él como JAR Maven
(`com.saywa.framework:aws-s3-starter:1.1.0-SNAPSHOT`, instalado en `.m2`
local con `mvn install` desde el repo del starter). Si `mvn compile` falla
con "no se pudo resolver la dependencia", el starter no está instalado en
`.m2` — ver `README.md` sección Quickstart, paso 1.
