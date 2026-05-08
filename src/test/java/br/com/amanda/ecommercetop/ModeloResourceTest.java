package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

// Testes de integracao da camada Resource de Modelo.
@QuarkusTest
class ModeloResourceTest {

    @Test
    void deveCriarEFiltrarModelo() {
        // Cria um modelo para validar POST.
        Map<String, Object> dto = Map.of(
            "nome", "Energy",
            "descricao", "Modelo base",
            "categoria", "ESPORTIVO"
        );

        Long id = given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/modelos")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        // Busca por id para validar GET/{id}.
        given()
        .when()
            .get("/modelos/{id}", id)
        .then()
            .statusCode(200)
            .body("categoria", is("ESPORTIVO"));

        // Filtra por nome para validar GET /find.
        given()
        .when()
            .get("/modelos/find/{nome}", "Energ")
        .then()
            .statusCode(200)
            .body("size()", is(1));

        // Atualiza o modelo para validar PUT.
        Map<String, Object> update = Map.of(
            "nome", "Energy X",
            "descricao", "Modelo atualizado",
            "categoria", "PREMIUM"
        );

        given()
            .contentType("application/json")
            .body(update)
        .when()
            .put("/modelos/{id}", id)
        .then()
            .statusCode(204);

        // Confirma a atualizacao via GET/{id}.
        given()
        .when()
            .get("/modelos/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Energy X"))
            .body("categoria", is("PREMIUM"));

        // Remove e valida que o registro nao existe mais.
        given()
        .when()
            .delete("/modelos/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/modelos/{id}", id)
        .then()
            .statusCode(404);
    }

    @Test
    void deveListarModelos() {
        // Cria um item para garantir que a lista nao venha vazia.
        Map<String, Object> dto = Map.of(
            "nome", "Classic",
            "descricao", "Modelo tradicional",
            "categoria", "BASICO"
        );

        given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/modelos")
        .then()
            .statusCode(201);

        // GET simples deve retornar lista.
        given()
        .when()
            .get("/modelos")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }
}
