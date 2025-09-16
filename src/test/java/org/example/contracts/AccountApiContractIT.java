package org.example.contracts;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountApiContractIT {

    private static OpenApiValidationFilter OAS;

    @LocalServerPort
    int port;

    @BeforeAll
    static void initOas() {
        var specPath = Paths.get("src","test","resources","openapi","openapi.yaml").toUri().toString();
        OAS = new OpenApiValidationFilter(specPath);
    }

    @Test
    void create_account_contract_and_status() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/";     // <- make sure no "/api" sneaks in

        var body = """
                {
                    "name": "Riz Kler",
                    "accountType": "personal"
                  }
      """;

        given()

                // add at request build chain
                .header("Authorization", "Bearer test-token") // if spec defines bearerAuth
  //              // or, if it uses an API key:
    //            .header("X-API-Key", "test-key")

                .filter(OAS)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                // IMPORTANT: path must match the spec: /v1/accounts (no /api)
                .post("/v1/accounts")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", not(emptyString()))
                .body("userId", equalTo("usr-ABCDEFG1"))
                .body("accountType", equalTo("current"))
                .body("currency", equalTo("GBP"))
                .body("balance", equalTo(0)); // BigDecimal.ZERO serialized as 0
    }
}
