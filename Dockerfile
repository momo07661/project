FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/project-1.1.0.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]
