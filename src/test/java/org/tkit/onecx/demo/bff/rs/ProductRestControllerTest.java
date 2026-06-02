package org.tkit.onecx.demo.bff.rs;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import io.quarkiverse.mockserver.test.InjectMockServerClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ProductRestControllerTest extends AbstractTest {
    @InjectMockServerClient
    MockServerClient mockServerClient;

    @AfterEach
    void resetMocks() {
        mockServerClient.clear(MOCK_ID);
    }

    @Test
    void searchProductItemsTest() {
        // unauthorized
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body("{}")
                .post("/products/search")
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());

        // mock backend
        mockServerClient.when(
                HttpRequest.request()
                        .withPath("/internal/products/search")
                        .withMethod("POST"))
                .withId(MOCK_ID).respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{}"));

        given()
                .when()
                .auth().oauth2(keycloakClient.getAccessToken(ADMIN))
                .header(APM_HEADER_PARAM, ADMIN)
                .contentType(APPLICATION_JSON)
                .body("{}")
                .post("/products/search")
                .then()
                .statusCode(200);
    }

    @Test
    void createProductTest() {
        // unauthorized
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body("{}")
                .post("/products")
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());

        // mock backend
        mockServerClient.when(
                HttpRequest.request()
                        .withPath("/internal/products")
                        .withMethod("POST"))
                .withId(MOCK_ID).respond(
                        HttpResponse.response()
                                .withStatusCode(201)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{}"));

        given()
                .when()
                .auth().oauth2(keycloakClient.getAccessToken(ADMIN))
                .header(APM_HEADER_PARAM, ADMIN)
                .contentType(APPLICATION_JSON)
                .body("{}")
                .post("/products")
                .then()
                .statusCode(201);
    }

    @Test
    void updateProductByIdTest() {
        // unauthorized
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body("{}")
                .put("/products/test-id")
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());

        // mock backend
        mockServerClient.when(
                HttpRequest.request()
                        .withPath("/internal/products/test-id")
                        .withMethod("PUT"))
                .withId(MOCK_ID).respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{}"));

        given()
                .when()
                .auth().oauth2(keycloakClient.getAccessToken(ADMIN))
                .header(APM_HEADER_PARAM, ADMIN)
                .contentType(APPLICATION_JSON)
                .body("{}")
                .put("/products/test-id")
                .then()
                .statusCode(200);
    }

    @Test
    void deleteProductByIdTest() {
        // unauthorized
        given()
                .when()
                .delete("/products/test-id")
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());

        // mock backend
        mockServerClient.when(
                HttpRequest.request()
                        .withPath("/internal/products/test-id")
                        .withMethod("DELETE"))
                .withId(MOCK_ID).respond(
                        HttpResponse.response()
                                .withStatusCode(204));

        given()
                .when()
                .auth().oauth2(keycloakClient.getAccessToken(ADMIN))
                .header(APM_HEADER_PARAM, ADMIN)
                .delete("/products/test-id")
                .then()
                .statusCode(204);
    }

}
