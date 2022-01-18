FROM adoptopenjdk/openjdk11:alpine

WORKDIR /opt/server
COPY ./target/cars-0.0.1-SNAPSHOT.jar server.jar

ENTRYPOINT ["java", "-jar", "server.jar"]

