FROM openjdk:11-oracle

WORKDIR /app

COPY dispatcher-microservice/target/dispatcher-microservice-0.0.1-SNAPSHOT.jar dispatcher-microservice.jar

CMD ["java", "-jar", "dispatcher-microservice.jar"]