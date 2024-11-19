FROM maven:3.9.0-openjdk-17-slim AS build

WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk-17-jdk-slim

EXPOSE 8080

COPY --from=build /target/global-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]