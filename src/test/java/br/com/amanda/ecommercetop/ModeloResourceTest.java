package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ModeloResourceTest {

    @Test
    void deveCriarEFiltrarModelo() {
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

        given()
        .when()
            .get("/modelos/{id}", id)
        .then()
            .statusCode(200)
            .body("categoria", is("ESPORTIVO"));

        given()
        .when()
            .get("/modelos/find/{nome}", "Energ")
        .then()
            .statusCode(200)
            .body("size()", is(1));

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

        given()
        .when()
            .get("/modelos/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Energy X"))
            .body("categoria", is("PREMIUM"));

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
}
