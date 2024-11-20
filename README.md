# Energy Link
#### Plataforma de Distribuição de Energia Sustentável para Cidades Próximas 🌍⚡

## Descrição do Projeto
Este repositório contém o desenvolvimento de uma **plataforma digital para conectar comunidades produtoras de energia limpa (eólica e solar) com fábricas e cidades próximas**. A solução promove:
- **Autossuficiência energética regional**.
- **Redução de custos** para empresas e comunidades.
- **Sustentabilidade ambiental**, incentivando o uso de energia limpa.


  ##

  
## Funcionalidades Principais
1. **Cadastro e Gestão dos Pontos de Produção de Energia**  
   - Cada ponto de geração pode se registrar na plataforma, com dados como:
     - Capacidade de geração.
     - Localização.
     - Histórico de produção.
   - Backend desenvolvido em **.NET** e **Java Advanced** para garantir escalabilidade.

2. **Marketplace para Compra e Venda de Energia**  
   - Interface para compradores e vendedores realizarem transações diretas de energia.  
   - Disponível como aplicativo mobile em **Kotlin** ou uma interface web.

3. **Otimização de Preços com Machine Learning**  
   - Preços dinâmicos baseados em variáveis como:
     - Demanda.
     - Condições climáticas.
     - Horários de pico.
   - Modelos desenvolvidos em **Python**.

4. **Monitoramento e Relatórios**  
   - Monitoramento e controle das transações.  
   - Backend processa dados utilizando **Java Advanced** e **.NET**.

## 


## Fluxo Tecnológico

### Arquitetura Geral
- **Backend**: `.NET` e `Java Advanced` para gestão de dados, segurança e escalabilidade.  
- **Machine Learning**: Modelos preditivos em `Python`  para envio de e-mails automatizados, baseado em eventos específicos ou condições predefinidas.  
- **Mastering Relational and non-relational database **:  Modelagem de Banco de Dados relacional e não relacional, utilizando práticas de modelagem de dados na 3ª Forma Normal (3FN) e implementando no banco de dados Oracle. 
- **Interface de Usuário**: Aplicativo em `Kotlin` ou interface web para acesso ao sistema.
- **DevOps Tools e Cloud Computing**: Implementação de 2 (duas) `Virtual Machines` em nuvem (preferencialmente Azure), sendo uma com aplicação frontend e outra com o backend (API e banco de dados), simulando um ambiente de desenvolvimento.

## Pré-requisitos
Para rodar este projeto, você precisará ter os seguintes itens instalados em sua máquina:

- **JDK (Java Development Kit)**: Certifique-se de ter o JDK instalado, pois é necessário para compilar e rodar aplicações Java.
- **Java 17+**: A aplicação foi desenvolvida usando a versão 17 ou superior do Java.
- **Maven**: O Maven é utilizado para gerenciar as dependências e compilar o projeto.

Documentação de Configuração do Banco de Dados
#### Configurações do DataSource:
```
spring.application.name=globalsolution
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
spring.datasource.username=<SEU_USUARIO>
spring.datasource.password=<SUA_SENHA>
spring.jpa.properties.hibernate.format_sql=true
```
Faça isso a primeira vez para criar o banco de dados
```
spring.jpa.hibernate.ddl-auto=create
```

depois mude para update para atualizar a estrutura do banco de dados sem perder dados:
```
spring.jpa.hibernate.ddl-auto=update
```

  ##
  
# Instruções para Rodar a Aplicação

### 1. Clonar o Repositório
Primeiro, clone o repositório do projeto para sua máquina local. No terminal, execute:

```bash
git clone https://github.com/seu-usuario/EnergyLink-java.git
```

Acesse o diretório do projeto clonado:
```
cd energyLink-project
```
Compile o projeto usando o Maven, pulando os testes. Execute o seguinte comando:
```
mvn clean package -Dmaven.test.skip=true
```
Após a compilação, o Maven irá gerar um arquivo JAR no diretório target. O caminho do arquivo JAR será:
```
<seu_diretorio_do_projeto>\target\<nome_do_projeto>-<versao>.jar
```
Acessar a Aplicação
A aplicação agora está rodando dentro do container. Abra o navegador e acesse a seguinte URL:
```
http://localhost:8080
```
Com Swagger:
```
http://localhost:8080/swagger-ui/index.html#/
```

  ##
  
##

### Modelagem do Banco de Dados
![Diagrama de Entidade e Relacionamento (DER)](https://github.com/jumarianobf/EnergyLink-java/blob/main/imagens/Relacionamento.png)


## Vídeo de Apresentação

### Link do Vídeo Pitch -> 
### Link do Vídeo demonstrando o software funcionando -> 
  
  ##

# Documentação da API



## Desenvolvedores

### Caio Eduardo Nascimento Martins (RM554025)
**Responsável pelo desenvolvimento em Advanced Business Development with .NET, DevOps Tools & Cloud Computing e Compliance, Quality Assurance & Tests.**

### Julia Mariano Barsotti Ferreira (RM552713)  
**Responsável pelo desenvolvimento Java Advanced, Mobile Application Development e Compliance, Quality Assurance & Tests.**

### Leonardo Gaspar Saheb (RM553383)  
**Responsável pelo desenvolvimento em Mastering Relational and Non-Relational Database, Disruptive Architectures: IoT, IoB & Generative AI e Compliance, Quality Assurance & Tests.**
