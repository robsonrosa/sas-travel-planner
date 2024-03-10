FROM openjdk:21-jdk

WORKDIR /api
COPY ./target/flight-service*.jar /api/flight-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/flight-service.jar"]
