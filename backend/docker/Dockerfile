FROM java:8-jre
ADD /sb-1.0.jar //
ADD /sb.yml //
CMD java $JAVA_OPTS -Ddw.server.applicationConnectors[0].port=$PORT -jar /sb-1.0.jar server /sb.yml
