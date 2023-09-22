# Use a base image with Java 17, Gradle, and MySQL
FROM openjdk:11
FROM gradle:7.5.1-jdk11

# Set the working directory
WORKDIR /app


# JDBC_URL Value
ENV JDBC_URL="jdbc:mysql://host.docker.internal:3306/lms_server?user=root&password=manhblue"
# ENV JDBC_URL="jdbc:mysql://localhost:3307/lms?user=root&password=root"

COPY ./build/libs/*-all.jar /app/com.asura.lms-server-all.jar

# Expose the port your Ktor application will run on
EXPOSE 8080:8080

# Start Ktor application
# CMD ["java", "-jar", "build/libs/com.asura.lms-server-all.jar"]
CMD ["java", "-jar", "/app/com.asura.lms-server-all.jar"]

# Build your Ktor application using Gradle Wrapper
#RUN gradle wrapper
#RUN ./gradlew build

# Copy the Gradle wrapper files (gradlew and gradlew.bat) to the container
#COPY gradlew gradlew.bat /app/
## Copy Ktor application code and Gradle files into the container
#COPY build.gradle.kts settings.gradle.kts gradle.properties /app/
#COPY src/ /app/src/