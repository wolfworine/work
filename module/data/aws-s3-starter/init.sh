#!/bin/bash
set -e

echo "🔍 Verificando entorno — harness-saywa-s3-starter"
echo ""

# ── Detectar Java (rutas conocidas Windows/Linux) ──────────────────────────
if ! command -v java &> /dev/null; then
    JAVA_CANDIDATES=(
        "/c/Programa/graalvm-jdk-21.0.5+9.1"
        "/c/Program Files/Java/jdk-21"
        "/c/Program Files/Common Files/Oracle/Java/javapath"
    )
    for candidate in "${JAVA_CANDIDATES[@]}"; do
        if [ -f "$candidate/bin/java.exe" ] || [ -f "$candidate/bin/java" ]; then
            export JAVA_HOME="$candidate"
            export PATH="$JAVA_HOME/bin:$PATH"
            echo "ℹ️  Java encontrado en: $JAVA_HOME"
            break
        fi
    done
fi

if ! command -v java &> /dev/null; then
    echo "❌ Java no encontrado. Exporta manualmente:"
    echo "   export JAVA_HOME='/c/Programa/graalvm-jdk-21.0.5+9.1'"
    echo "   export PATH=\$JAVA_HOME/bin:\$PATH"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
echo "✅ Java $JAVA_VERSION"

# ── Detectar Maven ─────────────────────────────────────────────────────────
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven no encontrado en PATH."
    exit 1
fi
echo "✅ Maven $(mvn -v 2>/dev/null | head -n 1 | cut -d' ' -f3)"
echo ""

# ── Verificar que el parent BOM esté en .m2 ────────────────────────────────
PARENT_IN_M2="$HOME/.m2/repository/com/saywa/framework/saywa-parent-bom/1.1.0-SNAPSHOT/saywa-parent-bom-1.1.0-SNAPSHOT.pom"
if [ ! -f "$PARENT_IN_M2" ]; then
    echo "⚠️  saywa-parent-bom no está en .m2 local."
    echo "   Instalando desde el reactor..."
    # Instalar el parent desde la raíz del proyecto
    SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
    ROOT_DIR="$(cd "$SCRIPT_DIR/../../.." && pwd)"
    mvn install -f "$ROOT_DIR/pom.xml" -pl module/parent -am -DskipTests -q
    echo "   ✅ saywa-parent-bom instalado en .m2"
fi
echo "✅ saywa-parent-bom en .m2 local"
echo ""

# ── Compilar ───────────────────────────────────────────────────────────────
echo "🔨 Compilando (mvn compile)..."
mvn compile -q

echo ""
echo "🧪 Ejecutando tests (mvn test)..."
mvn test

echo ""
echo "🔎 Verificando reglas críticas del starter..."

# Sin tipos SDK de AWS en api/ o domain/
RED_FLAGS=$(grep -r "software\.amazon\.awssdk\|NoSuchKeyException\|S3Presigner" \
    src/main/java/com/saywa/framework/data/s3/api/ \
    src/main/java/com/saywa/framework/data/s3/domain/ 2>/dev/null || true)
if [ -n "$RED_FLAGS" ]; then
    echo "❌ RED FLAG: Tipos del SDK de AWS en api/ o domain/:"
    echo "$RED_FLAGS"
    exit 1
fi
echo "✅ api/ y domain/ sin tipos del SDK de AWS"

# Sin bloqueos reactivos
BLOCKING=$(grep -rn "\.join()\|\.get()\|await()\.indefinitely()\|Thread\.sleep" \
    src/main/java/ 2>/dev/null || true)
if [ -n "$BLOCKING" ]; then
    echo "❌ RED FLAG: Llamadas bloqueantes:"
    echo "$BLOCKING"
    exit 1
fi
echo "✅ Sin llamadas bloqueantes"

# Sin S3Presigner.create()
PRESIGNER=$(grep -rn "S3Presigner\.create()" src/main/java/ 2>/dev/null || true)
if [ -n "$PRESIGNER" ]; then
    echo "❌ RED FLAG: S3Presigner.create() encontrado:"
    echo "$PRESIGNER"
    exit 1
fi
echo "✅ S3Presigner no se instancia manualmente"

echo ""
echo "✅ Todo OK. Starter listo."
