FROM openjdk:21-jdk

WORKDIR /api
COPY ./target/tips-service*.jar /api/tips-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/tips-service.jar"]
