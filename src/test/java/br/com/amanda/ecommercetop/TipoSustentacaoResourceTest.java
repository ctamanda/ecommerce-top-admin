package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TipoSustentacaoResourceTest {

    @Test
    void deveCriarEFiltrarTipoSustentacao() {
        Map<String, Object> dto = Map.of(
            "descricao", "Suporte alto",
            "nivel", "ALTA"
        );

        Long id = given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/tiposustentacoes")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        given()
        .when()
            .get("/tiposustentacoes/{id}", id)
        .then()
            .statusCode(200)
            .body("nivel", is("ALTA"));

        given()
        .when()
            .get("/tiposustentacoes/find/{descricao}", "Suporte")
        .then()
            .statusCode(200)
            .body("size()", is(1));

        Map<String, Object> update = Map.of(
            "descricao", "Suporte leve",
            "nivel", "LEVE"
        );

        given()
            .contentType("application/json")
            .body(update)
        .when()
            .put("/tiposustentacoes/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/tiposustentacoes/{id}", id)
        .then()
            .statusCode(200)
            .body("nivel", is("LEVE"))
            .body("descricao", is("Suporte leve"));

        given()
        .when()
            .delete("/tiposustentacoes/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/tiposustentacoes/{id}", id)
        .then()
            .statusCode(404);
    }
}
