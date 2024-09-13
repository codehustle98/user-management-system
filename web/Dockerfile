FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar web.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","web.jar"]