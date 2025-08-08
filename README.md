# Sistema de Gerenciamento de Estacionamento - Demo Park API

Uma API REST moderna e robusta para gerenciamento completo de estacionamento, desenvolvida com as melhores práticas e tecnologias atuais do ecossistema Java.

---

## Objetivo

Desenvolver uma API segura, eficiente e bem documentada que facilite a integração com front-ends e forneça gerenciamento completo de estacionamento, incluindo controle de usuários, clientes, vagas e operações de entrada/saída de veículos.

---

## Stack Tecnológica

### Backend
- **Java 17** - Versão LTS com recursos modernos
- **Spring Boot** - Framework principal para desenvolvimento rápido
- **Jakarta EE** - Especificações empresariais modernas
- **Spring Data JPA** - Abstração para persistência de dados
- **Spring MVC** - Framework web RESTful
- **Spring Security** - Segurança e autenticação

### Bibliotecas e Ferramentas
- **Lombok** - Redução de boilerplate code
- **Maven** - Gerenciamento de dependências e build
- **Git** - Controle de versão

### Banco de Dados
- **JPA/Hibernate** - ORM para mapeamento objeto-relacional
- Suporte para múltiplos SGBDs através do Spring Data JPA

---

## Funcionalidades Implementadas

### Gestão de Usuários
- ✅ Cadastro de novos usuários (público)
- ✅ Consulta de usuário por ID (restrito a administradores)
- ✅ Listagem de todos os usuários (restrito a administradores)
- ✅ Alteração de senha (usuário autenticado)
- ✅ Sistema de roles (`ROLE_ADMIN`, `ROLE_CLIENTE`)

### Autenticação e Segurança
- ✅ Controle de acesso por perfis de usuário

### Sistema de Auditoria
- ✅ Registro automático de data/hora de criação
- ✅ Registro de última modificação
- ✅ Rastreamento do usuário responsável pelas operações

### Testes
- ✅ Testes de integração end-to-end
- ✅ Validação dos fluxos principais da aplicação

---

## Recursos em Desenvolvimento

- **Módulo de Clientes** - Gestão completa de clientes
- **Gestão de Vagas** - Controle de vagas disponíveis/ocupadas
- **Controle de Estacionamento** - Registro de entrada/saída de veículos
- **Relatórios** - Dashboard e relatórios gerenciais
- **Documentação da API** - Swagger/OpenAPI

---

## ️ Arquitetura do Sistema

### Estrutura de Pastas
```plaintext
demo-park-api/ 
├── src/ 
│   ├── main/ 
│   │   ├── java/ 
│   │   │   └── com/carvalho/demo_park_api/
│   │   │       ├── config/             # Configurações
│   │   │       ├── entity/             # Entidades JPA
│   │   │       ├── exception/          # Gerenciamento de exceções
│   │   │       ├── repository/         # Repositórios Spring Data 
│   │   │       ├── service/            # Lógica de negócio 
│   │   │       ├── web/ 
│   │   │       │   ├── controller/     # Controllers REST
│   │   │       │   ├── dto/            # Data Transfer Objects
│   │   │       │   │   └── mapper/     # Conversão de tipos
│   │   │       │   └── exception/      # Gerenciamento de exceções
│   │   │       └── Main                # Ponto de partida 
│   │   └── resources/
│   │       └── application.properties
│   ├── test/                           # Testes automatizados
│   │   ├── java/
│   │   │   └── com/carvalho/demo_park_api/
│   │   │       ├── config/             # Configurações
│   │   │       └── Classe de teste     # Entidades JPA
│   │   └── resources/
│   │       ├── sql/
│   │       │   └── usuario/            # Construção de métodos para testes
│   │       └── application.properties
├── pom.xml                             # Dependências do Maven 
└── README.md
```

---

## Endpoints Disponíveis

#### Usuários
- `POST /api/v1/usuarios` - Criar usuário
- `GET /api/v1/usuarios/{id}` - Buscar usuário por ID
- `GET /api/v1/usuarios` - Listar todos os usuários
- `PATCH /api/v1/usuarios/{id}` - Alterar senha

---

## Como Executar o Projeto

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)
- Banco de dados configurado (MySQL, PostgreSQL, H2, etc.)

### Passos para Execução

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd demo-park-api
   ```

2. **Configure o banco de dados**
   Edite o arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://host:port/demo_park
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Execute a aplicação**
   ```bash
   # Via Maven
   ./mvnw spring-boot:run
   
   # Ou via IDE
   # Execute a classe principal com @SpringBootApplication
   ```

4. **Acesse a API**
    - URL base: `http://localhost:8080/api/v1`

---

## Configurações Importantes

### Timezone e Localização
- Configurado para timezone America/Sao_Paulo ( ou desejado )
- Locale pt_BR para formatação de datas e números ( ou desejado )

### Banco de Dados
- Pool de conexões otimizado
- Migração automática via JPA/Hibernate
- Scripts de dados iniciais

---

## Variáveis de Ambiente

### Banco de Dados
- DB_URL=jdbc:mysql://host:port/demo_park 
- DB_USERNAME=usuario 
- DB_PASSWORD=senha

---

## Executando os Testes

### Todos os testes
   ```bash
   # Via Maven
   ./mvnw test
   
   # Ou via IDE
   # Basta clicar no botão "Run Test" que fica no topo da classe a ser testada  
   # ou ao lado da annotation "@Test" para um teste específico
   ```
---

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

*Documentação atualizada em: 08/08/2025*
*Versão atual da API: 1.0.0*
