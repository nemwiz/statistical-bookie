#!/usr/bin/env bash

docker rmi sb-trivia
docker rmi registry.heroku.com/stats-bookie-integration/web

docker build -t sb-trivia .

docker tag sb-trivia registry.heroku.com/stats-bookie-integration/web
docker push registry.heroku.com/stats-bookie-integration/web