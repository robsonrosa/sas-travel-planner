FROM openjdk:21-jdk

WORKDIR /api
COPY **/target/travel-service*.jar ./travel-service.jar
EXPOSE 8080

ENV COSMOS_URI ""
ENV COSMOS_PRIMARY_KEY ""
ENV COSMOS_SECONDARY_KEY ""
ENV COSMOS_DATABASE_NAME ""

CMD ["java", "-jar", "travel-service.jar"]
