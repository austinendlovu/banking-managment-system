# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the application without running tests
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM eclipse-temurin:21-alpine

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage to the runtime stage
COPY --from=build /app/target/*.jar demo.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "demo.jar"]