#!/usr/bin/env bash

docker rmi sb-python-backend-integration
docker rmi registry.heroku.com/stats-bookie-integration/web

docker build -t sb-python-backend-integration .

docker tag sb-python-backend-integration registry.heroku.com/stats-bookie-integration/web
docker push registry.heroku.com/stats-bookie-integration/web