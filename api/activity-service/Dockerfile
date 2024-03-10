FROM openjdk:21-jdk

WORKDIR /api
COPY ./target/activity-service*.jar /api/activity-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/activity-service.jar"]
