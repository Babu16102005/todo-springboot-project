FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built JAR file
# Note: This Dockerfile assumes you have already run 'prepare_production.ps1' 
# or built the project such that the JAR exists.
COPY target/*.jar app.jar

# Expose port (Cloud providers often ignore this but good for documentation)
EXPOSE 8080

# Environment variables can be overridden at runtime
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tododb
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=pass

ENTRYPOINT ["java","-jar","/app.jar"]
