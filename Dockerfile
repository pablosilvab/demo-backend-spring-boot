FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY target/demo-backend-spring-boot-0.1.jar demo-backend-spring-boot.jar
ENTRYPOINT ["java","-jar","/demo-backend-spring-boot.jar"]