FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

COPY --from=build target/haibazoshop-0.0.1-SNAPSHOT.jar haibazoshop.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "haibazoshop.jar"]
ENV DB_PASSWORD=${DB_PASSWORD}
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV API_BASE_URL=${API_BASE_URL}
ENV CLIENT_BASE_URL=${CLIENT_BASE_URL}
ENV CLOUDINARY_CLOUD_NAME=${CLOUDINARY_CLOUD_NAME}
ENV CLOUDINARY_API_KEY=${CLOUDINARY_API_KEY}
ENV CLOUDINARY_API_SECRET=${CLOUDINARY_API_SECRET}