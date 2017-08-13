#!/usr/bin/env bash
#!/usr/bin/env bash

docker rmi sb-data-scraper
docker rmi registry.heroku.com/stats-bookie-data-scraper/web

mvn clean
mvn package

docker build -t sb-data-scraper .

docker tag sb-data-scraper registry.heroku.com/stats-bookie-data-scraper/web
docker push registry.heroku.com/stats-bookie-data-scraper/web