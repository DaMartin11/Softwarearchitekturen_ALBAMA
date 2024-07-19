# Build stage
FROM maven:3.6.3-openjdk-17 AS build
LABEL authors="alimi"
WORKDIR /app
COPY . /app

RUN mvn clean package -DskipTests

# Production stage
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/app.jar /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]