FROM openjdk:17-alpine
RUN apk --update add fontconfig ttf-dejavu
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]