#!/usr/bin/env bash
#!/usr/bin/env bash

docker rm sb-backend
docker rmi sb-backend
mvn clean package docker:build