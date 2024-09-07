FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY target/micro-recipient-*.jar micro-recipient.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "micro-recipient.jar"]