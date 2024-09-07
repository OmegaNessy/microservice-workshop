FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY target/micro-sender-*.jar micro-sender.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "micro-sender.jar"]