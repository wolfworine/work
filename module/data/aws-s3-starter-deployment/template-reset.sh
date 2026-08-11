#!/bin/bash
set -e

echo "🔄 Reseteando harness-aws-s3-starter-deployment a estado original..."
echo ""
echo "⚠️  ADVERTENCIA: Esto eliminará las personalizaciones de pom.xml, docker-compose.yml,"
echo "    feature_list.json, README.md, init.sh, scripts/bootstrap.sh, localstack-init/ y"
echo "    application.properties, y borrará el código Java generado por sesiones del harness."
read -p "¿Continuar? (y/n): " CONFIRM

if [ "$CONFIRM" != "y" ]; then
    echo "❌ Cancelado"
    exit 1
fi

echo ""
echo "🔄 Restaurando archivos originales..."

FILES_WITH_PLACEHOLDERS="pom.xml feature_list.json README.md docker-compose.yml init.sh scripts/bootstrap.sh localstack-init/01-create-bucket.sh src/main/resources/application.properties"
for f in $FILES_WITH_PLACEHOLDERS; do
    git checkout -- "$f" 2>/dev/null && echo "  ✅ $f restaurado (git)" || echo "  ⚠️  $f no rastreado por git (déjalo o restáuralo manualmente)"
done

# Restaurar .template-guard para volver a bloquear init.sh / bootstrap.sh
cat > .template-guard << 'GUARD'
#!/bin/bash

echo "⚠️  ESTE ES UN TEMPLATE PARAMETRIZABLE"
echo ""
echo "Antes de ejecutar init.sh o scripts/bootstrap.sh, debes:"
echo "  1. Ejecutar ./template-init.sh"
echo "  2. Configurar tu proyecto (artifact id del deployment, starter a consumir, bucket, etc.)"
echo ""
echo "Consulta TEMPLATE.md para más información."
echo ""
exit 1
GUARD

echo "  ✅ .template-guard restaurado (init.sh / bootstrap.sh bloqueados)"

echo ""
echo "🗑️  Limpiando código Java (queda a cargo de una nueva sesión del harness"
echo "    regenerarlo vía feature_list.json — útil si vas a apuntar este"
echo "    deployment a un starter con una API distinta)..."
find src/main/java -name "*.java" -delete 2>/dev/null || true
find src/test/java/integration -name "*.java" -delete 2>/dev/null || true

echo ""
echo "📝 Vaciando progress/current.md..."
echo "" > progress/current.md
echo "  ✅ progress/current.md vaciado"

echo ""
echo "✅ Reset completado"
echo ""
echo "Para volver a inicializar:"
echo "  ./template-init.sh"
