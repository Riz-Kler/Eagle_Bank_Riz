package org.example.contracts;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.EagleBankRizApplication;
import org.example.security.TestSecurityConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {EagleBankRizApplication.class, TestSecurityConfig.class}
)
@ActiveProfiles("test")
class UserApiContractIT {

    private static OpenApiValidationFilter OAS_FILTER;

    @LocalServerPort
    int port;

    @BeforeAll
    static void initValidator() {
        // Use the test copy of the spec
        String specPath = Paths.get("src", "test", "resources", "openapi", "openapi.yaml")
                .toUri()
                .toString();
        OAS_FILTER = new OpenApiValidationFilter(specPath);
    }

    @BeforeAll
    static void initRestAssured(@LocalServerPort int port) {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/";     // <- make sure no "/api" sneaks in
    }


    @Test
    void createUser_contract_and_status() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        // Minimal request payload that the service accept
        var body = """
                {
                  "name": "Riz Kler",
                  "phoneNumber": "+44 7777 123456",
                  "address": "221B Baker Street, London",
                  "email": "riz@example.com"
                }
                """;

        given()
                .filter(OAS_FILTER)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                // IMPORTANT: match the spec's exact path; your errors show "/v1/users/"
                .post("/v1/users/")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("name", equalTo("Riz Kler"))
                .body("phoneNumber", equalTo("+44 7777 123456"))
                .body("address", equalTo("221B Baker Street, London"))
                .body("createdTimestamp", notNullValue())
                .body("updatedTimestamp", notNullValue());
    }
}
