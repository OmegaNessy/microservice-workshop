FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY target/micro-collector-*.jar micro-collector.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "micro-collector.jar"]