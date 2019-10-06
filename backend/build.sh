#!/usr/bin/env bash
#!/usr/bin/env bash

docker rm sb-backend
docker rmi sb-backend
docker rmi registry.heroku.com/stats-bookie-backend/web

mvn clean
mvn package
mvn docker:build

docker tag sb-backend registry.heroku.com/stats-bookie-backend/web
docker push registry.heroku.com/stats-bookie-backend/web