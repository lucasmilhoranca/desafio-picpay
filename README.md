<p align="center" width="100%">
    <img width="30%" src="./images/picpay-logo.jpg"> 
</p>


<h3 align="center">
  Desafio Backend do PicPay
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">

</p>

## Desafio
- Confira o desafio [clicando aqui](https://github.com/PicPay/picpay-desafio-backend).

## Como interagir com o banco de dados?
- Utilizei o [Beekeeper Community](https://github.com/beekeeper-studio/beekeeper-studio/releases/tag/v4.1.13).

## Como interagir com a API?
- Utilizei o [Httpie](https://github.com/httpie).

:mag: Baixe o projeto e teste você mesmo na prática.

## :rocket: Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL
* Docker
* Spring Cloud Open Feign
* ControllerAdvice & Problem Details
* Hibernate Validator

**Implementação de API Completa com Spring Boot, JPA e MySQL:**

- Criar uma API com Spring Boot
- Mapear as entidades com Jakarta Persistence
- Comunicar com o banco de dados MySQL via Docker
- Comunicar com API externa via Spring Cloud OpenFeign
- Realizar processamento assíncrono com CompletableFuture
- Validar dados de entrada da API com Hibernate Validator
- Tratar as exceções de sua API com Problem Details (RFC 7807)
- Efetuar logs com o SLF4J

### Lista de tarefas:

**Introdução**:

- [x]  Qual é o desafio que vamos resolver?

---

**Comunicação com o banco e dados**:

- [x]  Iniciando o projeto Java (JPA, MySQL, Validator, OpenFeign)
- [x]  Configurando o MySQL no Docker
- [x]  Configurando a comunicacao do Spring Boot com o MySQL

---

**Funcionalidade de Cadastro de Wallet**:

- [x]  Mapear as entidades (Wallet, WalletType)
- [x]  Criar inicialização de WalletType no banco de dados
- [x]  Criar a funcionalidade de cadastro de Wallet:
- [x]  1° Validação dos dados únicos
- [x]  2° Inserção na base
- [x]  Criar tratamento de exceções com ProblemDetails e RestExceptionHandler
- [x]  Criar validação de dados de entrada
- [x]  Ajustar tratamento de exceções
- [x]  Testar a API e validar no banco de dados

---

**Funcionalidade de Transferência**:

- [x]  Mapear a entidade (Transaction)
- [x]  Criar os mocks do Autorizador e Notificador Externo (https://designer.mocky.io/)
- [x]  Criar o serviço que irá consultar o autorizador externo
- [x]  Criar o serviço que irá notificar o recebimento da transferência
- [x]  Criar a funcionalidade de transação
- [x]  1° Validar o tipo da carteira
- [x]  2° Validar o saldo
- [x]  3° Consultar autorizador externo
- [x]  4° Realizar a transferência
- [x]  5° Enviar notificação para o recebedor (CompletableFuture)
- [x]  Validar o tratamento de exceções com RestExceptionHandler
- [x]  Testar a API e validar no banco de dados

---

**Ajuste final**

- [x]  Aplicar logging no projeto com SLF4J
