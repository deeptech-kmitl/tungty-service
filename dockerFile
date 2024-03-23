# Stage 1: Build the application and generate the jar
FROM gradle:7.5.1-jdk17 AS builder

WORKDIR /app
COPY . .
RUN gradle build

# Stage 2: Copy the jar into a slim base image
FROM openjdk:17-jdk-slim

COPY --from=builder /app/build/libs/*.jar tungty-service.jar

EXPOSE 8087
ENTRYPOINT ["java", "-jar", "tungty-service.jar"]
