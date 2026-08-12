#!/bin/bash
# Se ejecuta automáticamente dentro del contenedor LocalStack cuando el
# servicio S3 está listo (montado en /etc/localstack/init/ready.d/).
# Crea el bucket por defecto que usa aws-s3-starter-deployment (vía
# compartamos.storage.s3.bucket-name) y un segundo bucket, usado solo para
# probar el bucketName explícito por request (S3ResourceIT / smoke-test.sh),
# ya que ahora cada llamada al starter indica su propio bucket.

set -euo pipefail

BUCKET_NAME="${BUCKET_NAME:-saywa-s3-deployment-poc}"
SECONDARY_BUCKET_NAME="${SECONDARY_BUCKET_NAME:-saywa-s3-deployment-poc-secondary}"

create_bucket_if_missing() {
    local bucket="$1"
    if awslocal s3api head-bucket --bucket "${bucket}" 2>/dev/null; then
        echo "[init] El bucket '${bucket}' ya existe."
    else
        awslocal s3api create-bucket --bucket "${bucket}" --region us-east-1
        echo "[init] Bucket '${bucket}' creado."
    fi
}

echo "[init] Creando buckets en LocalStack..."
create_bucket_if_missing "${BUCKET_NAME}"
create_bucket_if_missing "${SECONDARY_BUCKET_NAME}"

awslocal s3api list-buckets --query "Buckets[].Name" --output text
