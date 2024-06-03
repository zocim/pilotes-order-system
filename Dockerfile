# Use a base image with OpenJDK installed
FROM openjdk:11-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Copy the Maven project description
COPY pom.xml .

# Copy the project source code
COPY src src

# Build the application
RUN chmod +x ./mvnw
RUN ./mvnw install -DskipTests

# Use a new stage for the final image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
