package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

// Testes de integracao da camada Resource de Tamanho.
@QuarkusTest
class TamanhoResourceTest {

    @Test
    void deveCriarEFiltrarTamanho() {
        // Cria um tamanho para validar POST.
        Map<String, Object> dto = Map.of(
            "sigla", "M",
            "descricao", "Medio"
        );

        Long id = given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/tamanhos")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        // Busca por id para validar GET/{id}.
        given()
        .when()
            .get("/tamanhos/{id}", id)
        .then()
            .statusCode(200)
            .body("sigla", is("M"));

        // Filtra por descricao para validar GET /find.
        given()
        .when()
            .get("/tamanhos/find/{descricao}", "Med")
        .then()
            .statusCode(200)
            .body("size()", is(1));

        // Atualiza o tamanho para validar PUT.
        Map<String, Object> update = Map.of(
            "sigla", "G",
            "descricao", "Grande"
        );

        given()
            .contentType("application/json")
            .body(update)
        .when()
            .put("/tamanhos/{id}", id)
        .then()
            .statusCode(204);

        // Confirma a atualizacao via GET/{id}.
        given()
        .when()
            .get("/tamanhos/{id}", id)
        .then()
            .statusCode(200)
            .body("sigla", is("G"))
            .body("descricao", is("Grande"));

        // Remove e valida que o registro nao existe mais.
        given()
        .when()
            .delete("/tamanhos/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/tamanhos/{id}", id)
        .then()
            .statusCode(404);
    }

    @Test
    void deveListarTamanhos() {
        // Cria um item para garantir que a lista nao venha vazia.
        Map<String, Object> dto = Map.of(
            "sigla", "P",
            "descricao", "Pequeno"
        );

        given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/tamanhos")
        .then()
            .statusCode(201);

        // GET simples deve retornar lista.
        given()
        .when()
            .get("/tamanhos")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }
}
