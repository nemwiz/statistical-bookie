#!/usr/bin/env bash
#!/usr/bin/env bash

docker rm sb-backend
docker rmi sb-backend
mvn clean package docker:build

docker tag sb-backend registry.heroku.com/stats-bookie-backend/web
docker push registry.heroku.com/stats-bookie-backend/web