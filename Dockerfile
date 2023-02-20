FROM amazoncorretto:17 as BUILD

WORKDIR /usr/temp

COPY . .

RUN ["./gradlew", "clean", "ktlintCheck", "test", "app:build"]

FROM amazoncorretto:17

WORKDIR /usr/app/

COPY --from=BUILD /usr/temp/app/build/libs/app-SNAPSHOT.jar app.jar

ENV REDIS_HOST localhost
ENV REDIS_PORT 6397

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]