#!/bin/sh

(cd frontend \
     && git pull -p)
(cd backend \
     && git pull -p)

docker-compose down \
    && docker-compose build
