# Template — harness-saywa-s3-starter

Proyecto template parametrizable para generar el módulo `aws-s3-starter`
del framework Saywa (Quarkus JAR Library — sin REST, sin main).

## Cómo usar este template

### 1. Copiar
```bash
cp -r harness-saywa-s3-starter modules/data/aws-s3-starter
cd modules/data/aws-s3-starter
```

### 2. Inicializar con tus parámetros
```bash
./template-init.sh
```
Te pedirá:
- **Artifact ID** (default: `aws-s3-starter`)
- **Parent version** (default: `1.1.0-SNAPSHOT`)
- **Parent relativePath** (default: `../../parent/pom.xml`)
- **Project version**, **name**, **description**

### 3. Verificar entorno
```bash
./init.sh
```
⚠️ `init.sh` falla con error si `template-init.sh` no se ejecutó primero.

### 4. Resetear si algo salió mal
```bash
./template-reset.sh
./template-init.sh
```

## Flujo completo

```
template-init.sh          → reemplaza {{placeholders}} en pom.xml y otros
        ↓
init.sh                   → verifica Java 21, Maven, compila, tests, red flags
        ↓
Claude Code / IA          → implementa features en orden (feature_list.json)
        ↓
Por cada feature:
  implementer → código + tests
  reviewer    → valida vs CHECKPOINTS.md
  init.sh     → verde ✅
```

## Placeholders en pom.xml

| Placeholder | Valor por defecto |
|---|---|
| `{{ARTIFACT_ID}}` | `aws-s3-starter` |
| `{{PARENT_VERSION}}` | `1.1.0-SNAPSHOT` |
| `{{PARENT_RELATIVE_PATH}}` | `../../parent/pom.xml` |
| `{{PROJECT_VERSION}}` | `1.1.0-SNAPSHOT` |
| `{{PROJECT_NAME}}` | `Saywa AWS S3 Starter` |
| `{{PROJECT_DESCRIPTION}}` | texto largo |

## Fases del proyecto Saywa S3

| Fase | Harness | Cuándo |
|---|---|---|
| 1 | `harness-saywa-s3-starter` (este) | Ahora |
| 2 | `harness-saywa-s3-deployment` | Cuando Fase 1 esté estable |
| 3 | `harness-saywa-s3-transfer` | Solo con consumidor real de bulk |
