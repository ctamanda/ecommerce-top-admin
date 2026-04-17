package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CorResourceTest {

    @Test
    void deveCriarEBuscarCor() {
        Long id = given()
            .contentType("application/json")
            .body("{\"nome\":\"Azul\",\"hex\":\"#0000FF\"}")
        .when()
            .post("/cores")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        given()
        .when()
            .get("/cores/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Azul"))
            .body("hex", is("#0000FF"));

        given()
            .contentType("application/json")
            .body("{\"nome\":\"Vermelho\",\"hex\":\"#FF0000\"}")
        .when()
            .put("/cores/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/cores/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Vermelho"))
            .body("hex", is("#FF0000"));

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
}
