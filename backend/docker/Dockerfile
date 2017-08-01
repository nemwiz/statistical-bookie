FROM java:8-jre
ADD /sb-1.0.jar //
ADD /sb.yml //
CMD java $JAVA_OPTS -Ddw.server.applicationConnectors[0].port=$PORT -Ddw.mongo.seeds[0].host=$MONGO_HOST -Ddw.mongo.seeds[0].port=$MONGO_PORT -Ddw.mongo.database=$MONGO_DB_NAME -Ddw.mongo.credentials.userName=$MONGO_USER -Ddw.mongo.credentials.password=$MONGO_PASSWORD -jar /sb-1.0.jar server /sb.yml
