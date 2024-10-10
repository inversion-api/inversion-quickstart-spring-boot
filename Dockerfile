FROM openjdk:17-alpine AS build

COPY --chown=gradle:gradle . /build/inversion-spring-boot-main/

RUN apk add curl unzip
WORKDIR /gradle
RUN curl -L https://services.gradle.org/distributions/gradle-7.6.1-bin.zip -o gradle-7.6.1-bin.zip
RUN unzip gradle-7.6.1-bin.zip
ENV GRADLE_HOME=/gradle/gradle-7.6.1
ENV PATH=$PATH:$GRADLE_HOME/bin


WORKDIR /build/inversion-spring-boot-main
RUN gradle build --refresh-dependencies --no-daemon --console verbose

FROM openjdk:17-alpine
EXPOSE 8080

COPY --from=build /build/inversion-spring-boot-main/build/libs/inversion-spring-boot-main.jar /build/inversion-spring-boot-main/.env* /app/

ENTRYPOINT ["java", "-jar","/app/inversion-spring-boot-main.jar"]