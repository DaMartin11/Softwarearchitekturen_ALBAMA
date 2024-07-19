# Stage 1: Build the application
FROM maven:3.6.3-openjdk-17 AS build
LABEL authors="alimi"

WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/productservice-0.0.1-SNAPSHOT.jar /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/productservice-0.0.1-SNAPSHOT.jar"]
