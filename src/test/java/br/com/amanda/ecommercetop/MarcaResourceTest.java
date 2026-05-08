package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

// Testes de integracao da camada Resource de Marca.
@QuarkusTest
class MarcaResourceTest {

    @Test
    void deveCriarEFiltrarPorNome() {
        // Cria uma marca para validar POST.
        Long id = given()
            .contentType("application/json")
            .body("{\"nome\":\"TopFit\"}")
        .when()
            .post("/marcas")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        // Filtra por nome para validar GET /find.
        given()
        .when()
            .get("/marcas/find/{nome}", "Top")
        .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("TopFit"));

        // Atualiza a marca para validar PUT.
        given()
            .contentType("application/json")
            .body("{\"nome\":\"TopFit Pro\"}")
        .when()
            .put("/marcas/{id}", id)
        .then()
            .statusCode(204);

        // Confirma a atualizacao via GET/{id}.
        given()
        .when()
            .get("/marcas/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("TopFit Pro"));

        // Remove e valida que o registro nao existe mais.
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

    @Test
    void deveListarMarcas() {
        // Cria um item para garantir que a lista nao venha vazia.
        given()
            .contentType("application/json")
            .body("{\"nome\":\"NeoFit\"}")
        .when()
            .post("/marcas")
        .then()
            .statusCode(201);

        // GET simples deve retornar lista.
        given()
        .when()
            .get("/marcas")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }
}
