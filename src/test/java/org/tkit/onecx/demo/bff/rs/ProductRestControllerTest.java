package org.tkit.onecx.demo.bff.rs;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.tkit.quarkus.log.cdi.LogService;

import io.quarkiverse.mockserver.test.InjectMockServerClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@LogService
class ProductRestControllerTest extends AbstractTest {
    @InjectMockServerClient
    MockServerClient mockServerClient;

    @BeforeEach
    void resetExpectation() {
        try {
            mockServerClient.clear(MOCK_ID);
        } catch (Exception ex) {
            // mockId not existing
        }
    }

    @Test
    void createProductTest() {
        // unauthorized
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body("{}")
                .post("/internal/products")
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
                .post("/internal/products")
                .then()
                .statusCode(201);
    }

    @Test
    void getProductByIdTest() {
        // unauthorized
        given()
                .when()
                .get("/internal/products/test-id")
                .then()
                .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());

        // mock backend
        mockServerClient.when(
                HttpRequest.request()
                        .withPath("/internal/products/test-id")
                        .withMethod("GET"))
                .withId(MOCK_ID).respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{}"));

        given()
                .when()
                .auth().oauth2(keycloakClient.getAccessToken(ADMIN))
                .header(APM_HEADER_PARAM, ADMIN)
                .get("/internal/products/test-id")
                .then()
                .statusCode(200);
    }

    @Test
    void updateProductTest() {
        // unauthorized
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body("{}")
                .put("/internal/products/test-id")
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
                .put("/internal/products/test-id")
                .then()
                .statusCode(200);
    }

    @Test
    void deleteProductTest() {
        // unauthorized
        given()
                .when()
                .delete("/internal/products/test-id")
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
                .delete("/internal/products/test-id")
                .then()
                .statusCode(204);
    }

    @Test
    void searchProductsTest() {
        // unauthorized
        given()
                .when()
                .contentType(APPLICATION_JSON)
                .body("{}")
                .post("/internal/products/search")
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
                .post("/internal/products/search")
                .then()
                .statusCode(200);
    }

}
