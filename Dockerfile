FROM openjdk:11-jre-slim

EXPOSE 8080

ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]