# Backend - Plataforma de Mapeamento de Competências Altave

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-brightgreen?logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red?logo=apache-maven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)
![Status](https://img.shields.io/badge/status-Em_Desenvolvimento-yellow)

Este repositório contém o código-fonte do backend para a Plataforma de Mapeamento de Competências, um projeto desenvolvido para a FATEC São José dos Campos em parceria com a Altave.

## 🎯 Sobre o Projeto

O objetivo é desenvolver uma plataforma profissional semelhante a um "LinkedIn interno", onde cada colaborador possa criar e manter um perfil profissional. Neste perfil, ele poderá registrar suas competências técnicas (hard skills), soft skills, certificações, experiências anteriores e outras informações relevantes, facilitando a identificação de talentos e a colaboração entre áreas.

Este backend é responsável por toda a lógica de negócio, regras de acesso, e persistência dos dados.

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias:

* **Java 17:** Versão LTS da linguagem Java.
* **Spring Boot 3.5.5:** Framework principal para a construção da aplicação.
* **Spring Data JPA:** Para a persistência de dados de forma simplificada.
* **Maven:** Gerenciador de dependências e build do projeto.
* **MySQL:** Banco de dados relacional para o ambiente de produção.
* **H2 Database:** Banco de dados em memória para o ambiente de desenvolvimento local.
* **Flyway:** Ferramenta para versionamento e controle das migrações do banco de dados.

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter as seguintes ferramentas instaladas em sua máquina:

* [Java JDK 17](https://adoptium.net/pt-BR/temurin/releases/?version=17) (Recomendamos o uso do [SDKMAN!](https://sdkman.io/) para gerenciar as versões)
* [Git](https://git-scm.com/)
* [Maven](https://maven.apache.org/) (geralmente já vem com as IDEs)
* Uma IDE de sua preferência (IntelliJ, VS Code, Cursor, etc.)

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para ter o projeto rodando em sua máquina local.

**1. Clone o repositório:**
```bash
git clone [https://github.com/pedromattos11/backend-altave.git](https://github.com/pedromattos11/backend-altave.git)
