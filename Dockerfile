FROM gradle:6.0.1-jdk8 AS build
COPY --chown=gradle:gradle . /build/inversion-spring-boot-main/

WORKDIR /build/inversion-spring-boot-main
RUN gradle build --refresh-dependencies --no-daemon --console verbose

FROM openjdk:8-alpine
EXPOSE 8080

COPY --from=build /build/inversion-spring-boot-main/build/libs/inversion-spring-boot-main.jar /build/inversion-spring-boot-main/.env* /app/

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/inversion-spring-boot-main.jar"]