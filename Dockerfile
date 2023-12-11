FROM maven:3.9.5-eclipse-temurin-17-alpine AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
ARG BUILD_PROFILE
RUN --mount=type=cache,target=/root/.m2 mvn -f /usr/src/app/pom.xml clean verify
WORKDIR extracted
RUN java -Djarmode=layertools -jar /usr/src/app/target/*.jar extract
FROM eclipse-temurin:17.0.5_8-jre-focal
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]