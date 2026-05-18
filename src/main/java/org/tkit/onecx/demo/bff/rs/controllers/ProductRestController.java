package org.tkit.onecx.demo.bff.rs.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.tkit.onecx.demo.bff.rs.mappers.ExceptionMapper;
import org.tkit.onecx.demo.bff.rs.mappers.ProductMapper;
import org.tkit.quarkus.log.cdi.LogService;

import gen.org.tkit.onecx.demo.bff.backend.client.api.ProductsInternalApi;
import gen.org.tkit.onecx.demo.bff.backend.client.model.*;

@ApplicationScoped
@Transactional(Transactional.TxType.NOT_SUPPORTED)
@LogService
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductRestController {

    @Inject
    @RestClient
    ProductsInternalApi client;

    @Inject
    ProductMapper mapper;

    @Inject
    ExceptionMapper exceptionMapper;

    @POST
    @Path("/internal/products")
    public Response createProduct(Product productDto) {
        try (Response backendResponse = client.createProduct(productDto)) {
            Product result = backendResponse.readEntity(Product.class);
            return Response.status(201).entity(result).build();
        }
    }

    @GET
    @Path("/internal/products/{id}")
    public Response getProductById(@PathParam("id") String id) {
        try (Response backendResponse = client.getProductById(id)) {
            Product result = backendResponse.readEntity(Product.class);
            return Response.status(200).entity(result).build();
        }
    }

    @PUT
    @Path("/internal/products/{id}")
    public Response updateProduct(@PathParam("id") String id, Product productDto) {
        try (Response backendResponse = client.updateProduct(id, productDto)) {
            Product result = backendResponse.readEntity(Product.class);
            return Response.status(200).entity(result).build();
        }
    }

    @DELETE
    @Path("/internal/products/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        client.deleteProduct(id);
        return Response.noContent().build();
    }

    @POST
    @Path("/internal/products/search")
    public Response searchProducts(ProductSearchCriteria productSearchCriteriaDto) {
        try (Response backendResponse = client.searchProducts(productSearchCriteriaDto)) {
            ProductPageResult result = backendResponse.readEntity(ProductPageResult.class);
            return Response.status(200).entity(result).build();
        }
    }

    @ServerExceptionMapper
    public RestResponse<String> constraint(ConstraintViolationException ex) {
        return exceptionMapper.constraint(ex);
    }

    @ServerExceptionMapper
    public Response restException(ClientWebApplicationException ex) {
        return exceptionMapper.clientException(ex);
    }
}
