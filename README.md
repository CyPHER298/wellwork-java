# 🧠 GS - Gestão de Saúde Mental

API REST desenvolvida em **Spring Boot** para gerenciar usuários, metas, tarefas, lembretes, timers, alertas de crise
para neurodivergentes.

---

## 👥 **Integrantes do Grupo**

| Nome Completo | Função / Responsabilidade |
---
| **Henrique Batista de Souza - RM99742** | Líder do Projeto / Desenvolvedor Full-Stack (Java & ASP.NET / React.js &
React-Native & Typescript) |

| **Julia Lima Rodrigues - RM559781** | Desenvolvedora Back-end (Java & ASP.NET) / DevOps (Microsoft Azure) / QA &
Insurance |

| **Felipe Soares Gonçalves - RM559175** | Desenvolvedor Front-End (React.js) / Desenvolvedor Mobile (React-Native) /
Desenvolvedor IOT (Arduino) / Banco de Dados (OracleSQL) |

---

## ⚙️ **Como Rodar a Aplicação**

### ✅ Pré-requisitos

- **Java 17+**
- **Spring Boot**
- **OracleSQL**
- **Maven 3.8+**

### 🚀 Passos para execução

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/CyPHER298/wellwork-java.git
   ```

2. **Acessar o diretório do projeto:**
   ```bash
   cd wellwork-java
   ```

3. **Configurar o banco de dados no arquivo `application.properties`:**
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:xe
   spring.datasource.username=rm99742
   spring.datasource.password=290305
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
   ou
   ```yaml
   spring:
      datasource:
         url: jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
         username: rm99742
         password: 290305
         driver-class-name: oracle.jdbc.OracleDriver
    ```

4. **Executar o projeto:**
   ```bash
   mvn clean install
   mvn spring-boot:run

   ```

5. **Acessar a documentação Swagger:**
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 🔗 **Documentação da API (Swagger / OpenAPI)**

### **Principais Endpoints**

| Método     | Endpoint                 | Descrição                           |
|------------|--------------------------|-------------------------------------|
| **GET**    | `/api/usuario`           | Lista todos os usuarios             |
| **GET**    | `/api/usuario/{id}`      | Retorna um usuario pelo **id**      |
| **POST**   | `/api/usuario`           | Cadastra um novo cliente            |
| **PATCH**  | `/api/usuario/{id}`      | Atualiza um cliente                 |
| **DELETE** | `/api/usuario/{id}`      | Remove um cliente existente         |
| **GET**    | `/api/gestor`            | Lista todos os gestor               |
| **GET**    | `/api/gestor/{id}`       | Retorna um gestor pelo **id**       |
| **POST**   | `/api/gestor`            | Cadastra um novo gestor             |
| **PATCH**  | `/api/gestor/{id}`       | Atualiza um gestor existente        |
| **DELETE** | `/api/gestor/{id}`       | Remove um gestor existente          |
| **GET**    | `/api/lembrete`          | Lista todos os lembretes            |
| **GET**    | `/api/lembrete/{id}`     | Retorna uma lembretes pelo **id**   |
| **POST**   | `/api/lembrete`          | Cadastra uma nova lembretes         |
| **PATCH**  | `/api/lembrete/{id}`     | Atualiza uma lembretes existente    |
| **DELETE** | `/api/lembrete/{id}`     | Remove uma lembretes existente      |
| **GET**    | `/api/meta`              | Lista todos os metas                |
| **GET**    | `/api/meta/{id}`         | Retorna um meta pelo **id**         |
| **POST**   | `/api/meta`              | Cadastra um novo meta               |
| **PATCH**  | `/api/meta/{id}`         | Atualiza uma meta existente         |
| **DELETE** | `/api/meta/{id}`         | Remove um meta existente            |
| **GET**    | `/api/tarefa`            | Lista todos os metas                |
| **GET**    | `/api/tarefa/{id}`       | Retorna um tarefa pelo **id**       |
| **POST**   | `/api/tarefa`            | Cadastra um novo tarefa             |
| **PATCH**  | `/api/tarefa/{id}`       | Atualiza uma tarefa existente       |
| **DELETE** | `/api/tarefa/{id}`       | Remove um tarefa existente          |
| **GET**    | `/api/timer`              | Lista todos os metas                |
| **GET**    | `/api/timer/{id}`         | Retorna um timer pelo **id**         |
| **POST**   | `/api/timer`              | Cadastra um novo timer               |
| **PATCH**  | `/api/timer/{id}`         | Atualiza uma timer existente         |
| **DELETE** | `/api/timer/{id}`         | Remove um timer existente            |
| **GET**    | `/api/alerta-crise`      | Lista todos os metas                |
| **GET**    | `/api/alerta-crise/{id}` | Retorna um alerta-crise pelo **id** |
| **POST**   | `/api/alerta-crise`      | Cadastra um novo alerta-crise       |
| **PATCH**  | `/api/alerta-crise/{id}` | Atualiza uma alerta-crise existente |
| **DELETE** | `/api/alerta-crise/{id}` | Remove um alerta-crise existente    |

---

## 📜 **Observação**

Este projeto foi desenvolvido para fins acadêmicos na disciplina de **Desenvolvimento Web — Sprint 1 (Java)**.


## 🎲 QuerySQL
*Segue o script para criação das tabelas caso não esteja
sendo possível realizar as trocas de informações:*

```oracle
DROP TABLE AlertaCrise CASCADE CONSTRAINTS;
DROP TABLE Lembrete CASCADE CONSTRAINTS;
DROP TABLE Meta CASCADE CONSTRAINTS;
DROP TABLE Timer CASCADE CONSTRAINTS;
DROP TABLE Tarefa CASCADE CONSTRAINTS;
DROP TABLE Gestor CASCADE CONSTRAINTS;
DROP TABLE Usuario CASCADE CONSTRAINTS;

CREATE TABLE Usuario
(
   idUsuario      NUMBER(2) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   nome_usuario   VARCHAR2(60)        NOT NULL,
   email_usuario  VARCHAR2(40) UNIQUE NOT NULL,
   senha_usuario  VARCHAR2(10)        NOT NULL,
   cargo_usuario  VARCHAR2(30),
   acessibilidade VARCHAR2(50)
);

CREATE TABLE Gestor
(
   idGestor     NUMBER(2) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   nome_gestor  VARCHAR2(60)        NOT NULL,
   email_gestor VARCHAR2(40) UNIQUE NOT NULL,
   senha_gestor VARCHAR2(10)        NOT NULL,
   cargo_gestor VARCHAR2(30),
   departamento VARCHAR2(100)
);

CREATE TABLE Tarefa
(
   idTarefa         NUMBER(2) GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   idUsuario        NUMBER(2)    NOT NULL,
   titulo_tarefa    VARCHAR2(30) NOT NULL,
   descricao_tarefa VARCHAR2(100),
   dataHora_tarefa  TIMESTAMP,
   status_tarefa    VARCHAR2(20),
   CONSTRAINT chk_status_tarefa CHECK (status_tarefa IN ('pendente', 'concluida')),
   CONSTRAINT fk_tarefa_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario)
);

CREATE TABLE Timer
(
   idTimer      NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   idUsuario    NUMBER NOT NULL,
   tipo_timer   VARCHAR2(20),
   duracao      NUMBER,
   inicio       TIMESTAMP,
   fim          TIMESTAMP,
   status_timer VARCHAR2(20),
   CONSTRAINT chk_tipo_timer CHECK (tipo_timer IN ('foco', 'pausa')),
   CONSTRAINT fk_timer_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario)
);

CREATE TABLE Meta
(
   idMeta         NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   titulo_meta    VARCHAR2(50) NOT NULL,
   descricao_meta VARCHAR2(90),
   idUsuario      NUMBER,
   CONSTRAINT fk_meta_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario)
);

CREATE TABLE Lembrete
(
   idLembrete    NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   idUsuario     NUMBER NOT NULL,
   tipo_lembrete VARCHAR2(50),
   frequencia    NUMBER,
   ativo         CHAR(1) CHECK (ativo IN ('S', 'N')),
   CONSTRAINT fk_lembrete_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario)
);

CREATE TABLE AlertaCrise
(
   idAlertaCrise   NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   idUsuario       NUMBER NOT NULL,
   idGestor        NUMBER NOT NULL,
   dataHora_alerta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   status_alerta   VARCHAR2(20),
   CONSTRAINT chk_status_alerta CHECK (status_alerta IN ('ativo', 'resolvido')),
   CONSTRAINT fk_crise_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario),
   CONSTRAINT fk_crise_gestor FOREIGN KEY (idGestor) REFERENCES Gestor (idGestor)
);

```