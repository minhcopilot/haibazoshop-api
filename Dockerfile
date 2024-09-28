FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/haibazoshop-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888

CMD ["java", "-jar", "app.jar"]