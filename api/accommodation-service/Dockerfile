FROM openjdk:21-jdk

WORKDIR /api
COPY ./target/accommodation-service*.jar /api/accommodation-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/accommodation-service.jar"]
