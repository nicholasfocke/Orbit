# Orbit

Simulador de gravidade e catálogo do Sistema Solar, construído como uma
aplicação **Java + Spring Boot + Thymeleaf**. Evolução do protótipo original
feito em HTML, CSS e JavaScript puro, mantendo o mesmo visual e as mesmas
funcionalidades, agora servidas por um backend em Spring.

## Integrantes da dupla

- Nicholas Focke
- Igor Rafael

## Descrição do projeto

O Orbit é um site que apresenta um simulador de gravidade newtoniana:

- **Página inicial**: apresentação do projeto, com uma animação do Sistema
  Solar em `<canvas>`.
- **Catálogo**: lista os oito planetas do Sistema Solar com seus dados
  físicos (massa, distância do Sol, temperatura, duração do dia). Os dados
  são fornecidos pelo `CatalogController`/`PlanetService` (Java) e renderizados
  no HTML com Thymeleaf (`th:each`, `th:text`).
- **Cenários**: tela de listagem e cadastro de cenários de simulação
  (nome, corpo central, quantidade de corpos, notas). Os cenários criados
  pelo formulário ficam guardados em uma lista em memória
  (`ScenarioService`) enquanto a aplicação está rodando.
- **Sandbox**: tela do simulador propriamente dito (em construção), com
  atalhos para criar e visualizar cenários.
- **Login / Cadastro / Esqueci minha senha**: telas de autenticação
  (apenas visuais nesta etapa, sem persistência de usuários).

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web (MVC)
- Thymeleaf
- HTML5 / CSS3
- JavaScript (canvas 2D para as animações)
- Maven (com o Maven Wrapper, `mvnw` / `mvnw.cmd`)

Não é utilizado banco de dados nesta etapa: os dados de cenários são
simulados e mantidos em uma lista em memória no `ScenarioService`.

## Como executar o projeto

Pré-requisitos: JDK 21 instalado.

```bash
# clonar o repositório
git clone <link-do-repositorio>
cd orbit

# executar com o Maven Wrapper (Windows)
mvnw.cmd spring-boot:run

# executar com o Maven Wrapper (Linux/Mac)
./mvnw spring-boot:run
```

Depois de iniciar, acesse no navegador:

```
http://localhost:8080
```

## Principais rotas disponíveis

| Método | Rota                | Descrição                                             |
|--------|---------------------|--------------------------------------------------------|
| GET    | `/`                 | Página inicial (dashboard)                             |
| GET    | `/catalog`          | Listagem dos planetas (dados vindos do Java)            |
| GET    | `/scenarios`        | Listagem dos cenários salvos                            |
| GET    | `/scenarios/novo`   | Formulário de criação de um novo cenário                |
| POST   | `/scenarios`        | Recebe o formulário e salva o cenário em memória        |
| GET    | `/scenarios/{id}`   | Detalhes de um cenário específico                       |
| GET    | `/sandbox`          | Tela do simulador / sandbox                             |
| GET    | `/login`            | Tela de login                                           |
| GET    | `/register`         | Tela de cadastro de usuário                             |
| GET    | `/forgot-password`  | Tela de recuperação de senha                            |

## Estrutura do projeto

```
src/main/java/com/orbit/
├── OrbitApplication.java
├── controller/
│   ├── HomeController.java       # páginas estáticas: index, login, register...
│   ├── CatalogController.java    # catálogo de planetas
│   └── ScenarioController.java   # listagem/cadastro/detalhes de cenários
├── model/
│   ├── Planet.java
│   └── Scenario.java
└── service/
    ├── PlanetService.java        # dados simulados dos planetas
    └── ScenarioService.java      # lista em memória dos cenários

src/main/resources/
├── templates/       # páginas Thymeleaf
└── static/
    ├── css/
    ├── js/
    └── img/
```
