FROM amazoncorretto:17 as BUILD

WORKDIR /usr/temp

COPY . .

RUN ["./gradlew", "app:build"]

FROM amazoncorretto:17

WORKDIR /usr/app/

COPY --from=BUILD /usr/temp/app/build/libs/app-SNAPSHOT.jar app.jar

ENV REDIS_HOST localhost
ENV REDIS_PORT 6397
ENV REDIS_DB 0

ENV DB_HOST localhost
ENV DB_PORT 5432
ENV DB_NAME db_name
ENV DB_USER db_user
ENV DB_PASSWORD db_pass

ARG IMAGE_VERSION

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]