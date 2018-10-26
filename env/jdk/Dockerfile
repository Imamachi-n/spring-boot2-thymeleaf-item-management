FROM azul/zulu-openjdk:8u181-8.31.0.1

VOLUME /tmp

ARG JAR_FILE
COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java","-Dserver.port=8080","-jar","/app.jar"]
