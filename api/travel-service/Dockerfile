FROM openjdk:21-jdk

WORKDIR /api
COPY ./target/travel-service*.jar /api/travel-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/travel-service.jar"]
