# Ecommerce Top — API

API REST de um e-commerce de **tops** (moda fitness) com área administrativa e área do cliente, construída em **Quarkus**. O projeto é **somente backend**: a interface de uso e demonstração é feita via Swagger UI e pelo arquivo de requisições [`api-requests.http`](api-requests.http).

---

## 1. Descrição do projeto

O sistema cobre o ciclo completo de uma loja virtual:

- **Administrador**: gestão de produtos, estoque, usuários, pedidos e pagamentos.
- **Cliente**: cadastro, autenticação, perfil, endereços, lista de desejos, carrinho, checkout, pagamento (PIX / crédito / débito) e histórico de compras.
- **Regras de negócio**: controle de estoque com **reserva** no checkout e **baixa automática** após a aprovação do pagamento, **estorno** com devolução de estoque no cancelamento de pedidos pagos, e recuperação de senha por e-mail com token de uso único.

Autenticação por **JWT** com dois perfis: `ADMIN` e `CLIENTE`.

---

## 2. Tecnologias utilizadas

| Categoria | Tecnologia |
|-----------|-----------|
| Linguagem | Java 21 |
| Framework | Quarkus 3.31 |
| Persistência | Hibernate ORM + Panache (repository pattern) |
| Banco (dev) | PostgreSQL |
| Banco (testes) | H2 em memória |
| Segurança | SmallRye JWT + BCrypt (jBCrypt) |
| Validação | Hibernate Validator (Bean Validation) |
| Serialização | Jackson (quarkus-rest-jackson) |
| Pagamento | Mercado Pago via REST Client (quarkus-rest-client-jackson) |
| E-mail | quarkus-mailer |
| Documentação | SmallRye OpenAPI / Swagger UI |
| Testes | JUnit 5, REST Assured, Mockito (quarkus-junit5-mockito) |
| Build | Maven (wrapper incluído) |

---

## 3. Arquitetura

Arquitetura em camadas, organizada por pacote:

```
br.com.amanda.ecommercetop
├── model        → entidades JPA (Usuario, Produto/Top, Estoque, Endereco,
│                   Carrinho/ItemCarrinho, Pedido/ItemPedido, Pagamento,
│                   Favorito, PasswordResetToken) + enums de status
├── repository   → repositórios Panache (acesso a dados)
├── service      → regras de negócio (interface + Impl quando há contrato)
├── resource     → endpoints REST (JAX-RS), validação e autorização (@RolesAllowed)
├── dto          → records de request/response com Bean Validation
├── mapper       → conversão entidade ↔ DTO (classes estáticas)
├── client       → cliente REST tipado do Mercado Pago
└── bootstrap    → DataSeeder (admin + estoque inicial no startup)
```

**Fluxo de uma requisição:** `Resource` (valida DTO + checa perfil) → `Service` (regra de negócio, transação) → `Repository` (Panache) → `Mapper` (monta o DTO de resposta).

Decisões de modelagem relevantes:

- `Estoque` mantém `quantidade` e `reservado`; **disponível = quantidade − reservado**.
- `Pedido` guarda um **snapshot textual** do endereço e o **preço congelado** de cada item (`ItemPedido.precoUnitario`), preservando o histórico mesmo que produto/endereço mudem depois.
- `Pagamento` é 1:1 com `Pedido` e guarda o id externo do Mercado Pago e os dados do PIX (QR Code / copia e cola).

---

## 4. Como executar

Pré-requisitos: **JDK 21** e (para o modo dev) **PostgreSQL** rodando.

### Banco de dados (dev)

Crie o banco esperado pela configuração ([`application.properties`](src/main/resources/application.properties)):

```sql
CREATE DATABASE "ecommerce-top-admin";
-- usuário: postgres / senha: 123456 (ajuste conforme seu ambiente)
```

O schema é recriado automaticamente no boot (`drop-and-create`) e populado com dados de exemplo (`import.sql`).

### Rodar em modo desenvolvimento (live reload)

```bash
./mvnw quarkus:dev          # Linux/Mac
.\mvnw.cmd quarkus:dev      # Windows (PowerShell)
```

A API sobe em `http://localhost:8080`.

- **Swagger UI**: <http://localhost:8080/q/swagger-ui>
- **Dev UI**: <http://localhost:8080/q/dev/>

### Empacotar e rodar

```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

---

## 5. Como rodar os testes

```bash
./mvnw test          # Linux/Mac
.\mvnw.cmd test      # Windows
```

Os testes usam **H2 em memória** (não precisam de PostgreSQL). A suíte tem **testes unitários puros** (lógica de domínio, sem subir a aplicação) e **testes de integração** (`@QuarkusTest` + REST Assured) cobrindo cada parte do fluxo:

| Classe | Cobertura |
|--------|-----------|
| `unit/DominioUnitTest` | cálculo de estoque, subtotais, preço congelado, validade de token |
| `ClienteAuthTest` | cadastro, login, troca e recuperação de senha |
| `ClienteCatalogoTest` | endereços, favoritos, carrinho, consulta de produto |
| `PagamentoPedidoTest` | cartão, cancelamento, estorno, consultas admin |
| `EcommerceFlowTest` | fluxo PIX completo de ponta a ponta |
| `MercadoPagoWebhookTest` | aprovação via webhook (Mercado Pago real, mockado) |
| `*ResourceTest` | CRUD do catálogo (produtos e atributos) |

---

## 6. Usuários iniciais

No startup, o [`DataSeeder`](src/main/java/br/com/amanda/ecommercetop/bootstrap/DataSeeder.java) cria automaticamente um administrador (se ainda não existir):

| Perfil | Login | Senha | Observação |
|--------|-------|-------|------------|
| `ADMIN` | `admin` | `admin123` | configurável por variáveis `SEED_ADMIN_LOGIN`, `SEED_ADMIN_SENHA`, `SEED_ADMIN_EMAIL` |

Clientes são criados pelo endpoint público `POST /clientes/cadastro` (o **e-mail vira o login**).

Autenticação: `POST /auth/login` retorna `{ "token": "...", "type": "Bearer" }`. Envie nas chamadas protegidas o header `Authorization: Bearer <token>`.

---

## 7. Fluxo de demonstração

Sequência ponta a ponta (também disponível, pronta para executar, em [`api-requests.http`](api-requests.http) com a extensão *REST Client* do VSCode):

1. **Admin** loga (`admin` / `admin123`).
2. **Admin** cria um produto (modelo, tamanho, cor, sustentação, marca, material → `POST /tops`).
3. **Admin** define o estoque (`PUT /estoque/{topId}`).
4. **Cliente** se cadastra (`POST /clientes/cadastro`) e faz login.
5. **Cliente** cadastra endereço (`POST /enderecos`).
6. **Cliente** adiciona produto ao carrinho (`POST /carrinho/itens`).
7. **Cliente** faz checkout PIX (`POST /cliente/pedidos/checkout`) → pedido `AGUARDANDO_PAGAMENTO`, estoque **reservado**.
8. Pagamento é **confirmado** (webhook real ou `POST .../confirmar-simulado`) → pedido `PAGO`, estoque com **baixa**.
9. **Admin** acompanha e atualiza o status (`PUT /pedidos/{id}/status` → `EM_PREPARACAO` → `ENVIADO` → `ENTREGUE`).
10. **Cliente** consulta o **histórico** (`GET /cliente/pedidos`).

---

## 8. Endpoints principais

> Público = sem token · 🔒 ADMIN · 👤 CLIENTE

### Autenticação e cliente
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| POST | `/auth/login` | Público | Login (retorna JWT) |
| POST | `/clientes/cadastro` | Público | Cadastro de cliente |
| GET/PUT | `/clientes/perfil` | 👤/Autent. | Ver / atualizar perfil |
| PUT | `/clientes/senha` | Autent. | Trocar senha |
| POST | `/clientes/esqueci-senha` | Público | Solicitar recuperação |
| POST | `/clientes/redefinir-senha` | Público | Redefinir com token |

### Catálogo e estoque
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| GET | `/tops`, `/tops/{id}` | Público | Catálogo de produtos |
| POST/PUT/DELETE | `/tops` | Público* | CRUD de produtos |
| GET | `/produtos/{id}/disponibilidade`, `/produtos/{id}/preco` | Público | Consulta de disponibilidade/preço |
| GET | `/estoque`, `/estoque/{topId}`, `/estoque/sem-estoque` | 🔒 | Consultar estoque |
| PUT | `/estoque/{topId}` | 🔒 | Definir estoque manual |

### Cliente — endereços, favoritos, carrinho
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| GET/POST/PUT/DELETE | `/enderecos` | 👤 | CRUD de endereços |
| PUT | `/enderecos/{id}/principal` | 👤 | Definir endereço principal |
| GET/POST/DELETE | `/favoritos`, `/favoritos/{topId}` | 👤 | Lista de desejos |
| GET | `/carrinho` | 👤 | Ver carrinho |
| POST/PUT/DELETE | `/carrinho/itens` | 👤 | Adicionar / alterar / remover itens |
| DELETE | `/carrinho` | 👤 | Esvaziar carrinho |

### Pedidos e pagamentos
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| POST | `/cliente/pedidos/checkout` | 👤 | Finalizar compra |
| GET | `/cliente/pedidos`, `/cliente/pedidos/{id}` | 👤 | Histórico / detalhe |
| POST | `/cliente/pedidos/{id}/cancelar` | 👤 | Cancelar pedido |
| GET | `/pedidos`, `/pedidos/{id}` | 🔒 | Listar / ver pedidos |
| PUT | `/pedidos/{id}/status` | 🔒 | Atualizar status |
| GET | `/pagamentos`, `/pagamentos/estornos` | 🔒 | Consultar pagamentos |
| POST | `/webhooks/mercadopago` | Público | Webhook de pagamento |

\* O CRUD do catálogo segue aberto como no projeto base; pode ser restrito a `ADMIN` se necessário.

---

## 9. Integração Mercado Pago

A integração com a API de pagamentos do Mercado Pago é feita por um **REST Client tipado** ([`MercadoPagoClient`](src/main/java/br/com/amanda/ecommercetop/client/MercadoPagoClient.java)). Há dois modos:

### Modo real — vinculando sua conta

1. Pegue as credenciais da sua aplicação no painel: <https://www.mercadopago.com.br/developers> → **Suas integrações** → **Credenciais** (use as de **teste** para sandbox: `Access Token` começa com `TEST-`).
2. Copie o modelo [`run-mercadopago.example.ps1`](run-mercadopago.example.ps1) para `run-mercadopago.ps1` (esse fica fora do versionamento), cole nele o **Access Token** e a **Public Key**, e execute:
   ```powershell
   Copy-Item run-mercadopago.example.ps1 run-mercadopago.ps1
   ```
   ```powershell
   .\run-mercadopago.ps1
   ```
   (O script só exporta as variáveis nessa janela e sobe o app em modo real — **não afeta os testes**.) Alternativamente, exporte manualmente:
   ```powershell
   $env:MERCADOPAGO_ENABLED = "true"
   $env:MERCADOPAGO_ACCESS_TOKEN = "TEST-xxxxxxxx"
   $env:MERCADOPAGO_PUBLIC_KEY  = "TEST-xxxxxxxx"
   $env:MERCADOPAGO_NOTIFICATION_URL = "https://SEU-NGROK.ngrok-free.app/webhooks/mercadopago"
   .\mvnw.cmd quarkus:dev
   ```
3. Para o **webhook** alcançar sua máquina local, exponha a porta com um túnel:
   ```powershell
   ngrok http 8080
   ```
   Use a URL https gerada em `MERCADOPAGO_NOTIFICATION_URL` e cadastre-a no painel do Mercado Pago (Webhooks → evento *Pagamentos*).

> **Sobre o `notification_url`:** o Mercado Pago só aceita URL **pública** (rejeita `localhost`). A aplicação detecta isso e **omite** o `notification_url` quando ele aponta para `localhost`/`127.0.0.1` — assim o PIX é gerado normalmente em ambiente local. A confirmação automática (pedido → `PAGO` via webhook) só acontece quando você usa uma URL pública (ngrok). Sem ela, gere o QR e acompanhe o pagamento manualmente.

> **E-mail do pagador:** o MP bloqueia e-mails `@testuser.com` no PIX. Cadastre clientes de teste com e-mail comum (ex.: `@gmail.com`).

Fluxo:
1. No checkout, o backend cria a cobrança (`POST https://api.mercadopago.com/v1/payments`) com pagador (e-mail/nome) e, no PIX, validade do QR de 30 min.
2. **PIX**: retorna QR Code e código copia-e-cola reais (expostos em `GET /cliente/pedidos/{id}/pagamento`).
3. **Crédito/Débito**: o front gera o `token` e o `paymentMethodId` do cartão com o SDK do Mercado Pago (PCI — o backend nunca recebe o número do cartão). Para **testar sem frontend**, gere um token de cartão de teste: `POST /mercadopago/card-token` (autenticado) com `numero`, `mesValidade`, `anoValidade`, `cvv`, `nomeTitular` e use o token retornado no checkout.
4. O Mercado Pago notifica `POST /webhooks/mercadopago`; o backend consulta o status real e, se aprovado, marca o pedido como `PAGO` e dá **baixa no estoque**.

### Modo simulado (padrão, sem credenciais)
Com `MERCADOPAGO_ENABLED=false` o sistema gera uma cobrança fictícia para permitir rodar o fluxo localmente:
- **PIX**: gera um QR Code/código fake e fica `PENDENTE`; confirme com `POST /cliente/pedidos/{id}/pagamento/confirmar-simulado`.
- **Cartão**: aprova na hora se um `token` for informado.

---

## 10. Recuperação de senha

1. O cliente solicita: `POST /clientes/esqueci-senha` com o e-mail. A resposta é **sempre genérica** (não revela se o e-mail existe).
2. O sistema gera um **token de uso único** (`PasswordResetToken`), válido por **1 hora**, e envia o link por e-mail (`quarkus-mailer`).
3. O cliente acessa o link e redefine a senha: `POST /clientes/redefinir-senha` com `{ token, novaSenha }`.
4. O token é invalidado após o uso; expirado ou já usado retorna `400`.

**E-mail:** em **dev/teste** o mailer roda em **modo mock** (o token é apenas registrado no log, nada é enviado). Para envio real, configure SMTP:

```properties
quarkus.mailer.host=smtp.seu-provedor.com
quarkus.mailer.port=587
quarkus.mailer.username=...
quarkus.mailer.password=...
quarkus.mailer.start-tls=REQUIRED
%dev.quarkus.mailer.mock=false
```

---

### Estrutura de status

**Pedido:** `AGUARDANDO_PAGAMENTO` → `PAGO` → `EM_PREPARACAO` → `ENVIADO` → `ENTREGUE` · `CANCELADO`
**Pagamento:** `PENDENTE` → `APROVADO` · `RECUSADO` · `CANCELADO` · `ESTORNADO`
