# Octo Events
Octo Events é uma aplicação que recebe eventos do Github Events via webhooks e os expõe via API para uso futuro.

![alt text](imgs/octo_events.png)

## Executando e testando o projeto

### Pré-requisitos
* PostgresSQL (O banco que foi testado).
* Java >= 8 (versões > 8 talvez precisem de passos adicionais aos descritos aqui).
* Maven 3.
* Ngrok.
* Ter uma conta no Github.

### Passo a passo

####1 - Instalar o PostgresSQL:

A página oficial contém [links para download](https://www.postgresql.org/download/) do SGBD para diversas plataformas.

Para quem usa versões _debian like_ do Linux este [tutorial](https://www.digitalocean.com/community/tutorials/como-instalar-e-utilizar-o-postgresql-no-ubuntu-16-04-pt) 
contém um passo a passo bem detalhado de como configurar o banco.

Para instalação no Ubuntu 18 os passos seguidos foram:

1.1 Instalar o PostgreSQL:

```
$ sudo apt-get update
$ sudo apt-get install postgresql postgresql-contrib
```

1.2. Criar um usuário do s.o. chamado `postgres`:

`$ sudo -i -u postgres`

1.3. Criar um usuário para a aplicação chamado `octo_events`:

`$ sudo -u postgres createuser --interactive`

1.4. Criar o banco de dados para a aplicação:

`sudo -u postgres createdb octo_events`

1.5 Configurar senha:

`sudo -u postgres password octo_events`

####2 - Configurar as propriedades da aplicação 

Caminho até o arquivo de propriedades da aplicação:
`recrutamento-kotlin-jya-michelsilves/src/main/resources/application.yml`

O usuário e a senha cadastrados nos passos anteriores deverão ser informados
no arquivo de propriedades.

Por exemplo, a string de conexão com o banco `octo_events` rodando local na porta `8080`
seria `jdbc:postgresql://localhost:5432/octo_events`. Para configurar usuário `octo_events` e senha `123` uma configuração
válida seria a abaixo:

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/octo_events    
    username: octo_events     
    password: 123
```

**Observação sobre outrs propriedades:**

Para que as chamadas ao endpoint webhook sejam mais rastreáveis foi configurado para que as 
queries executadas pela aplicação e os payloads submetidos pelos eventos do Github (no nível DEBUG)
fossem gravados na saída padrão.

```
  jpa:
    properties.hibernate.dialect: org.hibernate.dialect.PostgreSQLDialect
    hibernate.ddl-auto: update
    show-sql: true
logging:
  level:
    org.springframework.web.filter.CommonsRequestLoggingFilter: DEBUG
```

#### 3 - Buildar e executar a aplicação

Dentro do diretório root da aplicação executar os passos abaixo.

3.1 Para rodar os testes:

`$ mvn test #Para rodar os testes`

3.2 Para buildar a aplicação:
`$ mvn clean install #buildando o pacote da aplicação`

3.3 Para executar a aplicação:

O Maven através do `spring-boot-maven-plugin` gera o jar executável.
Então basta entrar no diretório `target/` e executar 

`$ java -jar Octo-Events-1.0.jar`

A partir deste ponto se tudo ocorrer corretamente a aplicação estará rodando 
e atendendo requisições através da porta cadastrada no arquivo `application.yml` (se não foi alterado estará na porta 8080).

É possível utilizar o client do swagger através da url [http://localhost:8080/swagger-ui.html]


#### 4 Criar o tunel para acessar os endpoints externamente

Utilizamos o `ngrok` para criar uma ponte para da nossa API externamente.

Utilizar o `ngrok` é bem simples, após fazemos o download e dentro da sua pasta executamos
o comando abaixo para que se direcione para a porta 8080 os requests externos.

$ sudo ./ngrok http 8080

Retornarão as urls (http ou https) pelas quais se poderá acessar a aplicação externamente.

#### 5 Criar um repositório e cadastrar o webhook no github.

Cadastrar o endpoint /events como webhook e escolher a opção de escutar 
apenas os eventos de `issues`.

* Webhooks Overview: https://developer.github.com/webhooks/ 
* Creating Webhooks : https://developer.github.com/webhooks/creating/

#### 6 - Testar a aplicação
* Realizar as operações que deverão disparar eventos no repositório do Github ao qual foi cadastrado o webhook:
    1) criar nova issue
    2) Adicionar labels
    3) Adicionar Assigne
    4) Adicionar mais Assignees
    5) Criar Milestone
    6) fechar a issue.
    7) Atualizar o nome da issue.
    
* Acompanhar nos logs as chamadas realizadas.

* Utiliar um client ou Acessar a inteface web do swagger gerada pela aplicação para testar o endpoint `GET issues/{issueId}/events` 
para recuperar os eventos gravados.

**Observação:**

É possível acompanhar o `id`'s das `issues` através do payload das chamadas ao `/events` logadas, porém
a saída não está muito formatada. Alternativamente podemos buscar
os ids no banco.

É possível através da query abaixo obter estes id's.

`SELECT DISTINCT id FROM github_issue_snapshot`


## Endpoints 


`GET /issues/{issueId}/events`

`POST /events`


## Principais tecnologias e frameworks utilizados

- Java 8
- Spring Boot 2
- JUnit 5
- Lombok
- Hibernate
- Swagger
