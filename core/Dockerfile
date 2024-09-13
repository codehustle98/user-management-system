FROM openjdk:17-alpine
WORKDIR /app
COPY build/libs/*.jar core.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","core.jar"]