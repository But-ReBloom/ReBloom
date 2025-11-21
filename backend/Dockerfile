# 1. 빌드 단계 (여기를 바꿨습니다!)
# 원래 쓰던 eclipse-temurin 대신, 이미 Gradle이 깔려있는 이미지를 씁니다.
FROM gradle:jdk21-alpine AS builder
WORKDIR /app

# 파일 소유권을 gradle로 변경하며 복사 (이 이미지는 보안상 root가 아니기 때문)
COPY --chown=gradle:gradle . .

# ./gradlew (깨진 파일) 대신, 설치된 gradle 명령어를 직접 씁니다.
RUN gradle bootJar -x test --no-daemon

# 2. 실행 단계 (여기는 그대로)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# 빌드 결과물 위치가 조금 다를 수 있어서 찾아서 복사
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]