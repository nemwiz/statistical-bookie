#!/usr/bin/env bash

docker rmi sb-python-backend
docker rmi registry.heroku.com/stats-bookie-integration/web

docker build -t sb-python-backend .

docker tag sb-python-backend registry.heroku.com/stats-bookie-integration/web
docker push registry.heroku.com/stats-bookie-integration/web