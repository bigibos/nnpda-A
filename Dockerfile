# 1. Build stage – použij Gradle image
FROM gradle:8.5-jdk21 AS build

# Zkopíruj celý projekt
COPY --chown=gradle:gradle ./backend /home/gradle/project
WORKDIR /home/gradle/project

# Spusť build (vytvoří JAR v build/libs)
RUN gradle bootJar --no-daemon --stacktrace

# 2. Run stage – použij lehký JDK obraz
FROM eclipse-temurin:21-jdk-alpine

# Nastav pracovní složku
WORKDIR /app

# Zkopíruj JAR z předchozího buildu
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

# Exponuj port
EXPOSE 8080

# Spusť aplikaci
ENTRYPOINT ["java", "-jar", "app.jar"]
