# IncluiPlay 💛

## 📘 Descrição
Este projeto tem como objetivo a construção de uma **API REST utilizando o framework Spring Boot**. 
> 📌 **Tema:** Sistema de microdoações financeiras a projetos voltados à inclusão digital e acessibilidade.
> O sistema permite cadastrar **usuários** e **projetos**, e o usuário pode fazer **doações** para um ou mais projetos.

## 🚀 Tecnologias
Spring Boot · Spring Security · Spring Data JPA · H2 Database · JUnit · Mercado Pago API (sandbox) · Swagger UI · JUnit

## 🧩 Funcionalidades
- Cadastro e login de usuários + Autenticação com JWT
- Cadastro e listagem de projetos sociais
- Simulação de doações (integração com Mercado Pago)
- Histórico de doações por usuário
- Tratamento global de exceções
- Testes automatizados (JUnit + Mockito)

## 🛠️ Pré-requisitos e Configuração
Para rodar a API localmente:
- Requisitos: JDK 21+ e Maven instalados.
- Configuração de Segurança:
  - Atualize o `src/main/resources/application.properties` com uma chave secreta JWT segura (Base64) e as credenciais do banco de dados.

Execução:
> <p> mvn clean install </p>
> mvn spring-boot:run

## 🔑 Fluxo de Autenticação
O sistema utiliza o padrão JWT, onde o login retorna um token que deve ser usado para autenticar todas as requisições subsequentes que sejam autenticadas.

### Uso do Token (Acesso Protegido)
O Token JWT obtido no login deve ser enviado no cabeçalho Authorization para acessar qualquer endpoint autenticado.

> Authorization: Bearer [SEU TOKEN AQUI]
