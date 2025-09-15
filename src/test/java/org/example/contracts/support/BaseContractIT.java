package org.example.contracts.support;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.RestAssured;
import org.example.config.TestSecurityConfig;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestSecurityConfig.class)
public abstract class BaseContractIT {

    private static OpenApiValidationFilter VALIDATOR;

    @LocalServerPort
    int port;

    @BeforeAll
    static void setUpOpenApi() {
        // Contract is test resource: src/test/resources/openapi/openapi.yaml
        VALIDATOR = new OpenApiValidationFilter("openapi/openapi.yaml");
    }

    protected OpenApiValidationFilter validator() {
        return VALIDATOR;
    }

    protected void restAssuredPort() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }
}
