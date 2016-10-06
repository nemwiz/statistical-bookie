#!/usr/bin/env bash
#!/usr/bin/env bash
docker rmi sb-backend
mvn clean package docker:build