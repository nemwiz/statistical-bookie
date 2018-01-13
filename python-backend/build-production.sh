#!/usr/bin/env bash

docker rmi sb-python-backend-production
docker rmi registry.heroku.com/stats-bookie-backend/web

docker build -t sb-python-backend-production .

docker tag sb-python-backend-production registry.heroku.com/stats-bookie-backend/web
docker push registry.heroku.com/stats-bookie-backend/web