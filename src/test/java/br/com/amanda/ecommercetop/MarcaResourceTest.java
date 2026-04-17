package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class MarcaResourceTest {

    @Test
    void deveCriarEFiltrarPorNome() {
        Long id = given()
            .contentType("application/json")
            .body("{\"nome\":\"TopFit\"}")
        .when()
            .post("/marcas")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        given()
        .when()
            .get("/marcas/find/{nome}", "Top")
        .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("TopFit"));

        given()
            .contentType("application/json")
            .body("{\"nome\":\"TopFit Pro\"}")
        .when()
            .put("/marcas/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/marcas/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("TopFit Pro"));

        given()
        .when()
            .delete("/marcas/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/marcas/{id}", id)
        .then()
            .statusCode(404);
    }
}
