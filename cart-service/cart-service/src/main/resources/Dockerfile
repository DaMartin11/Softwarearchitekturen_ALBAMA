# Use Maven to build the application
FROM maven:3.6.3-openjdk-17 AS build
LABEL authors="alimi"

WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use OpenJDK to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/cart-service.jar

# Expose the application port
EXPOSE 8080

# Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/app/cart-service.jar"]
