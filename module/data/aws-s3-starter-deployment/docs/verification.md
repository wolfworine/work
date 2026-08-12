# Verificación — aws-s3-starter-deployment

## 1. Levantar el entorno

```bash
# 1. Instalar el starter en .m2 (una sola vez, desde el repo del starter)
#    cd modules/data/aws-s3-starter && mvn install -DskipTests

# 2. Levantar LocalStack (crea ambos buckets: el por defecto y el secundario
#    usado por las pruebas de bucket-por-request)
docker compose up -d
docker compose logs -f localstack   # esperar "Ready."

# 3. Verificar salud y buckets
curl -s http://localhost:4566/_localstack/health | grep -o '"s3": *"[a-z]*"'
curl -s http://localhost:4566/saywa-s3-deployment-poc
curl -s http://localhost:4566/saywa-s3-deployment-poc-secondary

# 4. Levantar la app
mvn quarkus:dev

# 5. Verificar que la app está lista
curl -s http://localhost:8080/q/health
```

## 2. Probar cada operación por curl

`bucketName` es obligatorio en las 9 — si falta, 400 con
`SaywaErrorResponse` (`ConstraintViolationException`).

```bash
BASE=http://localhost:8080/shrd/s3/bucket
BUCKET=saywa-s3-deployment-poc

# upload en memoria (multipart)
curl -s -F "file=@README.md;type=text/markdown" -F "bucketName=$BUCKET" -F "objectKey=docs/readme.md" "$BASE"

# upload por path (lee un archivo del deployment.s3.upload-base-dir del servidor,
# por defecto uploads/ — ver uploads/sample.txt)
curl -s -X POST -H "Content-Type: application/json" \
  -d "{\"bucketName\":\"$BUCKET\",\"objectKey\":\"docs/sample.txt\",\"objectFileToLoad\":\"sample.txt\"}" \
  "$BASE/path"

# upload por path con traversal -> debe rechazar con 400, no leer el archivo
curl -s -o /dev/null -w "%{http_code}\n" -X POST -H "Content-Type: application/json" \
  -d "{\"bucketName\":\"$BUCKET\",\"objectKey\":\"x.txt\",\"objectFileToLoad\":\"../../../../etc/passwd\"}" \
  "$BASE/path"

# list
curl -s "$BASE?bucketName=$BUCKET&prefix=docs/"

# download
curl -s "$BASE/docs/readme.md?bucketName=$BUCKET" -o /tmp/readme-descargado.md

# exists
curl -s "$BASE/docs/readme.md/exists?bucketName=$BUCKET"

# copy
curl -s -X POST "$BASE/docs/readme.md/copy?bucketName=$BUCKET&destinationKey=docs/readme-copia.md"

# move
curl -s -X POST "$BASE/docs/readme-copia.md/move?bucketName=$BUCKET&destinationKey=docs/readme-movido.md"

# presigned
curl -s "$BASE/docs/readme.md/presigned-url?bucketName=$BUCKET&ttlSeconds=300"

# bucketName faltante -> 400, no un 500 ni un bucket implícito
curl -s -o /dev/null -w "%{http_code}\n" "$BASE/docs/readme.md/exists"

# delete
curl -s -X DELETE -o /dev/null -w "%{http_code}\n" "$BASE/docs/readme-movido.md?bucketName=$BUCKET"
```

Todos deben responder sin error 500. Un 404 en `download`/`copy`/`presigned`
sobre una clave inexistente es correcto (mapeo de
`StorageObjectNotFoundException`); un 400 en el caso de traversal y de
`bucketName` faltante también es correcto.

## 3. Smoke test automatizado por curl (sin Maven)

Equivalente en shell del test de integración: ejecuta y verifica las
operaciones (incluidos ambos modos de upload y el aislamiento entre
buckets) contra la app real, e imprime PASS/FAIL con código de salida
distinto de 0 si algo falla.

```bash
docker compose up -d
mvn quarkus:dev &      # o: java -jar target/quarkus-app/quarkus-run.jar &
./scripts/smoke-test.sh
```

## 4. OpenAPI / Postman — alternativa al LocalStack Web App

El [LocalStack Web App](https://app.localstack.cloud) (resource browser con
UI) requiere cuenta y `LOCALSTACK_AUTH_TOKEN`. Si no tienes eso configurado,
la app expone su propia documentación OpenAPI, que se puede importar
directo en Postman:

```bash
curl -s http://localhost:8080/q/openapi -H "Accept: application/json" -o openapi.json
```

En Postman: **File → Import → openapi.json** (o pega la URL
`http://localhost:8080/q/openapi` en **Link**) genera una colección con los
endpoints, parámetros y schemas de request/response.

También hay una UI interactiva en `http://localhost:8080/q/swagger-ui/`
para probar los endpoints a mano sin salir del navegador.

## 5. Test de integración automatizado

```bash
mvn verify -Pintegration-tests
```

Requiere LocalStack corriendo (`docker compose up -d`) y ambos buckets
creados. Cubre unit tests (Mockito, `S3Facade`/`S3Service`/`S3ExceptionMappers`/
`S3UploadPathResolver`) + integración real contra LocalStack
(`S3ControllerIT`).

## 6. Checklist de cierre de sesión

- [ ] `docker compose ps` muestra `localstack` healthy
- [ ] `curl http://localhost:8080/q/health` responde `UP`
- [ ] Los curl de la sección 2 se ejecutaron y respondieron lo esperado
- [ ] `./scripts/smoke-test.sh` termina en verde (o `mvn verify -Pintegration-tests`)
- [ ] `docker compose down` no deja contenedores huérfanos
