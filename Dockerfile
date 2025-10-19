# Use OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Definir diretório de trabalho
WORKDIR /app

# Copiar pom.xml primeiro para cache das dependências
COPY pom.xml .

# Baixar dependências
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Build da aplicação
RUN mvn clean package -DskipTests -B

# Expor porta
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "target/backend-altave-0.0.1-SNAPSHOT.jar"]