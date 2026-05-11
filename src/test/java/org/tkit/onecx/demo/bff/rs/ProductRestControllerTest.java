package org.tkit.onecx.demo.bff.rs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import org.tkit.onecx.demo.bff.rs.controllers.ProductRestController;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ProductRestControllerTest extends AbstractTest {
    @Inject
    ProductRestController controller;

    @Test
    void shouldInjectController() {
        assertNotNull(controller);
    }

    @Test
    void shouldHandleCreateproduct() {
        // TODO add endpoint behavior assertions when frontend contract is finalized.
        assertNotNull(controller);
    }

    @Test
    void shouldHandleGetproductbyid() {
        // TODO add endpoint behavior assertions when frontend contract is finalized.
        assertNotNull(controller);
    }

    @Test
    void shouldHandleUpdateproduct() {
        // TODO add endpoint behavior assertions when frontend contract is finalized.
        assertNotNull(controller);
    }

    @Test
    void shouldHandleDeleteproduct() {
        // TODO add endpoint behavior assertions when frontend contract is finalized.
        assertNotNull(controller);
    }

    @Test
    void shouldHandleSearchproducts() {
        // TODO add endpoint behavior assertions when frontend contract is finalized.
        assertNotNull(controller);
    }

}
