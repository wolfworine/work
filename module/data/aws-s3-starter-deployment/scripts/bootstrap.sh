#!/bin/bash
# bootstrap.sh — deja el entorno listo y corre la verificación end-to-end
# completa de aws-s3-starter-deployment. Pensado para que Claude Code (u
# otro agente) lo ejecute de punta a punta en una máquina con Docker y
# Maven reales.
#
# Uso:
#   ./scripts/bootstrap.sh /ruta/al/repo/saywa
#
# El argumento es la raíz del repo que contiene modules/data/aws-s3-starter
# (o, si ya tienes el jar en .m2, puedes omitirlo y el script solo valida).
set -euo pipefail

STARTER_REPO="${1:-}"
BUCKET_NAME="saywa-s3-deployment-poc"
STARTER_ARTIFACT_ID="aws-s3-starter"
STARTER_VERSION="1.1.0-SNAPSHOT"
STARTER_M2="$HOME/.m2/repository/com/saywa/framework/${STARTER_ARTIFACT_ID}/${STARTER_VERSION}/${STARTER_ARTIFACT_ID}-${STARTER_VERSION}.jar"

echo "== 1/6 Prerrequisitos =="
command -v docker >/dev/null || { echo "❌ docker no está en PATH"; exit 1; }
command -v mvn    >/dev/null || { echo "❌ mvn no está en PATH"; exit 1; }
JAVA_MAJOR=$(java -version 2>&1 | head -1 | grep -oE '"[0-9]+' | tr -d '"')
if [ "${JAVA_MAJOR:-0}" -lt 21 ]; then
    echo "❌ Se requiere Java 21+ (detectado: ${JAVA_MAJOR:-desconocido})"
    exit 1
fi
echo "✅ docker, mvn, Java $JAVA_MAJOR OK"

echo ""
echo "== 2/6 Instalar ${STARTER_ARTIFACT_ID} en .m2 (si falta) =="
if [ -f "$STARTER_M2" ]; then
    echo "✅ ya está instalado: $STARTER_M2"
elif [ -n "$STARTER_REPO" ]; then
    STARTER_MODULE="$STARTER_REPO/module/data/${STARTER_ARTIFACT_ID}"
    [ -d "$STARTER_MODULE" ] || STARTER_MODULE="$STARTER_REPO/modules/data/${STARTER_ARTIFACT_ID}"
    if [ ! -d "$STARTER_MODULE" ]; then
        echo "❌ No encontré ${STARTER_ARTIFACT_ID} bajo $STARTER_REPO/module(s)/data/${STARTER_ARTIFACT_ID}"
        exit 1
    fi
    echo "→ Instalando parent BOM..."
    mvn -q -f "$STARTER_REPO/pom.xml" install -pl module/parent -am -DskipTests || \
    mvn -q -f "$STARTER_REPO/pom.xml" install -pl modules/parent -am -DskipTests
    echo "→ Instalando ${STARTER_ARTIFACT_ID}..."
    mvn -q -f "$STARTER_MODULE/pom.xml" install -DskipTests
    echo "✅ instalado"
else
    echo "❌ ${STARTER_ARTIFACT_ID} no está en .m2 y no pasaste la ruta del repo del starter."
    echo "   Uso: ./scripts/bootstrap.sh /ruta/al/repo/saywa"
    exit 1
fi

echo ""
echo "== 3/6 Levantar LocalStack =="
docker compose up -d
echo "→ Esperando healthcheck..."
for i in $(seq 1 30); do
    if docker compose ps --format '{{.Health}}' localstack 2>/dev/null | grep -q healthy; then
        echo "✅ LocalStack healthy"
        break
    fi
    sleep 2
    if [ "$i" -eq 30 ]; then
        echo "❌ LocalStack no llegó a healthy en 60s"
        docker compose logs localstack | tail -40
        exit 1
    fi
done

echo ""
echo "== 4/6 Verificar bucket =="
if curl -sf "http://localhost:4566/${BUCKET_NAME}" >/dev/null 2>&1 || \
   curl -s "http://localhost:4566/_localstack/health" | grep -q '"s3": *"available"' ; then
    echo "✅ S3 disponible en LocalStack"
else
    echo "⚠️  No pude confirmar el bucket por curl; revisa docker compose logs localstack"
fi

echo ""
echo "== 5/6 Compilar app deployment =="
mvn -q compile
echo "✅ compila"

echo ""
echo "== 6/6 Test de integración end-to-end (sin mocks, contra LocalStack real) =="
mvn verify -Pintegration-tests

echo ""
echo "🎉 Verificación completa OK. Marca la feature 5 como 'done' en feature_list.json"
echo "   y mueve la entrada de progress/current.md a progress/history.md."
