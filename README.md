# Parrot Tech

## Integrantes do Grupo

### Caio Eduardo Nascimento Martins (RM554025)
**Responsável pelo desenvolvimento em Advanced Business Development with .NET, DevOps Tools & Cloud Computing e Compliance, Quality Assurance & Tests.**

### Julia Mariano Barsotti Ferreira (RM552713)  
**Responsável pelo desenvolvimento Java Advanced, Mobile Application Development e Compliance, Quality Assurance & Tests.**

### Leonardo Gaspar Saheb (RM553383)  
**Responsável pelo desenvolvimento em Mastering Relational and Non-Relational Database, Disruptive Architectures: IoT, IoB & Generative AI e Compliance, Quality Assurance & Tests.**

  ##

## Pré-requisitos
Para rodar este projeto, você precisará ter os seguintes itens instalados em sua máquina:

- **JDK (Java Development Kit)**: Certifique-se de ter o JDK instalado, pois é necessário para compilar e rodar aplicações Java.
- **Java 17+**: A aplicação foi desenvolvida usando a versão 17 ou superior do Java.
- **Maven**: O Maven é utilizado para gerenciar as dependências e compilar o projeto.

Documentação de Configuração do Banco de Dados
#### Configurações do DataSource:
```
spring.datasource.url=jdbc:mysql://localhost/challenge?createDatabaseIfNotExist=true&useSSL=true
spring.datasource.username=<SEU_USUARIO>
spring.datasource.password=<SUA_SENHA>
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
```

  ##
  
# Instruções para Rodar a Aplicação

### 1. Clonar o Repositório
Primeiro, clone o repositório do projeto para sua máquina local. No terminal, execute:

```bash
git clone https://github.com/seu-usuario/odontoprev-project.git
```

Acesse o diretório do projeto clonado:
```
cd odontoprev-project
```
Compile o projeto usando o Maven, pulando os testes. Execute o seguinte comando:
```
mvn clean package -Dmaven.test.skip=true
```
Após a compilação, o Maven irá gerar um arquivo JAR no diretório target. O caminho do arquivo JAR será:
```
<seu_diretorio_do_projeto>\target\<nome_do_projeto>-<versao>.jar
```
E então é so rodar seu Application!

  ##
  
## Imagem dos Diagramas
### Sprint 1 
![Diagrama de Entidade e Relacionamento (DER)](https://github.com/jumarianobf/Odontoprev/blob/main/imagens/Relational_Sprint1.png)

![Diagrama de Classes de Entidade](https://github.com/jumarianobf/Odontoprev/blob/main/imagens/Odontoprev.drawio%20(sprint_1).png)

##

### Sprint 2
![Diagrama de Entidade e Relacionamento (DER)](https://github.com/jumarianobf/Odontoprev/blob/main/imagens/DER_Sprint_2.png)

![Diagrama de Classes de Entidade](https://github.com/jumarianobf/Odontoprev/blob/main/imagens/Odontoprev.drawio%20(sprint_2).png)

## Vídeo de Apresentação

### Link video apresentando proposta tecnológica -> https://www.youtube.com/watch?v=9aIgBieK4Hk
  
  ##

# Documentação da API

## Usuários
- **GET** `/api/usuarios/listar`: Lista todos os usuários.
- **POST** `/api/usuarios/cadastrar`: Cria um novo usuário.
- **GET** `/api/usuarios/{id}`: Retorna os detalhes de um id (do usuario) específico.
- **PUT** `/api/usuarios/{id}`: Atualiza as informações de um usuário.
- **DELETE** `/api/usuarios/{id}`: Remove um usuário.

## Previsões
- **GET** `/api/previsao-usuario/listar`: Lista todas as previsões de usuários.
- **POST** `/api/previsao-usuario/cadastrar`: Cria uma nova previsão de usuário.
- **GET** `/api/previsao-usuario/{id}`: Retorna os detalhes de uma previsão específica.
- **PUT** `/api/previsao-usuario/{id}`: Atualiza uma previsão de usuário.
- **DELETE** `/api/previsao-usuario/{id}`: Remove uma previsão de usuário.

---

## Atendimentos
- **GET** `/api/atendimento-usuario/listar`: Lista todos os atendimentos de usuários.
- **POST** `/api/atendimento-usuario/cadastrar`: Cria um novo atendimento de usuário.
- **GET** `/api/atendimento-usuario/{id}`: Retorna os detalhes de um atendimento específico.
- **PUT** `/api/atendimento-usuario/{id}`: Atualiza um atendimento de usuário.
- **DELETE** `/api/atendimento-usuario/{id}`: Remove um atendimento de usuário.

---

## Imagens
- **GET** `/api/imagem-usuario/listar`: Lista todas as imagens de usuários.
- **POST** `/api/imagem-usuario/cadastrar`: Adiciona uma nova imagem de usuário.
- **GET** `/api/imagem-usuario/{id}`: Retorna os detalhes de uma imagem específica.
- **PUT** `/api/imagem-usuario/{id}`: Atualiza as informações de uma imagem de usuário.
- **DELETE** `/api/imagem-usuario/{id}`: Remove uma imagem de usuário.

---

## Endereços
- **GET** `/api/endereco-usuario/listar`: Lista todos os endereços de usuários.
- **POST** `/api/endereco-usuario/cadastrar`: Adiciona um novo endereço de usuário.
- **GET** `/api/endereco-usuario/{id}`: Retorna os detalhes de um endereço específico.
- **PUT** `/api/endereco-usuario/{id}`: Atualiza as informações de um endereço de usuário.
- **DELETE** `/api/endereco-usuario/{id}`: Remove um endereço de usuário.

---

## Contatos
- **GET** `/api/contato-usuario/listar`: Lista todos os contatos de usuários.
- **POST** `/api/contato-usuario/cadastro`: Adiciona um novo contato de usuário.
- **GET** `/api/contato-usuario/{id}`: Retorna os detalhes de um contato específico.
- **PUT** `/api/contato-usuario/{id}`: Atualiza as informações de um contato de usuário.
- **DELETE** `/api/contato-usuario/{id}`: Remove um contato de usuário.

---

## Dentistas
- **GET** `/api/dentista/listar`: Lista todos os dentistas.
- **POST** `/api/dentista/cadastrar`: Adiciona um novo dentista.
- **GET** `/api/dentista/{id}`: Retorna os detalhes de um dentista específico.
- **PUT** `/api/dentista/{id}`: Atualiza as informações de um dentista.
- **DELETE** `/api/dentista/{id}`: Remove um dentista.

---

## Clínicas
- **GET** `/api/clinica/listar`: Lista todas as clínicas.
- **POST** `/api/clinica/cadastrar`: Adiciona uma nova clínica.
- **GET** `/api/clinica/{id}`: Retorna os detalhes de uma clínicas específica.
- **PUT** `/api/clinica/{id}`: Atualiza as informações de uma clínica.
- **DELETE** `/api/clinica/{id}`: Remove uma clínica.

