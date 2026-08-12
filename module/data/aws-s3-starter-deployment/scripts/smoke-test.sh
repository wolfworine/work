#!/bin/bash
# smoke-test.sh — ejecuta y verifica por curl las operaciones de
# S3StorageService a través de S3Controller (/shrd/s3), contra la app real
# corriendo sobre LocalStack (sin mocks). Es el equivalente en shell de
# S3ControllerIT.java — útil cuando no se puede correr Maven o se quiere un
# chequeo rápido tras `mvn quarkus:dev` / `docker compose --profile app up`.
#
# bucketName es obligatorio en cada llamada (ya no hay bucket implícito) —
# ver S3StorageService del starter.
#
# Uso:
#   docker compose up -d               # LocalStack arriba (ambos buckets)
#   mvn quarkus:dev &                  # o el jar/contenedor de la app
#   ./scripts/smoke-test.sh [BASE_URL]
#
# BASE_URL por defecto: http://localhost:8080
set -uo pipefail

BASE="${1:-http://localhost:8080}/shrd/s3/bucket"
BUCKET="saywa-s3-deployment-poc"
SECONDARY_BUCKET="saywa-s3-deployment-poc-secondary"
# compartamos.storage.s3.default-prefix=poc (application.properties) antepone
# "poc/" a las claves normalizadas al subir/copiar/mover (transparente para
# download/exists/delete, que reciben la clave lógica), pero NO a un
# `prefix` de listado explícito (S3RequestFactory#resolvePrefix) -- por eso
# el chequeo de `list` usa la clave ya prefijada.
KEY="smoke/hello.txt"
COPY_KEY="smoke/hello-copy.txt"
MOVED_KEY="smoke/hello-moved.txt"
SECONDARY_KEY="smoke/secondary.txt"
KEY_PREFIXED="poc/${KEY}"
SECONDARY_KEY_PREFIXED="poc/${SECONDARY_KEY}"
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

# 1. upload en memoria (multipart)
CODE=$(curl -s -o /tmp/smoke-upload.json -w "%{http_code}" \
    -F "file=@${TMP_FILE}" \
    -F "bucketName=${BUCKET}" \
    -F "objectKey=${KEY}" \
    "${BASE}")
check "upload (en memoria)" "200" "$CODE"

# 2. upload por path (server-side, lee uploads/sample.txt del deployment)
CODE=$(curl -s -o /tmp/smoke-upload-path.json -w "%{http_code}" \
    -X POST -H "Content-Type: application/json" \
    -d "{\"bucketName\":\"${BUCKET}\",\"objectKey\":\"smoke/from-path.txt\",\"objectFileToLoad\":\"sample.txt\"}" \
    "${BASE}/path")
check "upload (por path)" "200" "$CODE"

# 3. upload por path con traversal -> 400 (rechazado, no leído)
CODE=$(curl -s -o /dev/null -w "%{http_code}" \
    -X POST -H "Content-Type: application/json" \
    -d "{\"bucketName\":\"${BUCKET}\",\"objectKey\":\"smoke/should-not-exist.txt\",\"objectFileToLoad\":\"../../../../etc/passwd\"}" \
    "${BASE}/path")
check "upload por path con traversal rechazado" "400" "$CODE"

# 4. exists tras upload
CODE=$(curl -s -o /tmp/smoke-exists.txt -w "%{http_code}" "$BASE/$KEY/exists?bucketName=${BUCKET}")
check "exists (tras upload)" "200" "$CODE"
if ! grep -q "true" /tmp/smoke-exists.txt; then
    echo "❌ exists devolvió '$(cat /tmp/smoke-exists.txt)', esperaba 'true'"
    FAIL=$((FAIL + 1))
fi

# 5. list
CODE=$(curl -s -o /tmp/smoke-list.json -w "%{http_code}" "$BASE?bucketName=${BUCKET}&prefix=poc/smoke/")
check "list" "200" "$CODE"
if ! grep -q "$KEY_PREFIXED" /tmp/smoke-list.json; then
    echo "❌ list no incluyó '$KEY_PREFIXED': $(cat /tmp/smoke-list.json)"
    FAIL=$((FAIL + 1))
fi

# 6. download y verificación de contenido
CODE=$(curl -s -o "$DOWNLOAD_FILE" -w "%{http_code}" "$BASE/$KEY?bucketName=${BUCKET}")
check "download" "200" "$CODE"
if ! diff -q "$TMP_FILE" "$DOWNLOAD_FILE" >/dev/null 2>&1; then
    echo "❌ download: el contenido descargado no coincide con el subido"
    FAIL=$((FAIL + 1))
fi

# 7. download de clave inexistente -> 404
CODE=$(curl -s -o /dev/null -w "%{http_code}" "$BASE/smoke/no-existe.txt?bucketName=${BUCKET}")
check "download de clave inexistente" "404" "$CODE"

# 8. bucketName faltante -> 400 (ya no hay bucket implícito)
CODE=$(curl -s -o /dev/null -w "%{http_code}" "$BASE/$KEY/exists")
check "bucketName faltante rechazado" "400" "$CODE"

# 9. copy
CODE=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$BASE/$KEY/copy?bucketName=${BUCKET}&destinationKey=$COPY_KEY")
check "copy" "200" "$CODE"

# 10. move
CODE=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$BASE/$COPY_KEY/move?bucketName=${BUCKET}&destinationKey=$MOVED_KEY")
check "move" "200" "$CODE"

CODE=$(curl -s -o /tmp/smoke-exists-copy.txt -w "%{http_code}" "$BASE/$COPY_KEY/exists?bucketName=${BUCKET}")
if grep -q "false" /tmp/smoke-exists-copy.txt; then
    echo "✅ exists (clave origen del move ya no existe)"
    PASS=$((PASS + 1))
else
    echo "❌ exists tras move: '$COPY_KEY' debería no existir, respuesta: $(cat /tmp/smoke-exists-copy.txt)"
    FAIL=$((FAIL + 1))
fi

# 11. presigned
CODE=$(curl -s -o /tmp/smoke-presigned.json -w "%{http_code}" "$BASE/$KEY/presigned-url?bucketName=${BUCKET}&ttlSeconds=120")
check "presigned" "200" "$CODE"
if ! grep -q "http" /tmp/smoke-presigned.json; then
    echo "❌ presigned no devolvió una URL: $(cat /tmp/smoke-presigned.json)"
    FAIL=$((FAIL + 1))
fi

# 12. bucket es una elección por request, no un default implícito
CODE=$(curl -s -o /dev/null -w "%{http_code}" \
    -F "file=@${TMP_FILE}" \
    -F "bucketName=${SECONDARY_BUCKET}" \
    -F "objectKey=${SECONDARY_KEY}" \
    "${BASE}")
check "upload al bucket secundario" "200" "$CODE"

CODE=$(curl -s -o /tmp/smoke-list-secondary.json -w "%{http_code}" "$BASE?bucketName=${SECONDARY_BUCKET}&prefix=poc/smoke/")
if grep -q "$SECONDARY_KEY_PREFIXED" /tmp/smoke-list-secondary.json; then
    echo "✅ el objeto aparece listando el bucket secundario"
    PASS=$((PASS + 1))
else
    echo "❌ el objeto no aparece en el bucket secundario: $(cat /tmp/smoke-list-secondary.json)"
    FAIL=$((FAIL + 1))
fi

CODE=$(curl -s -o /tmp/smoke-list-default.json -w "%{http_code}" "$BASE?bucketName=${BUCKET}&prefix=poc/smoke/")
if grep -q "$SECONDARY_KEY_PREFIXED" /tmp/smoke-list-default.json; then
    echo "❌ el objeto del bucket secundario apareció listando el bucket por defecto (no debería)"
    FAIL=$((FAIL + 1))
else
    echo "✅ el objeto del bucket secundario NO aparece en el bucket por defecto"
    PASS=$((PASS + 1))
fi

CODE=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE "$BASE/$SECONDARY_KEY?bucketName=${SECONDARY_BUCKET}")
check "delete $SECONDARY_KEY (bucket secundario)" "204" "$CODE"

# Limpieza: delete de los objetos de prueba en el bucket por defecto
for k in "$KEY" "$MOVED_KEY" "smoke/from-path.txt"; do
    CODE=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE "$BASE/$k?bucketName=${BUCKET}")
    check "delete $k" "204" "$CODE"
done

rm -f /tmp/smoke-upload.json /tmp/smoke-upload-path.json /tmp/smoke-exists.txt /tmp/smoke-list.json \
      /tmp/smoke-exists-copy.txt /tmp/smoke-presigned.json /tmp/smoke-list-secondary.json /tmp/smoke-list-default.json

echo ""
echo "== Resultado: $PASS pasaron, $FAIL fallaron =="
[ "$FAIL" -eq 0 ]
