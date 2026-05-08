package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

// Testes de integracao da camada Resource de Material.
@QuarkusTest
class MaterialResourceTest {

    @Test
    void deveCriarEFiltrarMaterial() {
        // Cria um material para validar POST.
        Long id = given()
            .contentType("application/json")
            .body("{\"nome\":\"Poliamida\",\"composicao\":\"90% Poliamida 10% Elastano\"}")
        .when()
            .post("/materiais")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        // Busca por id para validar GET/{id}.
        given()
        .when()
            .get("/materiais/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Poliamida"));

        // Filtra por nome para validar GET /find.
        given()
        .when()
            .get("/materiais/find/{nome}", "Polia")
        .then()
            .statusCode(200)
            .body("size()", is(1));

        // Atualiza o material para validar PUT.
        given()
            .contentType("application/json")
            .body("{\"nome\":\"Poliamida Premium\",\"composicao\":\"85% Poliamida 15% Elastano\"}")
        .when()
            .put("/materiais/{id}", id)
        .then()
            .statusCode(204);

        // Confirma a atualizacao via GET/{id}.
        given()
        .when()
            .get("/materiais/{id}", id)
        .then()
            .statusCode(200)
            .body("nome", is("Poliamida Premium"));

        // Remove e valida que o registro nao existe mais.
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

    @Test
    void deveListarMateriais() {
        // Cria um item para garantir que a lista nao venha vazia.
        given()
            .contentType("application/json")
            .body("{\"nome\":\"Algodao\",\"composicao\":\"100% Algodao\"}")
        .when()
            .post("/materiais")
        .then()
            .statusCode(201);

        // GET simples deve retornar lista.
        given()
        .when()
            .get("/materiais")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }
}
