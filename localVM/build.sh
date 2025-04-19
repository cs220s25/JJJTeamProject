#!/bin/bash

docker network create wordgame
docker run -d --network wordgame --name redisdb redis
docker build -t bot .

if ! docker network inspect wordgame > /dev/null 2>&1; then
  docker network create wordgame
else
  echo "Network 'wordgame' already exists."
fi
