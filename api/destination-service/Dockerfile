FROM openjdk:21-jdk

WORKDIR /api
COPY ./target/destination-service*.jar /api/destination-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/destination-service.jar"]
