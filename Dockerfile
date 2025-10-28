# Dockerfile para Railway
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar pom.xml e baixar dependências primeiro (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Build da aplicação
RUN mvn clean package -DskipTests -B

# Fase de runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar JAR
COPY --from=build /app/target/backend-altave-0.0.1-SNAPSHOT.jar app.jar

# Criar diretório de uploads
RUN mkdir -p /app/uploads && chmod 755 /app/uploads

EXPOSE 8080

ENV JAVA_OPTS="-Xmx512m -Xms256m"

CMD ["java", "-jar", "app.jar"]