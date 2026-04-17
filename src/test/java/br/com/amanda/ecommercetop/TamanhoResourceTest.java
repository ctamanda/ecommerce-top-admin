package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TamanhoResourceTest {

    @Test
    void deveCriarEFiltrarTamanho() {
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

        given()
        .when()
            .get("/tamanhos/{id}", id)
        .then()
            .statusCode(200)
            .body("sigla", is("M"));

        given()
        .when()
            .get("/tamanhos/find/{descricao}", "Med")
        .then()
            .statusCode(200)
            .body("size()", is(1));

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

        given()
        .when()
            .get("/tamanhos/{id}", id)
        .then()
            .statusCode(200)
            .body("sigla", is("G"))
            .body("descricao", is("Grande"));

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
}
