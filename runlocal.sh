#!/bin/bash
# Script para execução em ambiente LOCAL
# Parametros possiveis:
# -b : true ou false (ex: sh runlocal.sh -b false). default = true
# O parâmetro -b indica se é necessário compilar a aplicação ou não

CONTAINER_NAME="quarkus-clean-arch"

while getopts p:b: flag
do
    case "${flag}" in
        b) build=${OPTARG};;
    esac
done

if [[ -z "$build" || "$build" != "false" ]]
then
    build=true
fi

echo "Build $build"

if [ "$build" == "true" ]
then
    echo "---- Iniciando compilação do projeto ---- "
    ./gradlew clean build
fi

result=$?
if [ $result -ne 0 ] ; then
  echo Could not perform gradle clean build, exit code [$result]; exit $result
fi

echo "---- Parando o container docker ----"
docker stop $CONTAINER_NAME

echo "---- Deletando a imagem docker ----"
docker rmi $CONTAINER_NAME

echo "---- Criando o container docker ----"
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-clean-arch .
docker run -i --name quarkus-clean-arch --rm -p 8080:8080 quarkus-clean-arch