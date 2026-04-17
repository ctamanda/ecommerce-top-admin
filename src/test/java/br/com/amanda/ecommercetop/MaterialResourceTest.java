package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class MaterialResourceTest {

    @Test
    void deveCriarEFiltrarMaterial() {
        Long id = given()
            .contentType("application/json")
            .body("{\"nome\":\"Poliamida\",\"composicao\":\"90% Poliamida 10% Elastano\"}")
        .when()
            .post("/materiais")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        given()
        .when()
            .get("/materiais/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Poliamida"));

        given()
        .when()
            .get("/materiais/find/{nome}", "Polia")
        .then()
            .statusCode(200)
            .body("size()", is(1));

        given()
            .contentType("application/json")
            .body("{\"nome\":\"Poliamida Premium\",\"composicao\":\"85% Poliamida 15% Elastano\"}")
        .when()
            .put("/materiais/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/materiais/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Poliamida Premium"));

        given()
        .when()
            .delete("/materiais/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/materiais/{id}", id)
        .then()
            .statusCode(404);
    }
}
