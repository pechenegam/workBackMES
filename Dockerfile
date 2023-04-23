FROM maven:3.6.0-jdk-11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
