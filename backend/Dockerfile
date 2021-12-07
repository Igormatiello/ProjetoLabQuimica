FROM adoptopenjdk/openjdk11:latest

ADD target/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
