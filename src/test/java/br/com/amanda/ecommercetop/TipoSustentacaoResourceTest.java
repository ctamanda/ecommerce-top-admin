package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

// Testes de integracao da camada Resource de TipoSustentacao.
@QuarkusTest
class TipoSustentacaoResourceTest {

    @Test
    void deveCriarEFiltrarTipoSustentacao() {
        // Cria um tipo para validar POST.
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

        // Busca por id para validar GET/{id}.
        given()
        .when()
            .get("/tiposustentacoes/{id}", id)
        .then()
            .statusCode(200)
            .body("nivel", is("ALTA"));

        // Filtra por descricao para validar GET /find.
        given()
        .when()
            .get("/tiposustentacoes/find/{descricao}", "Suporte")
        .then()
            .statusCode(200)
            .body("descricao", hasItem("Suporte alto"));

        // Atualiza o tipo para validar PUT.
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

        // Confirma a atualizacao via GET/{id}.
        given()
        .when()
            .get("/tiposustentacoes/{id}", id)
        .then()
            .statusCode(200)
            .body("nivel", is("LEVE"))
            .body("descricao", is("Suporte leve"));

        // Remove e valida que o registro nao existe mais.
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

    @Test
    void deveListarTiposSustentacao() {
        // Cria um item para garantir que a lista nao venha vazia.
        Map<String, Object> dto = Map.of(
            "descricao", "Suporte medio",
            "nivel", "MEDIA"
        );

        given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/tiposustentacoes")
        .then()
            .statusCode(201);

        // GET simples deve retornar lista.
        given()
        .when()
            .get("/tiposustentacoes")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }
}
