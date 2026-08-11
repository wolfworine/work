# Template — harness-aws-s3-starter-deployment

Proyecto template parametrizable para generar un módulo de **PoC de
consumo** (Fase 3 del plan Saywa) contra LocalStack, para cualquier starter
Quarkus/Mutiny del framework Saywa que exponga una API tipo
`XxxStorageService` inyectable por CDI — no solo `aws-s3-starter`.

## Cómo usar este template

### 1. Copiar

```bash
cp -r aws-s3-starter-deployment modules/data/<mi-nuevo-deployment>
cd modules/data/<mi-nuevo-deployment>
```

### 2. Inicializar con tus parámetros

```bash
./template-init.sh
```

Te pedirá:

- **Artifact ID de este deployment** (default: `aws-s3-starter-deployment`)
- **Versión del deployment** (default: `1.0.0-SNAPSHOT`)
- **Nombre display**, **descripción**
- **Artifact ID del starter a consumir** (default: `aws-s3-starter`)
- **Versión del starter** (default: `1.1.0-SNAPSHOT`)
- **Nombre del bucket en LocalStack** (default: `saywa-s3-deployment-poc`)

### 3. Verificar entorno y correr todo de punta a punta

```bash
./scripts/bootstrap.sh /ruta/al/repo/saywa
```

⚠️ `init.sh` y `scripts/bootstrap.sh` fallan con error si `template-init.sh`
no se ejecutó primero (bloqueados por `.template-guard`).

### 4. Resetear si algo salió mal

```bash
./template-reset.sh
./template-init.sh
```

## Flujo completo

```
template-init.sh           → reemplaza {{placeholders}} en pom.xml, docker-compose.yml,
                              feature_list.json, README.md, init.sh, scripts/bootstrap.sh,
                              localstack-init/, application.properties
        ↓
scripts/bootstrap.sh       → instala el starter en .m2, levanta LocalStack, compila,
                              corre el test de integración end-to-end
        ↓
Claude Code / IA           → implementa/ajusta features en orden (feature_list.json)
        ↓
Por cada feature:
  implementer → código + verificación real contra LocalStack
  reviewer    → valida vs CHECKPOINTS.md
  init.sh     → verde ✅
```

## Placeholders

| Placeholder | Valor por defecto | Dónde se usa |
|---|---|---|
| `{{DEPLOYMENT_ARTIFACT_ID}}` | `aws-s3-starter-deployment` | `pom.xml`, `docker-compose.yml`, `feature_list.json`, `README.md`, `application.properties` |
| `{{DEPLOYMENT_VERSION}}` | `1.0.0-SNAPSHOT` | `pom.xml` |
| `{{PROJECT_NAME}}` | `Saywa AWS S3 Starter — Deployment PoC` | `pom.xml` |
| `{{PROJECT_DESCRIPTION}}` | texto largo | `pom.xml` |
| `{{STARTER_ARTIFACT_ID}}` | `aws-s3-starter` | `pom.xml`, `init.sh`, `scripts/bootstrap.sh`, `feature_list.json`, `README.md`, `application.properties` |
| `{{STARTER_VERSION}}` | `1.1.0-SNAPSHOT` | mismos archivos que arriba |
| `{{BUCKET_NAME}}` | `saywa-s3-deployment-poc` | `docker-compose.yml`, `localstack-init/01-create-bucket.sh`, `application.properties` |

**Lo que NO se parametriza:** el paquete Java
(`com.saywa.framework.deployment.s3`) y el código de `resource/`, `dto/` y
`mapper/`, porque están escritos contra la API concreta de
`S3StorageService`. Si apuntas este template a un starter con una API
distinta (otro servicio AWS, otra forma), el código Java debe reescribirse
— para eso está el flujo `implementer`/`reviewer` de `CLAUDE.md`: agrega una
feature a `feature_list.json` describiendo la nueva API a consumir y deja
que el harness la implemente.

## Relación con `aws-s3-starter`

Este template nació como Fase 3 (`aws-s3-starter-deployment`) del plan de
`aws-s3-starter` (Fase 1) y `aws-s3-transfer-manager-starter` (Fase 2, aún
no construida). Si Fase 2 necesita su propio PoC de consumo, este mismo
template es el punto de partida — cambia solo `STARTER_ARTIFACT_ID`/
`STARTER_VERSION` y ajusta `resource/S3Resource.java` si la API del starter
de transferencias masivas difiere de `S3StorageService`.
