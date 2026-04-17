package br.com.amanda.ecommercetop;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TopResourceTest {

    @Test
    void deveCriarEBuscarTop() {
        Long modeloId = criarModelo();
        Long tamanhoId = criarTamanho();
        Long corId = criarCor();
        Long tipoSustentacaoId = criarTipoSustentacao();
        Long marcaId = criarMarca();
        Long materialId = criarMaterial();
        Long materialExtraId = criarMaterialExtra();

        Map<String, Object> dto = new HashMap<>();
        dto.put("nome", "Top Energy");
        dto.put("descricao", "Top esportivo");
        dto.put("preco", 129.9);
        dto.put("codigo", "TP-001");
        dto.put("modeloId", modeloId);
        dto.put("tamanhoId", tamanhoId);
        dto.put("corId", corId);
        dto.put("tipoSustentacaoId", tipoSustentacaoId);
        dto.put("marcaId", marcaId);
        dto.put("materialIds", Set.of(materialId));

        Map<String, Object> ficha = new HashMap<>();
        ficha.put("peso", 0.25);
        ficha.put("elasticidade", "Alta");
        ficha.put("costura", "Reforcada");
        dto.put("fichaTecnica", ficha);

        Long id = given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/tops")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");

        given()
        .when()
            .get("/tops/{id}", id)
        .then()
            .statusCode(200)
            .body("codigo", is("TP-001"))
            .body("fichaTecnica", notNullValue());

        given()
        .when()
            .get("/tops/find/{codigo}", "TP-001")
        .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].codigo", is("TP-001"));

        Map<String, Object> update = new HashMap<>();
        update.put("nome", "Top Energy X");
        update.put("descricao", "Top esportivo atualizado");
        update.put("preco", 149.9);
        update.put("codigo", "TP-001-ALT");
        update.put("modeloId", modeloId);
        update.put("tamanhoId", tamanhoId);
        update.put("corId", corId);
        update.put("tipoSustentacaoId", tipoSustentacaoId);
        update.put("marcaId", marcaId);
        update.put("materialIds", Set.of(materialId, materialExtraId));

        Map<String, Object> fichaUpdate = new HashMap<>();
        fichaUpdate.put("peso", 0.27);
        fichaUpdate.put("elasticidade", "Media");
        fichaUpdate.put("costura", "Dupla");
        update.put("fichaTecnica", fichaUpdate);

        given()
            .contentType("application/json")
            .body(update)
        .when()
            .put("/tops/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/tops/{id}", id)
        .then()
            .statusCode(200)
            .body("codigo", is("TP-001-ALT"))
            .body("fichaTecnica.elasticidade", is("Media"));

        given()
        .when()
            .delete("/tops/{id}", id)
        .then()
            .statusCode(204);

        given()
        .when()
            .get("/tops/{id}", id)
        .then()
            .statusCode(404);
    }

    private Long criarModelo() {
        Map<String, Object> dto = Map.of(
            "nome", "Energy",
            "descricao", "Modelo base",
            "categoria", "ESPORTIVO"
        );
        return given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/modelos")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");
    }

    private Long criarTamanho() {
        Map<String, Object> dto = Map.of(
            "sigla", "M",
            "descricao", "Medio"
        );
        return given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/tamanhos")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");
    }

    private Long criarCor() {
        return given()
            .contentType("application/json")
            .body("{\"nome\":\"Preto\",\"hex\":\"#000000\"}")
        .when()
            .post("/cores")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");
    }

    private Long criarTipoSustentacao() {
        Map<String, Object> dto = Map.of(
            "descricao", "Suporte alto",
            "nivel", "ALTA"
        );
        return given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/tiposustentacoes")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");
    }

    private Long criarMarca() {
        return given()
            .contentType("application/json")
            .body("{\"nome\":\"LinhaFit\"}")
        .when()
            .post("/marcas")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");
    }

    private Long criarMaterial() {
        Map<String, Object> dto = Map.of(
            "nome", "Poliamida",
            "composicao", "90% Poliamida 10% Elastano"
        );
        return given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/materiais")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");
    }

    private Long criarMaterialExtra() {
        Map<String, Object> dto = Map.of(
            "nome", "Elastano",
            "composicao", "100% Elastano"
        );
        return given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/materiais")
        .then()
            .statusCode(201)
            .extract().jsonPath().getLong("id");
    }
}
