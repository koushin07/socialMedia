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

#
# Database configuration
#
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://<your-database-hostname>:5432/socmed_backend
ENV SPRING_DATASOURCE_USERNAME=socmed_backend_user
ENV SPRING_DATASOURCE_PASSWORD=hhEemvRKk6pSYIzdKHUFXyNbRQBBOcgL
ENV SPRING_JPA_DATABASE-PLATFORM=org.hibernate.dialect.PostgreSQLDialect
