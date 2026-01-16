# Multi-stage Dockerfile to build and run the application

# Stage 1: Build the Application
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the source code
COPY TodoBackend /app/TodoBackend
COPY TodoFrontend /app/TodoFrontend

# Create static directory if it doesn't exist (it should be created by maven but just in case)
RUN mkdir -p /app/TodoBackend/src/main/resources/static

# Copy frontend files to backend static resources
# This allows the Spring Boot app to serve the frontend directly
RUN cp -r /app/TodoFrontend/* /app/TodoBackend/src/main/resources/static/

# Modify script.js to use relative paths for production
# This replaces 'http://localhost:8080' with an empty string
RUN sed -i 's|http://localhost:8080||g' /app/TodoBackend/src/main/resources/static/script.js

# Build the application
WORKDIR /app/TodoBackend
RUN mvn clean package -DskipTests

# Stage 2: Prepare the Runtime Image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/TodoBackend/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
