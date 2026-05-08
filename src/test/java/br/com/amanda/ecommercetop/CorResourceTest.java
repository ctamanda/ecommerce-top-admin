package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

// Testes de integracao da camada Resource de Cor.
@QuarkusTest
class CorResourceTest {

    @Test
    void deveCriarEBuscarCor() {
        // Cria uma cor valida para testar POST e retornos.
        Long id = given()
            .contentType("application/json")
            .body("{\"nome\":\"Azul\",\"hex\":\"#0000FF\"}")
        .when()
            .post("/cores")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        // Busca por id para validar GET/{id}.
        given()
        .when()
            .get("/cores/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Azul"))
            .body("hex", is("#0000FF"));

        // Atualiza a cor para validar PUT.
        given()
            .contentType("application/json")
            .body("{\"nome\":\"Vermelho\",\"hex\":\"#FF0000\"}")
        .when()
            .put("/cores/{id}", id)
        .then()
            .statusCode(204);

        // Confirma a atualizacao via GET/{id}.
        given()
        .when()
            .get("/cores/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Vermelho"))
            .body("hex", is("#FF0000"));

        // Remove e valida que o registro nao existe mais.
        given()
        .when()
            .delete("/cores/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/cores/{id}", id)
        .then()
            .statusCode(404);
    }

    @Test
    void deveListarCores() {
        // Cria um item para garantir que a lista nao venha vazia.
        given()
            .contentType("application/json")
            .body("{\"nome\":\"Verde\",\"hex\":\"#00FF00\"}")
        .when()
            .post("/cores")
        .then()
            .statusCode(201);

        // GET simples deve retornar lista.
        given()
        .when()
            .get("/cores")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }
}
