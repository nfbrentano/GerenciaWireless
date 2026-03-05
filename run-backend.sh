#!/bin/bash

# Abortar em caso de erro
set -e

TOMCAT_VERSION="9.0.87"
TOMCAT_DIR="apache-tomcat-$TOMCAT_VERSION"

echo "=========================================="
echo "🔧 PREPARANDO AMBIENTE BACKEND JAVA"
echo "=========================================="

# 0. Parar Tomcat se estiver rodando
if [ -d "$TOMCAT_DIR" ] && [ -f "$TOMCAT_DIR/bin/shutdown.sh" ]; then
    echo "⏹️  Parando Tomcat (se estiver rodando)..."
    bash "$TOMCAT_DIR/bin/shutdown.sh" || true
    sleep 2
fi

# 1. Baixar Tomcat 9 (compatível com javax.servlet)
if [ ! -d "$TOMCAT_DIR" ]; then
    echo "⬇️  Baixando Servidor Apache Tomcat $TOMCAT_VERSION..."
    curl -s -O "https://archive.apache.org/dist/tomcat/tomcat-9/v$TOMCAT_VERSION/bin/$TOMCAT_DIR.tar.gz"
    echo "📦 Extraindo Tomcat..."
    tar -xzf "$TOMCAT_DIR.tar.gz"
    rm "$TOMCAT_DIR.tar.gz"
else
    echo "✅ Tomcat já está baixado."
fi

# 2. Configurar pasta de build
echo "🔨 Limpando e preparando pastas de compilação..."
rm -rf build_out
mkdir -p build_out/WEB-INF/classes
mkdir -p build_out/WEB-INF/lib

# 3. Copiar bibliotecas (libs) do projeto
echo "📚 Copiando dependências (.jar)..."
cp lib/*.jar build_out/WEB-INF/lib/

# 4. Compilar os arquivos Java e copiar recursos
echo "⚙️  Compilando arquivos .java..."
# Usamos o Tomcat lib para achar o servlet-api.jar e as nossas libs
javac -encoding UTF-8 \
      -cp "$TOMCAT_DIR/lib/*:build_out/WEB-INF/lib/*" \
      -d build_out/WEB-INF/classes \
      $(find src/java -name "*.java")

echo "📂 Copiando recursos (...properties)..."
find src/java -name "*.properties" -exec cp {} build_out/WEB-INF/classes/ \;

# 5. Copiar arquivos Web (Filtros extras, web.xml)
echo "📂 Copiando configurações WEB-INF..."
cp -r web/* build_out/

# 6. Publicar no Tomcat (na rota /GerenciaWireless)
echo "🚀 Publicando aplicação no servidor..."
rm -rf "$TOMCAT_DIR/webapps/GerenciaWireless*"
cp -r build_out "$TOMCAT_DIR/webapps/GerenciaWireless"

# 7. Iniciar o Tomcat
echo "▶️  Iniciando Tomcat..."
chmod +x $TOMCAT_DIR/bin/*.sh
$TOMCAT_DIR/bin/startup.sh

echo "=========================================="
echo "✅ DEPLOY CONCLUÍDO!"
echo "🌐 Backend rodando em: http://localhost:8080/GerenciaWireless"
echo "=========================================="
echo "📝 Para ver os logs no futuro, digite:"
echo "   tail -f $TOMCAT_DIR/logs/catalina.out"
