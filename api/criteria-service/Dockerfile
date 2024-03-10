FROM openjdk:21-jdk

WORKDIR /api
COPY ./target/criteria-service*.jar /api/criteria-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/criteria-service.jar"]
