# Use Eclipse Temurin 17 como base
FROM eclipse-temurin:17-jdk-alpine AS build

# Instalar Maven
RUN apk add --no-cache maven

# Definir diretório de trabalho
WORKDIR /app

# Copiar pom.xml primeiro para cache das dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Build da aplicação
RUN mvn clean package -DskipTests -B

# Fase de runtime com imagem menor
FROM eclipse-temurin:17-jre-alpine

# Copiar JAR do estágio de build
COPY --from=build /app/target/backend-altave-0.0.1-SNAPSHOT.jar /app/app.jar

# Trabalhar como root para criar diretório e permissões
RUN mkdir -p /app/uploads && chmod 755 /app/uploads

# Expor porta
EXPOSE 8080

# Expor variáveis de ambiente
ENV JAVA_OPTS=""

# Comando para iniciar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]