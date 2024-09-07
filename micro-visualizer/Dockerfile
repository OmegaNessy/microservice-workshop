FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY target/micro-visualizer-*.jar micro-visualizer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "micro-visualizer.jar"]