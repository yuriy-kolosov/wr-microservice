# Use the official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/wr-m-0.0.1-SNAPSHOT.jar wr-m_app.jar

# Expose port 9090
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "wr-m_app.jar"]