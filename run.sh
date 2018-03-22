#!/bin/sh
set -ex

# build frontend fe docker image
(cd frontend \
     && docker build -t fe .)

# extract assets
./dist-fe.sh && ls -al dist-fe

# build backend be docker image
(cd backend/backend \
        && docker build -t be .)

# extract assets
./dist-be.sh && ls -al be.jar

# run all
docker-compose up
