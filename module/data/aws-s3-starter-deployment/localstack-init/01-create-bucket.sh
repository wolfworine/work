#!/bin/bash
# Se ejecuta automáticamente dentro del contenedor LocalStack cuando el
# servicio S3 está listo (montado en /etc/localstack/init/ready.d/).
# Crea el bucket que consume aws-s3-starter-deployment vía
# compartamos.storage.s3.bucket-name.

set -euo pipefail

BUCKET_NAME="${BUCKET_NAME:-saywa-s3-deployment-poc}"

echo "[init] Creando bucket '${BUCKET_NAME}' en LocalStack..."

if awslocal s3api head-bucket --bucket "${BUCKET_NAME}" 2>/dev/null; then
    echo "[init] El bucket '${BUCKET_NAME}' ya existe."
else
    awslocal s3api create-bucket \
        --bucket "${BUCKET_NAME}" \
        --region us-east-1
    echo "[init] Bucket '${BUCKET_NAME}' creado."
fi

awslocal s3api list-buckets --query "Buckets[].Name" --output text
