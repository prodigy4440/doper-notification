FROM maven:3-eclipse-temurin-17 AS build
WORKDIR app
COPY pom.xml pom.xml
RUN mvn -B dependency:go-offline
COPY src src
RUN mvn package

FROM eclipse-temurin:17-jdk-alpine as runtime
WORKDIR app
COPY --from=build /app/target/*.jar doper-notification.jar
CMD java -jar doper-notification.jar