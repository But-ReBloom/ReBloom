# 1️⃣ 빌드 단계 (Gradle + JDK 21 + 로케일 설정)
FROM gradle:jdk21-alpine AS builder
WORKDIR /app

# --- 로케일 설정 (alpine은 musl, 그래서 이렇게 해야 함) ---
RUN apk update && apk add --no-cache \
    musl-locales \
    musl-locales-lang

ENV LANG=ko_KR.UTF-8
ENV LC_ALL=ko_KR.UTF-8
ENV LANGUAGE=ko_KR:ko
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

# 프로젝트 복사
COPY --chown=gradle:gradle . .

# Gradle 빌드 (테스트 제외)
RUN gradle bootJar -x test --no-daemon


# 2️⃣ 실행 단계 (Temurin JRE 21 + 로케일 + UTF-8)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# --- 로케일 설정 ---
RUN apk update && apk add --no-cache \
    musl-locales \
    musl-locales-lang

ENV LANG=ko_KR.UTF-8
ENV LC_ALL=ko_KR.UTF-8
ENV LANGUAGE=ko_KR:ko
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

# 빌드된 JAR 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# 실행
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
