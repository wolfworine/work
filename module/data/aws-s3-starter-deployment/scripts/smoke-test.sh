#!/bin/bash
# smoke-test.sh — ejecuta y verifica por curl las 8 operaciones de
# S3StorageService a través de S3Resource, contra la app real corriendo
# sobre LocalStack (sin mocks). Es el equivalente en shell de
# S3ResourceIT.java — útil cuando no se puede correr Maven o se quiere un
# chequeo rápido tras `mvn quarkus:dev` / `docker compose --profile app up`.
#
# Uso:
#   docker compose up -d               # LocalStack arriba
#   mvn quarkus:dev &                  # o el jar/contenedor de la app
#   ./scripts/smoke-test.sh [BASE_URL]
#
# BASE_URL por defecto: http://localhost:8080
set -uo pipefail

BASE="${1:-http://localhost:8080}/s3/objects"
# compartamos.storage.s3.default-prefix=poc (application.properties) antepone
# "poc/" a las claves normalizadas al subir/copiar/mover (transparente para
# download/exists/delete, que reciben la clave lógica), pero NO a un
# `prefix` de listado explícito (S3RequestFactory#resolvePrefix) -- por eso
# el chequeo de `list` usa la clave ya prefijada.
KEY="smoke/hello.txt"
COPY_KEY="smoke/hello-copy.txt"
MOVED_KEY="smoke/hello-moved.txt"
KEY_PREFIXED="poc/${KEY}"
CONTENT="smoke test $(date +%s)"
TMP_FILE=$(mktemp)
DOWNLOAD_FILE=$(mktemp)
echo -n "$CONTENT" > "$TMP_FILE"
trap 'rm -f "$TMP_FILE" "$DOWNLOAD_FILE"' EXIT

PASS=0
FAIL=0

check() {
    local desc="$1" expected="$2" actual="$3"
    if [ "$expected" = "$actual" ]; then
        echo "✅ $desc (HTTP $actual)"
        PASS=$((PASS + 1))
    else
        echo "❌ $desc — esperado HTTP $expected, recibido $actual"
        FAIL=$((FAIL + 1))
    fi
}

echo "== Smoke test contra $BASE =="
echo ""

# 1. upload
CODE=$(curl -s -o /tmp/smoke-upload.json -w "%{http_code}" \
    -F "file=@${TMP_FILE}" \
    "${BASE}?objectKey=${KEY}")
check "upload" "200" "$CODE"

# 2. exists tras upload
CODE=$(curl -s -o /tmp/smoke-exists.txt -w "%{http_code}" "$BASE/$KEY/exists")
check "exists (tras upload)" "200" "$CODE"
if ! grep -q "true" /tmp/smoke-exists.txt; then
    echo "❌ exists devolvió '$(cat /tmp/smoke-exists.txt)', esperaba 'true'"
    FAIL=$((FAIL + 1))
fi

# 3. list
CODE=$(curl -s -o /tmp/smoke-list.json -w "%{http_code}" "$BASE?prefix=poc/smoke/")
check "list" "200" "$CODE"
if ! grep -q "$KEY_PREFIXED" /tmp/smoke-list.json; then
    echo "❌ list no incluyó '$KEY_PREFIXED': $(cat /tmp/smoke-list.json)"
    FAIL=$((FAIL + 1))
fi

# 4. download y verificación de contenido
CODE=$(curl -s -o "$DOWNLOAD_FILE" -w "%{http_code}" "$BASE/$KEY")
check "download" "200" "$CODE"
if ! diff -q "$TMP_FILE" "$DOWNLOAD_FILE" >/dev/null 2>&1; then
    echo "❌ download: el contenido descargado no coincide con el subido"
    FAIL=$((FAIL + 1))
fi

# 5. download de clave inexistente -> 404
CODE=$(curl -s -o /dev/null -w "%{http_code}" "$BASE/smoke/no-existe.txt")
check "download de clave inexistente" "404" "$CODE"

# 6. copy
CODE=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$BASE/$KEY/copy?destinationKey=$COPY_KEY")
check "copy" "200" "$CODE"

# 7. move
CODE=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$BASE/$COPY_KEY/move?destinationKey=$MOVED_KEY")
check "move" "200" "$CODE"

CODE=$(curl -s -o /tmp/smoke-exists-copy.txt -w "%{http_code}" "$BASE/$COPY_KEY/exists")
if grep -q "false" /tmp/smoke-exists-copy.txt; then
    echo "✅ exists (clave origen del move ya no existe)"
    PASS=$((PASS + 1))
else
    echo "❌ exists tras move: '$COPY_KEY' debería no existir, respuesta: $(cat /tmp/smoke-exists-copy.txt)"
    FAIL=$((FAIL + 1))
fi

# 8. presigned
CODE=$(curl -s -o /tmp/smoke-presigned.txt -w "%{http_code}" "$BASE/$KEY/presigned?ttlSeconds=120")
check "presigned" "200" "$CODE"
if ! grep -q "http" /tmp/smoke-presigned.txt; then
    echo "❌ presigned no devolvió una URL: $(cat /tmp/smoke-presigned.txt)"
    FAIL=$((FAIL + 1))
fi

# Limpieza: delete de los objetos de prueba
for k in "$KEY" "$MOVED_KEY"; do
    CODE=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE "$BASE/$k")
    check "delete $k" "204" "$CODE"
done

rm -f /tmp/smoke-upload.json /tmp/smoke-exists.txt /tmp/smoke-list.json /tmp/smoke-exists-copy.txt /tmp/smoke-presigned.txt

echo ""
echo "== Resultado: $PASS pasaron, $FAIL fallaron =="
[ "$FAIL" -eq 0 ]
