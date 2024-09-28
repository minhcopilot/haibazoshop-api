FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

COPY --from=build target/haibazoshop-0.0.1-SNAPSHOT.jar haibazoshop.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "haibazoshop.jar"]