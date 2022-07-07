FROM openjdk:8-jre-alpine
ADD target/scala-2.12/flight-management-api-service-assembly-3.0-SNAPSHOT.jar flight-management-api-service-assembly-3.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","flight-management-api-service-assembly-3.0-SNAPSHOT.jar"]