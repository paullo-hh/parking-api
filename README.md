# Sistema de Gerenciamento de Estacionamento

Este projeto consiste em uma API REST para gerenciamento de estacionamento, desenvolvida com Jakarta EE, Spring Data JPA, Spring MVC, Lombok e Java 21. A API possui autenticação via JWT e recursos que atendem às necessidades de cadastro, controle de vagas, veículos e entradas/saídas, além de gerenciamento de usuários e clientes.

---

## Objetivo

Desenvolver uma API segura, eficiente e bem documentada, facilitando integração com um front-end e gerenciamento completo do estacionamento, atendendo aos requisitos levantados junto ao cliente.

---

## Funcionalidades Implementadas até o momento

- Entidade `Usuario` com atributos essenciais e controle de roles (`ROLE_ADMIN`, `ROLE_CLIENTE`).
- Configuração básica de auditoria para registro de data/criação e última modificação, além do usuário responsável por essas ações.
- Implementação de autenticação via JWT com endpoints para login e validação do token.
- Cadastro de usuários com controle de acesso diferenciado entre administrador e cliente.
- Recuperação de informações de usuários, com restrições de acordo com o perfil do usuário autenticado.
- Envio de respostas detalhadas e documentação dos principais recursos disponíveis.
- Testes de integração ponta a ponta para garantir o funcionamento dos principais fluxos da API.

---

## Estrutura do Sistema

### Recursos principais já implementados
- **Usuários**
    - Criar usuário (sem necessidade de autenticação)
    - Consultar usuários pelo ID (restrito a administrador)
    - Listar todos os usuários (restrito a administrador)
    - Alterar senha (autorizado pelo próprio usuário)
- **Autenticação**
    - Endpoint para login que devolve JWT
- **Auditoria**
    - Registro automático de data/hora de criação e última modificação
    - Registro do usuário responsável por essas ações

### Recursos pendentes / a implementar
- Clientes
- Vagas de estacionamento
- Estacionamentos (entrada e saída de veículos)

---

## Requisitos Técnicos

- **Configuração de timezone e locale**: a API deverá estar alinhada ao timezone e locale do país de operação.
- **Segurança**: implementação de JWT para autenticação e autorização.
- **Banco de Dados**: conexão à base configurada para o ambiente de desenvolvimento.
- **Auditoria**: registro automático de criação e alterações dos registros, incluindo quem realizou a operação.

---

## Como executar o projeto

1. Clone este repositório.
2. Configure as credenciais do banco de dados no arquivo de propriedades (application.properties ou application.yml).
3. Execute a aplicação usando sua IDE ou o comando Maven/Gradle.
4. A API estará acessível na URL configurada (exemplo: `http://localhost:8080/api`).

---

## Documentação dos recursos

A documentação atual, gerada automaticamente ou manualmente, cobre:

- Endpoints de autenticação (/auth/login)
- Gerenciamento de usuários (/usuarios)
- Gerenciamento de clientes (/clientes) (a ser concluído)
- Controle de vagas (/vagas) (a ser desenvolvido)
- Controle de estacionamento (/estacionamentos) (a ser implementado)

---

## Testes

- Testes de integração ponta a ponta estão implementados para validar os fluxos principais.
- Recomenda-se a execução de testes automatizados antes de cada implantação.

---

## Observações finais

Este é o primeiro versionamento do sistema. Futuramente, espera-se:
- Inclusão de mais recursos para gerenciamento de vagas e estacionamentos.
- Melhoria na documentação e nos testes.
- Otimizações de performance e segurança adicional.

---

**Para dúvidas ou sugestões, fique à vontade para ajudar.**

---

*Obs: Este README será atualizado conforme novos recursos forem implementados e a documentação evoluir.*