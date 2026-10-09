# --- Etapa de compilación ---
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar archivos de Maven y dependencias para aprovechar la caché
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dar permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente y empaquetar la app omitiendo pruebas
COPY src src
RUN ./mvnw package -DskipTests

# --- Etapa de ejecución ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]