FROM eclipse-temurin:17-jdk-focal AS builder
WORKDIR /app

COPY target/ventas_bolivar-0.0.1-SNAPSHOT.jar app.jar

FROM eclipse-temurin:17-jre-focal
WORKDIR /app

COPY --from=builder /app/app.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]