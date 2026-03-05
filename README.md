# GerenciaWireless

Um sistema web completo para gerenciamento de provedores de internet (ISPs), focando no controle de roteadores, endereços e clientes.

A aplicação é dividida em duas partes principais:
* **Frontend:** Construído com React, Vite e Lucide Icons.
* **Backend:** Construído com Java (Servlets puro), JDBC e PostgreSQL.

## Pré-requisitos
* **Docker Desktop** (para rodar a stack completa de forma mais simples).
* Git instalado.

## Como Executar Localmente (com Docker)

A maneira mais fácil e segura de levantar toda a aplicação (Banco de Dados + API + Frontend) é utilizando o Docker Compose:

1. Clone o repositório na sua máquina.
2. Abra o terminal na raiz do projeto (`GerenciaWireless`).
3. Execute o comando:
   ```bash
   docker-compose up --build -d
   ```
4. Aguarde a construção das imagens.
5. Em seu navegador, a aplicação estará disponível em `http://localhost`.

O banco de dados PostgreSQL será populado automaticamente e a API ficará acessível nos contêineres e exposta em `http://localhost:8080/GerenciaWireless`.

## Como Executar Localmente (Sem Docker)

Caso prefira rodar as peças individualmente em seu ambiente:
1. Certifique-se de possuir o Java JDK 11, Node.js e PostgreSQL instalados.
2. Inicialize seu Postgres com um banco de dados chamado `cadastroweb` e execute o script `./init.sql`.
3. Na pasta raiz, execute `./run-backend.sh` para testar, compilar e subir o Tomcat com as APIs.
4. Em outro terminal, na pasta `frontend`, rode `npm install` e `npm run dev`.
5. Acesse a URL que o Vite exibir (geralmente `http://localhost:5173`).
