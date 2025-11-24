# 1️⃣ 빌드 단계
FROM gradle:jdk21-alpine AS builder
WORKDIR /app

# 소유권 gradle로 변경하며 프로젝트 복사
COPY --chown=gradle:gradle . .

# gradle 빌드 (테스트 제외, 데몬 사용 안함)
RUN gradle bootJar -x test --no-daemon

# 2️⃣ 실행 단계
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# JVM, OS 로케일 UTF-8 강제
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

# 빌드 결과물 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 (Spring Boot 기본 8080)
EXPOSE 8080

# 실행 시 UTF-8 강제
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
