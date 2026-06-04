# Roteiro de Apresentação — Ecommerce Top API

Guia passo a passo para demonstrar o projeto (duração sugerida: **10–15 min**).
Backend Quarkus, sem frontend — a demonstração é via **Swagger UI** e/ou o arquivo
[`api-requests.http`](api-requests.http).

---

## 0. Antes de começar (preparação)

```bash
# 1) Banco PostgreSQL rodando com o database "ecommerce-top-admin"
# 2) Subir a aplicação:
.\mvnw.cmd quarkus:dev
```

Deixe abertos:
- **Swagger UI**: <http://localhost:8080/q/swagger-ui>
- O arquivo `api-requests.http` (extensão *REST Client* do VSCode)
- Um terminal para mostrar os testes

> Dica: tenha o `api-requests.http` já com a sequência pronta para clicar "Send Request" em ordem.

---

## 1. Abertura (1 min) — o que é o projeto

> "É uma **API REST de e-commerce** de tops fitness, em **Java 21 + Quarkus**.
> Tem duas áreas: **administrador** (produtos, estoque, pedidos, pagamentos) e
> **cliente** (cadastro, carrinho, checkout, pagamento e histórico).
> Autenticação por **JWT** com perfis `ADMIN` e `CLIENTE`, e integração de
> pagamento com o **Mercado Pago**."

Mostre rapidamente a **estrutura de pacotes** (model, repository, service, resource, dto, mapper, client) — destacar a **arquitetura em camadas**.

---

## 2. Mostrar a documentação viva (1 min)

Abra o **Swagger UI**. Role pelos grupos de endpoints (auth, clientes, carrinho,
pedidos, estoque, pagamentos). Mensagem: *"toda a API é autodocumentada"*.

---

## 3. Login do administrador (1 min)

No `api-requests.http`, bloco **1**:
```
POST /auth/login   { "login": "admin", "senha": "admin123" }
```
> "O admin já é criado automaticamente no startup pelo `DataSeeder`. O login
> devolve um **JWT** que vai no header `Authorization: Bearer ...`."

---

## 4. Admin cadastra um produto e define estoque (2 min)

Blocos **2 e 3**:
- Cria modelo, tamanho, cor, sustentação, marca, material e depois o **Top** (`POST /tops`).
- Define o estoque: `PUT /estoque/{topId}  { "quantidade": 10 }`.

> "O produto é composto por vários atributos. O estoque é controlado à parte,
> com **quantidade** e **reservado** — o disponível é a diferença."

---

## 5. Cliente se cadastra e entra (1 min)

Bloco **4**:
- `POST /clientes/cadastro` (nome, email, senha) → o **e-mail vira o login**.
- `POST /auth/login` como cliente → novo JWT (perfil `CLIENTE`).
- `GET /clientes/perfil` para mostrar os dados do usuário autenticado.

> Mostre que **um cliente não acessa rota de admin** (ex.: `GET /pedidos` com o
> token do cliente retorna **403**).

---

## 6. Endereço, favoritos e carrinho (2 min)

Blocos **5 e 6**:
- `POST /enderecos` → o primeiro endereço já vira **principal**.
- `POST /favoritos/{topId}` (lista de desejos).
- `POST /carrinho/itens  { topId, quantidade: 2 }`.
- `GET /carrinho` → mostrar **itens, subtotais e valor total**.

> Destaque: o carrinho **valida o estoque disponível** ao adicionar/alterar
> (tentar mais do que existe retorna **409**).

---

## 7. Checkout com PIX (2 min) — o ponto alto

Bloco **7**:
- `POST /cliente/pedidos/checkout  { enderecoId, formaPagamento: "PIX" }`
  → pedido `AGUARDANDO_PAGAMENTO`, pagamento `PENDENTE` com **QR Code**.
- Mostre que o **estoque ficou reservado**: `GET /estoque/{topId}` → `reservado: 2`, `disponivel: 8`.

Bloco **8** — confirmação:
- **Modo simulado** (padrão, sem credenciais):
  `POST /cliente/pedidos/{id}/pagamento/confirmar-simulado`.
- **Modo real**: a confirmação chega pelo **webhook** do Mercado Pago.

Depois:
- `GET /cliente/pedidos/{id}` → pedido agora **PAGO**.
- `GET /estoque/{topId}` → **baixa automática** (quantidade 10 → 8, reservado 0).

> "Aqui está a regra central: **reserva** no checkout e **baixa** só após a
> **aprovação** do pagamento."

---

## 8. Admin acompanha e fecha o pedido (1 min)

Bloco **9**:
- `GET /pedidos` (lista), `PUT /pedidos/{id}/status  { "status": "ENVIADO" }`.
- `GET /pagamentos?forma=PIX` (consulta de pagamentos).

E o cliente vê o **histórico**: `GET /cliente/pedidos`.

---

## 9. Mostrar os testes (2 min) — credibilidade

No terminal:
```bash
.\mvnw.cmd test
```
> "**40 testes, 0 falhas.** Tem **testes unitários** da lógica de domínio
> (estoque, subtotais, preço congelado, validade de token) e **testes de
> integração** cobrindo cada fluxo: autenticação, recuperação de senha,
> carrinho, checkout PIX, pagamento com cartão, **cancelamento com estorno** e
> até o **webhook do Mercado Pago** (com o cliente HTTP mockado)."

Pontos que valem destacar se perguntarem:
- **Recuperação de senha**: token de uso único, expira em 1h, resposta genérica.
- **Estorno**: cancelar um pedido pago **devolve o estoque** e marca o pagamento `ESTORNADO`.
- **Idempotência**: webhook duplicado não dá baixa em dobro.

---

## 10. Fechamento (30 s)

> "Resumindo: a API cobre o ciclo completo de uma loja — do cadastro do produto
> à entrega — com **controle de estoque**, **pagamento integrado** e **segurança
> por perfil**, tudo coberto por testes automatizados."

---

### Perguntas prováveis e respostas curtas

| Pergunta | Resposta |
|----------|----------|
| Por que Quarkus? | Boot rápido, live reload, e foco em microsserviços/cloud. |
| Onde fica a senha? | Hash **BCrypt**; nunca em texto puro. |
| E os dados do cartão? | Nunca passam pelo backend — o front gera o **token** com o SDK do Mercado Pago (PCI). |
| Como testou pagamento sem credenciais? | **Modo simulado** + teste de webhook com o cliente HTTP **mockado**. |
| E se o pagamento for recusado? | Pedido fica `AGUARDANDO_PAGAMENTO` e o cliente pode cancelar (libera a reserva). |
