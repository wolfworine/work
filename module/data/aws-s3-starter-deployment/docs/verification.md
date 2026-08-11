# Verificación — aws-s3-starter-deployment

## 1. Levantar el entorno

```bash
# 1. Instalar el starter en .m2 (una sola vez, desde el repo del starter)
#    cd modules/data/aws-s3-starter && mvn install -DskipTests

# 2. Levantar LocalStack
docker compose up -d
docker compose logs -f localstack   # esperar "Ready."

# 3. Verificar salud y bucket
curl -s http://localhost:4566/_localstack/health | grep -o '"s3": *"[a-z]*"'
curl -s http://localhost:4566/saywa-s3-deployment-poc

# 4. Levantar la app
mvn quarkus:dev
```

## 2. Probar cada operación por curl

```bash
BASE=http://localhost:8080/s3/objects

# upload (multipart)
curl -s -F "file=@README.md;type=text/markdown" "$BASE?objectKey=docs/readme.md"

# list
curl -s "$BASE?prefix=docs/"

# download
curl -s "$BASE/docs/readme.md" -o /tmp/readme-descargado.md

# exists
curl -s "$BASE/docs/readme.md/exists"

# copy
curl -s -X POST "$BASE/docs/readme.md/copy?destinationKey=docs/readme-copia.md"

# move
curl -s -X POST "$BASE/docs/readme-copia.md/move?destinationKey=docs/readme-movido.md"

# presigned
curl -s "$BASE/docs/readme.md/presigned?ttlSeconds=300"

# delete
curl -s -X DELETE -o /dev/null -w "%{http_code}\n" "$BASE/docs/readme-movido.md"
```

Todos deben responder sin error 500. Un 404 en `download`/`copy`/`presigned`
sobre una clave inexistente es correcto (mapeo de
`StorageObjectNotFoundException`).

## 3. Smoke test automatizado por curl (sin Maven)

Equivalente en shell del test de integración: ejecuta y verifica las 8
operaciones contra la app real, e imprime PASS/FAIL con código de salida
distinto de 0 si algo falla.

```bash
docker compose up -d
mvn quarkus:dev &      # o: java -Dquarkus.profile=dev -jar target/quarkus-app/quarkus-run.jar &
./scripts/smoke-test.sh
```

## 4. OpenAPI / Postman — alternativa al LocalStack Web App

El [LocalStack Web App](https://app.localstack.cloud) (resource browser con
UI) requiere cuenta y `LOCALSTACK_AUTH_TOKEN`. Si no tienes eso configurado,
la app expone su propia documentación OpenAPI, que se puede importar
directo en Postman para probar los 8 endpoints con datos de prueba:

```bash
curl -s http://localhost:8080/q/openapi -H "Accept: application/json" -o openapi.json
```

En Postman: **File → Import → openapi.json** (o pega la URL
`http://localhost:8080/q/openapi` en **Link**) genera una colección con los
8 endpoints, parámetros y schemas de request/response.

También hay una UI interactiva en `http://localhost:8080/q/swagger-ui/`
para probar los endpoints a mano sin salir del navegador.

## 5. Test de integración automatizado

```bash
mvn verify -Pintegration-tests
```

Requiere LocalStack corriendo (`docker compose up -d`) y el bucket creado.

## 6. Checklist de cierre de sesión

- [ ] `docker compose ps` muestra `localstack` healthy
- [ ] Los 8 curl de la sección 2 se ejecutaron y respondieron lo esperado
- [ ] `./scripts/smoke-test.sh` termina en verde (o `mvn verify -Pintegration-tests`)
- [ ] `docker compose down` no deja contenedores huérfanos
