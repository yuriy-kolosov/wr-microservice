FROM maven:3.9.9-amazoncorretto-17-al2023 AS build

COPY . /app

WORKDIR /app

RUN mvn clean package -DskipTests=true

# Use the official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Copy the built jar file into the container
COPY --from=build /app/target/wr-m-0.0.1-SNAPSHOT.jar /wr-m_app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "wr-m_app.jar"]