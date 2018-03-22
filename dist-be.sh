#!/bin/sh

IMAGE="be"
SRC="/build/target/be.jar"
OUT="be.jar"

docker run --name temp-container-name $IMAGE /bin/true
docker cp temp-container-name:$SRC $OUT
docker rm temp-container-name
