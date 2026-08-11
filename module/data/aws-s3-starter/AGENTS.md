# AGENTS.md — Mapa de navegación para agentes de IA

> Punto de entrada para cualquier agente. NO es una biblia: es un **mapa**.
> Lee solo lo que necesites cuando lo necesites.

---

## 1. Antes de empezar (obligatorio)

1. Ejecuta `./init.sh` — si falla, **para** y resuelve el entorno.
2. Lee `progress/current.md` para entender el estado de la última sesión.
3. Lee `feature_list.json` y elige **una** tarea `pending`. No mezcles features.

## 2. Mapa del repositorio

| Archivo / carpeta | Qué contiene | Cuándo leerlo |
|---|---|---|
| `feature_list.json` | Cola de trabajo con acceptance criteria | Siempre, al empezar |
| `progress/current.md` | Estado de la sesión activa | Siempre, al empezar |
| `progress/history.md` | Bitácora append-only | Si necesitas contexto histórico |
| `docs/architecture.md` | Arquitectura del starter Quarkus/Mutiny/S3 | Antes de implementar |
| `docs/conventions.md` | Reglas de código: naming, Javadoc, Uni<T> | Antes de escribir código |
| `docs/verification.md` | Cómo verificar que el trabajo funciona | Antes de declarar done |
| `CHECKPOINTS.md` | Criterios objetivos de estado final | Para auto-evaluarte |
| `.claude/agents/` | Definiciones de subagentes | Si orquestas trabajo |
| `src/main/java/.../api/` | Interfaz pública S3StorageService | Contrato público |
| `src/main/java/.../domain/` | Records inmutables sin AWS SDK | DTOs del starter |
| `src/main/java/.../config/` | @ConfigMapping Quarkus | Configuración |
| `src/main/java/.../service/` | S3StorageServiceImpl (Uni<T> reactivo) | Implementación core |
| `src/main/java/.../exception/` | Mapper + jerarquía de excepciones | Manejo de errores |
| `src/test/java/unit/` | Tests sin Quarkus real (Mockito) | Verificación unitaria |
| `src/test/java/integration/` | Tests con LocalStack (perfil Maven) | Verificación integración |

## 3. Reglas duras (no negociables)

- **Una sola feature a la vez.**
- **No declares done sin tests verdes.** Ejecuta `./init.sh`.
- **Nunca `join()`, `get()`, `await().indefinitely()`, ni `Thread.sleep()`.**
- **Nunca `S3Client` síncrono** — solo `S3AsyncClient`.
- **Nunca tipos del SDK de AWS en la API pública** (`api/`, `domain/`).
- **Nunca `S3Presigner.create()` dentro de métodos** — se inyecta vía CDI.
- **Records inmutables para todo el dominio** — sin setters.
- **Javadoc obligatorio** en todo objeto público — es JAR, el consumidor no ve el fuente.
- **Cada excepción propia expone `(String message, Throwable cause)` como único constructor.**
- **Auditoría sin registrar** contenido, credenciales ni URLs prefirmadas completas.

## 4. Cómo elegir una tarea

```
1. Abre feature_list.json
2. Filtra por status == "pending"
3. Toma la de menor "id"
4. Cambia su status a "in_progress" y guarda
5. Anota en progress/current.md: feature, hora de inicio, plan breve
```

## 5. Estructura del starter (quick reference)

```
com.saywa.framework.data.s3
├── api/            S3StorageService.java          ← interfaz pública
├── config/         S3Configuration.java           ← @ConfigMapping
│                   S3ConfigurationValidator.java  ← validaciones startup
├── domain/         S3ObjectRequest.java           ← records inmutables
│                   S3ObjectResponse.java
│                   S3ObjectContent.java
│                   S3ObjectSummary.java           ← incluye lastModified
│                   S3Operation.java               ← enum
├── service/        S3StorageServiceImpl.java      ← Uni<T> reactivo
├── factory/        S3RequestFactory.java          ← builders AWS SDK
├── mapper/         S3ResponseMapper.java          ← SDK → domain
├── audit/          S3AuditEvent.java
│                   S3AuditLogger.java
├── exception/      S3StorageException.java
│                   S3ObjectNotFoundException.java
│                   S3AccessDeniedException.java
│                   S3ConfigurationException.java
│                   S3ExceptionMapper.java
└── util/           S3KeyUtils.java
                    S3ContentTypeUtils.java
```

## 6. Cierre de sesión

1. Ejecuta `./init.sh` — todo verde.
2. Si la tarea está acabada: marca `status: "done"` en `feature_list.json`.
3. Mueve el resumen de `progress/current.md` al final de `progress/history.md`.
4. Vacía `progress/current.md` dejando solo la plantilla.

## 7. Red flags específicas de este starter

- 🚩 `S3AsyncClient`, `S3Presigner`, `NoSuchKeyException` en `api/` o `domain/`
- 🚩 Llamar `S3Presigner.create()` dentro de un método de servicio
- 🚩 Usar `.await().indefinitely()`, `.join()`, o `Thread.sleep()`
- 🚩 Records con setters o estado mutable
- 🚩 Excepciones sin preservar la causa (`Throwable cause`)
- 🚩 Auditoría que registra URLs prefirmadas completas o contenido de archivos
- 🚩 `S3ConfigurationException` sin ser hija de `S3StorageException`
- 🚩 Javadoc vacío, placeholder, o ausente en cualquier clase pública
