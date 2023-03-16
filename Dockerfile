#
# Build stage
#
FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/socmed-0.0.1-SNAPSHOT.jar socmed.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","socmed.jar"]