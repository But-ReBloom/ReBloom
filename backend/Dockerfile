FROM gradle:jdk21-alpine AS builder
WORKDIR /app

RUN apk update && apk add --no-cache \
    musl-locales \
    musl-locales-lang

ENV LANG=ko_KR.UTF-8
ENV LC_ALL=ko_KR.UTF-8
ENV TZ=Asia/Seoul
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

COPY --chown=gradle:gradle . .

RUN gradle bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN apk update && apk add --no-cache \
    musl-locales \
    musl-locales-lang

ENV LANG=ko_KR.UTF-8
ENV LC_ALL=ko_KR.UTF-8
ENV TZ=Asia/Seoul

COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
