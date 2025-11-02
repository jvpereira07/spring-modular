## Multi-stage build for the multi-module Maven project

FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy the entire multi-module project
COPY . ./

# Build only what's needed (core) but also build its module deps (-am)
RUN mvn -q -DskipTests -pl core -am package


FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy Spring Boot fat jar from core module
COPY --from=build /workspace/core/target/core-*.jar /app/app.jar

ENV JAVA_OPTS=""
EXPOSE 8080

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

