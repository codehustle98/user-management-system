FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar import.jar
EXPOSE 8082
CMD ["java","-jar","import.jar"]