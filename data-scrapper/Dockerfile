FROM cogniteev/oracle-java
COPY /amd64 /usr/lib/jvm/java-8-oracle/lib/amd64/
RUN apt-get update && apt-get install -y libgtk2.0-bin libxslt1.1 libxext6 libxtst6 libxxf86vm1 libgl1-mesa-glx libasound2
ADD /target/ds-1.0.jar //
ADD /ds-prod.yml //
WORKDIR /
CMD java $JAVA_OPTS -Ddw.server.applicationConnectors[0].port=$PORT -Ddw.apiKey=$API_KEY -Ddw.mongo.seeds[0].host=$MONGO_HOST -Ddw.mongo.seeds[0].port=$MONGO_PORT -Ddw.mongo.database=$MONGO_DB_NAME -Ddw.mongo.credentials.userName=$MONGO_USER -Ddw.mongo.credentials.password=$MONGO_PASSWORD -Dui4j.headless -Dprism.useFontConfig=false -jar /ds-1.0.jar server /ds-prod.yml