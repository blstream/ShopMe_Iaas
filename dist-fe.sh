#!/bin/sh

IMAGE="fe"
SRC="/build/build/"
OUT="dist-fe"

docker run --name temp-container-name $IMAGE /bin/true
docker cp temp-container-name:$SRC $OUT
docker rm temp-container-name
