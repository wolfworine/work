#!/bin/bash
set -e

# Modo no interactivo: ./template-init.sh -y | --yes | --defaults
# (o simplemente cuando no hay una terminal real conectada a stdin)
# usa todos los valores por defecto sin preguntar nada.
NON_INTERACTIVE=0
case "${1:-}" in
    -y|--yes|--defaults) NON_INTERACTIVE=1 ;;
esac
if [ ! -t 0 ]; then
    NON_INTERACTIVE=1
fi

echo "🎯 Inicializador — harness-aws-s3-starter-deployment (Quarkus REST PoC)"
if [ "$NON_INTERACTIVE" = "1" ]; then
    echo "(modo no interactivo: usando valores por defecto / variables de entorno)"
fi
echo ""

# read_clean: usa la variable de entorno del mismo nombre si ya está seteada
# (útil para automatizar: DEPLOYMENT_ARTIFACT_ID=mi-app ./template-init.sh -y),
# si no, pregunta por stdin normal (sin forzar /dev/tty, que falla en muchas
# terminales — Git Bash en Windows, shells sin tty real, ejecución por un
# agente), y si estamos en modo no interactivo usa directamente el default.
read_clean() {
    local var_name="$1"
    local prompt="$2"
    local default="$3"
    local existing="${!var_name:-}"
    local result

    if [ -n "$existing" ]; then
        echo "$existing"
        return
    fi

    if [ "$NON_INTERACTIVE" = "1" ]; then
        echo "$default"
        return
    fi

    printf "%s" "$prompt" >&2
    IFS= read -r result || result=""
    result=$(printf '%s' "$result" | tr -d '\r')
    if [ -z "$result" ]; then
        result="$default"
    fi
    echo "$result"
}

# ── Solicitar parámetros ────────────────────────────────────────────────────
DEPLOYMENT_ARTIFACT_ID=$(read_clean DEPLOYMENT_ARTIFACT_ID "Artifact ID de este deployment [aws-s3-starter-deployment]: " "aws-s3-starter-deployment")
DEPLOYMENT_VERSION=$(read_clean DEPLOYMENT_VERSION "Versión del deployment [1.0.0-SNAPSHOT]: " "1.0.0-SNAPSHOT")
PROJECT_NAME=$(read_clean PROJECT_NAME "Nombre display [Saywa AWS S3 Starter — Deployment PoC]: " "Saywa AWS S3 Starter — Deployment PoC")
PROJECT_DESCRIPTION=$(read_clean PROJECT_DESCRIPTION "Descripción [App Quarkus REST reactiva que consume el starter para validarlo contra LocalStack.]: " "App Quarkus REST reactiva que consume el starter para validarlo contra LocalStack.")
STARTER_ARTIFACT_ID=$(read_clean STARTER_ARTIFACT_ID "Artifact ID del starter a consumir [aws-s3-starter]: " "aws-s3-starter")
STARTER_VERSION=$(read_clean STARTER_VERSION "Versión del starter [1.1.0-SNAPSHOT]: " "1.1.0-SNAPSHOT")
BUCKET_NAME=$(read_clean BUCKET_NAME "Nombre del bucket en LocalStack [saywa-s3-deployment-poc]: " "saywa-s3-deployment-poc")

echo ""
echo "📋 Configuración:"
echo "  DEPLOYMENT_ARTIFACT_ID: $DEPLOYMENT_ARTIFACT_ID"
echo "  DEPLOYMENT_VERSION:     $DEPLOYMENT_VERSION"
echo "  PROJECT_NAME:           $PROJECT_NAME"
echo "  STARTER_ARTIFACT_ID:    $STARTER_ARTIFACT_ID"
echo "  STARTER_VERSION:        $STARTER_VERSION"
echo "  BUCKET_NAME:            $BUCKET_NAME"
echo ""
echo "⚠️  Nota: el paquete Java (com.saywa.framework.deployment.s3) y las"
echo "    importaciones de S3Resource/DTOs quedan fijos — están escritos"
echo "    contra la API de S3StorageService. Si el starter que eliges tiene"
echo "    una API distinta, deberás ajustar resource/dto/mapper a mano (o"
echo "    pedirle al harness — CLAUDE.md — que lo haga vía feature_list.json)."
echo ""

CONFIRM=$(read_clean CONFIRM "¿Continuar? (y/n): " "y")
if [ "$CONFIRM" != "y" ]; then
    echo "❌ Cancelado"
    exit 1
fi

echo ""
echo "🔄 Aplicando configuración..."

if [[ "$OSTYPE" == "darwin"* ]]; then
    _sed() { sed -i '' "$@"; }
else
    _sed() { sed -i "$@"; }
fi

FILES_WITH_PLACEHOLDERS="pom.xml feature_list.json README.md docker-compose.yml init.sh scripts/bootstrap.sh localstack-init/01-create-bucket.sh src/main/resources/application.properties"

for f in $FILES_WITH_PLACEHOLDERS; do
    [ -f "$f" ] || continue
    _sed "s|{{DEPLOYMENT_ARTIFACT_ID}}|${DEPLOYMENT_ARTIFACT_ID}|g" "$f"
    _sed "s|{{DEPLOYMENT_VERSION}}|${DEPLOYMENT_VERSION}|g" "$f"
    _sed "s|{{PROJECT_NAME}}|${PROJECT_NAME}|g" "$f"
    _sed "s|{{PROJECT_DESCRIPTION}}|${PROJECT_DESCRIPTION}|g" "$f"
    _sed "s|{{STARTER_ARTIFACT_ID}}|${STARTER_ARTIFACT_ID}|g" "$f"
    _sed "s|{{STARTER_VERSION}}|${STARTER_VERSION}|g" "$f"
    _sed "s|{{BUCKET_NAME}}|${BUCKET_NAME}|g" "$f"
    echo "  ✅ $f configurado"
done

# ── Validar que no quedan placeholders conocidos (no toca sintaxis legítima
#    como {{.Health}} de docker compose --format) ───────────────────────────
KNOWN_PLACEHOLDERS='{{DEPLOYMENT_ARTIFACT_ID}}|{{DEPLOYMENT_VERSION}}|{{PROJECT_NAME}}|{{PROJECT_DESCRIPTION}}|{{STARTER_ARTIFACT_ID}}|{{STARTER_VERSION}}|{{BUCKET_NAME}}'
REMAINING=$(grep -rE "$KNOWN_PLACEHOLDERS" $FILES_WITH_PLACEHOLDERS 2>/dev/null || true)
if [ -n "$REMAINING" ]; then
    echo "❌ Quedaron placeholders sin reemplazar:"
    echo "$REMAINING"
    exit 1
fi

chmod +x init.sh scripts/bootstrap.sh localstack-init/01-create-bucket.sh

rm -f .template-guard
echo ""
echo "  ✅ Template desbloqueado"

echo ""
echo "✅ Template inicializado correctamente"
echo ""
echo "Próximos pasos:"
echo "  1. ./scripts/bootstrap.sh /ruta/al/repo/saywa   # instala el starter, levanta LocalStack, compila, testea"
echo "  2. claude                                        # o abre el harness y sigue CLAUDE.md"
