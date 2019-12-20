FROM gradle:6.0.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

RUN rm /home/gradle/src/build/libs/src*
RUN ls -la /home/gradle/src/build/libs/*

FROM openjdk:8-alpine
EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/inversion-app.jar
COPY --from=build /home/gradle/src/dist/files/*.* /app/
COPY --from=build /home/gradle/src/.env /.env

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/inversion-app.jar"]