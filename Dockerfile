FROM gradle:6.0.1-jdk8 AS build
COPY --chown=gradle:gradle . /build/inversion-app

WORKDIR /build/inversion-app
RUN gradle build --no-daemon --console verbose

FROM openjdk:8-alpine
EXPOSE 8080

COPY --from=build /build/inversion-app/build/libs/inversion-app.jar /build/inversion-app/.env* /app/

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/inversion-app.jar"]