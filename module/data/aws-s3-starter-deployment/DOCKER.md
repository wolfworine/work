# Docker — aws-s3-starter-deployment

## Solo LocalStack (modo desarrollo habitual)

```bash
docker compose up -d
docker compose logs -f localstack   # esperar "Ready."
mvn quarkus:dev                     # la app corre en el host, apunta a localhost:4566
```

## LocalStack + app, todo en contenedores

```bash
mvn -DskipTests package
docker compose --profile app up --build
```

La app se conecta a LocalStack por la red interna de Compose
(`http://localstack:4566`, ver `QUARKUS_S3_ENDPOINT_OVERRIDE` en
`docker-compose.yml`), mientras que desde el host sigue expuesta en
`http://localhost:8080`.

## Apagar y limpiar

```bash
docker compose --profile app down     # baja app + localstack
docker compose down -v                # además borra el volumen de datos de LocalStack
```

## Troubleshooting

| Síntoma | Causa probable | Solución |
|---|---|---|
| `mvn compile` falla resolviendo `aws-s3-starter` | El starter no está en `.m2` | `cd <starter> && mvn install -DskipTests` |
| `curl` a la app da `S3ConfigurationException` / timeout de conexión | LocalStack no está healthy todavía | `docker compose ps`, esperar `healthy` |
| `NoSuchBucket` al hacer upload | El script `localstack-init/01-create-bucket.sh` no corrió | `docker compose down -v && docker compose up -d` para forzar re-init |
| `SdkClientException: Unable to execute HTTP request` | `path-style-access` no está en `true`, o el endpoint apunta a `localstack` desde el host en vez de `localhost` | Revisar `quarkus.s3.path-style-access=true` y qué hostname usa el perfil activo |
