# Usar uma imagem do OpenJDK 17
FROM openjdk:17-jdk-slim

# Diretório onde a aplicação será copiada
WORKDIR /app

# Copiar o arquivo JAR do diretório target para o contêiner
COPY target/global-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta 8080, que é a porta padrão do Spring Boot
EXPOSE 8080

# Definir o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# CMD pode ser utilizado para configurar argumentos padrão, mas não é necessário nesse caso
# CMD ["--server.port=8080"]
