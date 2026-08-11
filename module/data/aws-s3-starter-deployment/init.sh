#!/bin/bash
set -e

echo "🔍 Verificando entorno — harness-aws-s3-starter-deployment"
echo ""

# ── Java 21 ──────────────────────────────────────────────────────────────
if ! command -v java &> /dev/null; then
    echo "❌ Java no encontrado en PATH."
    exit 1
fi
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
echo "✅ Java $JAVA_VERSION"

# ── Maven ────────────────────────────────────────────────────────────────
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven no encontrado en PATH."
    exit 1
fi
echo "✅ Maven $(mvn -v 2>/dev/null | head -n 1 | cut -d' ' -f3)"

# ── Docker ───────────────────────────────────────────────────────────────
if ! command -v docker &> /dev/null; then
    echo "❌ Docker no encontrado en PATH (necesario para LocalStack)."
    exit 1
fi
echo "✅ Docker $(docker --version 2>/dev/null | cut -d' ' -f3 | tr -d ',')"
echo ""

# ── Starter en .m2 ───────────────────────────────────────────────────────
STARTER_ARTIFACT_ID="aws-s3-starter"
STARTER_VERSION="1.1.0-SNAPSHOT"
STARTER_IN_M2="$HOME/.m2/repository/com/saywa/framework/${STARTER_ARTIFACT_ID}/${STARTER_VERSION}/${STARTER_ARTIFACT_ID}-${STARTER_VERSION}.jar"
if [ ! -f "$STARTER_IN_M2" ]; then
    echo "⚠️  ${STARTER_ARTIFACT_ID} NO está instalado en .m2 local."
    echo "    Instálalo desde el repo del starter:"
    echo "    cd <ruta-al-starter>/modules/data/${STARTER_ARTIFACT_ID} && mvn install -DskipTests"
    echo "    (o mvn install -pl module/data/${STARTER_ARTIFACT_ID} -am -DskipTests desde la raíz del reactor)"
    exit 1
fi
echo "✅ ${STARTER_ARTIFACT_ID}-${STARTER_VERSION}.jar en .m2 local"
echo ""

# ── LocalStack ───────────────────────────────────────────────────────────
if command -v docker compose &> /dev/null || docker compose version &> /dev/null 2>&1; then
    if docker compose ps --status running 2>/dev/null | grep -q localstack; then
        echo "✅ LocalStack corriendo"
    else
        echo "⚠️  LocalStack no está corriendo. Levántalo con: docker compose up -d"
    fi
fi
echo ""

# ── Compilar ─────────────────────────────────────────────────────────────
echo "🔨 Compilando (mvn compile)..."
mvn compile -q

echo ""
echo "🔎 Verificando reglas críticas del deployment..."

# Sin S3Client/S3AsyncClient directo en este módulo
SDK_DIRECT=$(grep -rn "software\.amazon\.awssdk\.services\.s3\.S3AsyncClient\|software\.amazon\.awssdk\.services\.s3\.S3Client" \
    src/main/java/ 2>/dev/null || true)
if [ -n "$SDK_DIRECT" ]; then
    echo "❌ RED FLAG: uso directo de S3Client/S3AsyncClient (debe ir vía S3StorageService del starter):"
    echo "$SDK_DIRECT"
    exit 1
fi
echo "✅ Sin S3Client/S3AsyncClient directo"

# Sin bloqueos reactivos
BLOCKING=$(grep -rn "\.join()\|\.get()\|await()\.indefinitely()\|Thread\.sleep" \
    src/main/java/ 2>/dev/null || true)
if [ -n "$BLOCKING" ]; then
    echo "❌ RED FLAG: Llamadas bloqueantes en el flujo de request:"
    echo "$BLOCKING"
    exit 1
fi
echo "✅ Sin llamadas bloqueantes"

# Sin credenciales reales en dev/test
REAL_CREDS=$(grep -n "AKIA[0-9A-Z]\{16\}" src/main/resources/application.properties 2>/dev/null || true)
if [ -n "$REAL_CREDS" ]; then
    echo "❌ RED FLAG: posible credencial AWS real en application.properties"
    exit 1
fi
echo "✅ Sin credenciales AWS reales en config"

echo ""
echo "✅ Todo OK. Deployment listo (recuerda: verificación completa = curl/tests contra LocalStack real, ver docs/verification.md)."
