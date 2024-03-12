# builder
FROM jelastic/maven:3.9.5-openjdk-21 AS builder
WORKDIR /build

COPY . .

RUN mvn clean install -DskipTests

# runner
FROM openjdk:21-jdk

WORKDIR /api
COPY --from=builder /build/travel-service/target/travel-service*.jar ./travel-service.jar
EXPOSE 8080

ENV COSMOS_URI ""
ENV COSMOS_PRIMARY_KEY ""
ENV COSMOS_SECONDARY_KEY ""
ENV COSMOS_DATABASE_NAME ""

ENTRYPOINT ["java", "-jar", "travel-service.jar"]
