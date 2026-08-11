#!/bin/bash
set -e

echo "🎯 Inicializador — harness-saywa-s3-starter (Quarkus JAR Library)"
echo ""

# ── Función para leer limpio (sin secuencias ANSI) ──────────────────────────
read_clean() {
    local prompt="$1"
    local default="$2"
    local result

    # Usar read con -r para evitar interpretación de backslash
    # Redirigir stderr para suprimir warnings de terminal
    printf "%s" "$prompt"
    IFS= read -r result </dev/tty

    # Limpiar secuencias de escape ANSI y caracteres de control
    result=$(echo "$result" | sed 's/\x1b\[[0-9;]*[mGKHF]//g' | tr -d '\r\000-\010\013-\037\177')

    # Usar default si está vacío
    if [ -z "$result" ]; then
        result="$default"
    fi

    echo "$result"
}

# ── Solicitar parámetros ────────────────────────────────────────────────────
ARTIFACT_ID=$(read_clean "Artifact ID del módulo [aws-s3-starter]: " "aws-s3-starter")
PARENT_VERSION=$(read_clean "Versión del parent saywa-parent-bom [1.1.0-SNAPSHOT]: " "1.1.0-SNAPSHOT")
PROJECT_VERSION=$(read_clean "Versión del módulo [1.1.0-SNAPSHOT]: " "1.1.0-SNAPSHOT")
PROJECT_NAME=$(read_clean "Nombre display [Saywa AWS S3 Starter]: " "Saywa AWS S3 Starter")
PROJECT_DESCRIPTION=$(read_clean "Descripción [Starter base para S3 en Quarkus/Saywa]: " "Starter base para integraciones con Amazon S3 en servicios Quarkus del framework Saywa.")

# relativePath vacío — el parent está en .m2 local, no en filesystem
PARENT_RELATIVE_PATH=""

echo ""
echo "📋 Configuración:"
echo "  ARTIFACT_ID:    $ARTIFACT_ID"
echo "  PARENT_VERSION: $PARENT_VERSION"
echo "  PROJECT_VERSION:$PROJECT_VERSION"
echo "  PROJECT_NAME:   $PROJECT_NAME"
echo ""

CONFIRM=$(read_clean "¿Continuar? (y/n): " "y")
if [ "$CONFIRM" != "y" ]; then
    echo "❌ Cancelado"
    exit 1
fi

echo ""
echo "🔄 Aplicando configuración..."

# ── Detectar sed compatible ─────────────────────────────────────────────────
if [[ "$OSTYPE" == "darwin"* ]]; then
    _sed() { sed -i '' "$@"; }
else
    _sed() { sed -i "$@"; }
fi

# ── Reemplazar placeholders en pom.xml ──────────────────────────────────────
_sed "s|{{ARTIFACT_ID}}|${ARTIFACT_ID}|g"               pom.xml
_sed "s|{{PARENT_VERSION}}|${PARENT_VERSION}|g"         pom.xml
_sed "s|{{PROJECT_VERSION}}|${PROJECT_VERSION}|g"       pom.xml
_sed "s|{{PROJECT_NAME}}|${PROJECT_NAME}|g"             pom.xml
_sed "s|{{PROJECT_DESCRIPTION}}|${PROJECT_DESCRIPTION}|g" pom.xml
# relativePath vacío para buscar en .m2 local
_sed "s|<relativePath>{{PARENT_RELATIVE_PATH}}</relativePath>|<relativePath/>|g" pom.xml

echo "  ✅ pom.xml configurado"

# ── Reemplazar en feature_list.json ─────────────────────────────────────────
_sed "s|{{ARTIFACT_ID}}|${ARTIFACT_ID}|g"       feature_list.json
_sed "s|{{PARENT_VERSION}}|${PARENT_VERSION}|g" feature_list.json

echo "  ✅ feature_list.json configurado"

# ── Reemplazar en README.md ──────────────────────────────────────────────────
_sed "s|{{ARTIFACT_ID}}|${ARTIFACT_ID}|g"   README.md
_sed "s|{{PROJECT_NAME}}|${PROJECT_NAME}|g" README.md

echo "  ✅ README.md configurado"

# ── Validar que no quedan placeholders ───────────────────────────────────────
REMAINING=$(grep -r "{{.*}}" pom.xml feature_list.json README.md 2>/dev/null || true)
if [ -n "$REMAINING" ]; then
    echo "❌ Quedaron placeholders sin reemplazar:"
    echo "$REMAINING"
    exit 1
fi

# ── Validar que no hay caracteres de control en la versión ───────────────────
VERSION_CHECK=$(grep "<version>" pom.xml | head -3)
echo ""
echo "  Verificando versiones en pom.xml:"
echo "$VERSION_CHECK"

# ── Eliminar .template-guard ─────────────────────────────────────────────────
rm -f .template-guard
echo ""
echo "  ✅ Template desbloqueado"

echo ""
echo "✅ Template inicializado correctamente"
echo ""
echo "Próximos pasos:"
echo "  1. bash install-parent.sh   # (si no instalaste el BOM todavía)"
echo "  2. bash init.sh             # Verificar entorno"
echo "  3. code .                   # Abrir en Claude Code"
echo "     → 'Ejecuta el workflow de CLAUDE.md'"
