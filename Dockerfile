# Use the official Ubuntu base image
FROM ubuntu:latest

# Install JDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the Spring Boot application
RUN mvn clean package

# Expose the port on which the application runs
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/basic-container-0.0.1-SNAPSHOT.jar"]