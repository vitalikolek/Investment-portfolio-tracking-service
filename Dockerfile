FROM gradle:jdk11 AS builder
WORKDIR /app
COPY . /app
RUN gradle build

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/build/libs/investmentPortfolioTrackingService-1.0-SNAPSHOT.war backend.war
ENTRYPOINT ["java", "-jar", "backend.war"]
