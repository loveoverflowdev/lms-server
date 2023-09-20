# Use a base image with Java 17, Gradle, and MySQL
FROM openjdk:17-alpine
FROM gradle:7.5.1-jdk11

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper files (gradlew and gradlew.bat) to the container
COPY gradlew gradlew.bat /app/
# Copy Ktor application code and Gradle files into the container
COPY build.gradle.kts settings.gradle.kts gradle.properties /app/
COPY src/ /app/src/

# Build your Ktor application using Gradle Wrapper
RUN gradle wrapper
RUN ./gradlew build

# Expose the port your Ktor application will run on
EXPOSE 8080

# Define environment variables for the MySQL database connection
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_USER=root
ENV DB_PASSWORD=root
ENV DB_NAME=lms

# Start Ktor application
CMD ["java", "-jar", "build/libs/com.asura.lms-server-all.jar"]
