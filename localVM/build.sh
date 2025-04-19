#!/bin/bash


if ! docker network inspect wordgame > /dev/null 2>&1; then
  docker network create wordgame
else
  echo "Network 'wordgame' already exists."
fi

docker run -d --network wordgame --name localhost redis
docker build  -t bot ..
