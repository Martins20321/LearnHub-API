# 🎓 Documentação Técnica - LearnHub API

Este documento descreve a infraestrutura técnica e as escolhas de design feitas para garantir que a **LearnHub API** seja uma plataforma educacional resiliente e bem estruturada. O foco atual é a refatoração do domínio para uma arquitetura orientada a documentos (NoSQL).

## 1. Stack & Ambiente

* **Linguagem:** Java 17 (LTS) - Utilizando Record Types e melhorias de performance.
* **Framework:** Spring Boot 3.5.8
* **Persistência:** Spring Data MongoDB.
* **Banco de Dados:** MongoDB.
* **Ambiente de Desenvolvimento:** WSL 2 (Windows Subsystem for Linux) e IntelliJ IDEA.
* **Validação:** Jakarta Bean Validation (Hibernate Validator).
* **Ferramenta de API:** Postman para testes manuais de endpoints.

## 2. Arquitetura em Camadas e Padrões

A API segue o padrão de **Layered Architecture**, garantindo o desacoplamento e a facilidade na manutenção:

* **Controller (Resources):** Porta de entrada da aplicação, responsável pelo gerenciamento de endpoints e contratos HTTP.
* **Service:** Concentração da lógica de negócio, gerenciando as regras de matrícula e acesso a cursos.
* **Repository:** Camada de persistência que utiliza Spring Data para comunicação com o MongoDB.
* **DTO (Data Transfer Objects):** Utilização de `records` para transferência de dados, protegendo as entidades de domínio.

## 3. Modelagem de Dados e Integridade

O sistema foi redesenhado para garantir consistência lógica no ambiente NoSQL, utilizando o diagrama de classes abaixo como referência:

### 🔹 Diagrama de Classes

> ![Diagrama de Classes](/docs/DiagramaClasses.png)

### 🔹 Relacionamentos e Estratégia NoSQL:
* **Course ↔ Lesson:** Relacionamento de **Composição**. No MongoDB, as aulas são embutidas (Embedding) no documento do curso, garantindo que o conteúdo seja recuperado de forma atômica.
* **User ↔ Enrollment ↔ Course:** A `Enrollment` atua como a ponte entre o aluno e o conteúdo, utilizando referências (Linking) por ID para manter a escalabilidade.
* **Simplificação do Domínio:** A entidade `Payment` foi removida para otimizar o fluxo principal de aprendizado e gestão de matrículas.

## 4. Padronização e Tratamento de Erros

A API utiliza um `@ControllerAdvice` para interceptar exceções e garantir respostas consistentes:

* **201 Created:** Retornado em cadastros bem-sucedidos (Usuários, Cursos, Matrículas).
* **204 No Content:** Utilizado em deleções confirmadas.
* **400 Bad Request:** Acionado por erros de validação ou integridade de dados.
* **404 Not Found:** Retornado quando um ID não é encontrado na base de dados.

## 5. Fluxos de Negócio (Atual)

| Operação | Endpoint | Validação Principal | Status Esperado |
| :--- | :--- | :--- | :--- |
| **Cadastro Usuário** | `POST /users` | Validação de Role e campos obrigatórios. | 201 Created |
| **Gestão de Cursos** | `POST /courses` | Vinculação com Category e CourseLevel. | 201 Created |
| **Consulta de Curso** | `GET /courses/{id}` | Retorno do curso com aulas embutidas. | 200 OK |
| **Matrícula** | `POST /enrollments` | Registro do momento da matrícula e status inicial. | 201 Created |

## 6. Execução Local

1. **Pré-requisitos:** JDK 17 e Maven instalados.
2. **Banco de Dados:** Certifique-se de que uma instância do MongoDB está rodando localmente ou via Atlas.
3. **Execução:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   
## 7. Próximas Evoluções

O projeto segue em desenvolvimento ativo. As próximas etapas incluem:

* **[ ]** Implementar testes automatizados (JUnit 5 e Mockito).

* **[ ]** Containerização total com Docker e Docker Compose.

* **[ ]** Adicionar documentação da API com Swagger (OpenAPI).

* **[ ]** Implementar autenticação e autorização com Spring Security e JWT. 