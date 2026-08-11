#!/bin/bash
set -e

echo "🔄 Reseteando harness-saywa-s3-starter a estado original..."
echo ""
echo "⚠️  ADVERTENCIA: Esto eliminará todas las personalizaciones del pom.xml"
read -p "¿Continuar? (y/n): " CONFIRM

if [ "$CONFIRM" != "y" ]; then
    echo "❌ Cancelado"
    exit 1
fi

echo ""
echo "🔄 Restaurando archivos originales..."

# Restaurar con git si es posible
git checkout pom.xml 2>/dev/null        && echo "  ✅ pom.xml restaurado (git)"     || echo "  ⚠️  pom.xml no rastreado por git"
git checkout feature_list.json 2>/dev/null && echo "  ✅ feature_list.json restaurado" || echo "  ⚠️  feature_list.json no rastreado"
git checkout README.md 2>/dev/null      && echo "  ✅ README.md restaurado"          || echo "  ⚠️  README.md no rastreado"

# Restaurar .template-guard para volver a bloquear init.sh
cat > .template-guard << 'GUARD'
#!/bin/bash

echo "⚠️  ESTE ES UN TEMPLATE PARAMETRIZABLE"
echo ""
echo "Antes de ejecutar init.sh, debes:"
echo "  1. Ejecutar ./template-init.sh"
echo "  2. Configurar tu módulo (parentVersion, artifactId, descripción, etc.)"
echo ""
echo "Consulta TEMPLATE.md para más información."
echo ""
exit 1
GUARD

echo "  ✅ .template-guard restaurado (init.sh bloqueado)"

# Limpiar archivos Java generados (mantener solo .gitkeep)
echo ""
echo "🗑️  Limpiando código generado..."
find src/main/java -name "*.java" -not -name ".gitkeep" -delete 2>/dev/null && \
    echo "  ✅ Archivos .java eliminados" || true
find src/test/java -name "*.java" -not -name ".gitkeep" -delete 2>/dev/null && \
    echo "  ✅ Tests .java eliminados" || true

# Limpiar progress
echo "" > progress/current.md
echo "  ✅ progress/current.md vaciado"

echo ""
echo "✅ Reset completado"
echo ""
echo "Para volver a inicializar:"
echo "  ./template-init.sh"
