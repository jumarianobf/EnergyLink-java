# Etapa de construção
FROM ubuntu:latest AS build

# Instalar OpenJDK 17 e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos do projeto
COPY . .

# Construir o projeto com Maven
RUN mvn clean install

# Etapa de execução
FROM openjdk:17-jdk-slim

# Expor a porta 8080
EXPOSE 8080

# Copiar o JAR gerado pela etapa anterior
COPY --from=build /app/target/global-0.0.1-SNAPSHOT.jar /app.jar

# Definir o comando de inicialização
ENTRYPOINT ["java", "-jar", "/app.jar"]
