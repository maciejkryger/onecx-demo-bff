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
import gen.org.tkit.onecx.demo.bff.rs.internal.ProductApiService;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.*;

@ApplicationScoped
@Transactional(Transactional.TxType.NOT_SUPPORTED)
@LogService

public class ProductRestController implements ProductApiService {

    @Inject
    @RestClient
    ProductsInternalApi client;

    @Inject
    ProductMapper mapper;

    @Inject
    ExceptionMapper exceptionMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response searchProductItems(SearchProductRequestDTO searchProductRequestDto) {
        try (Response backendResponse = client.searchProducts(mapper.map(searchProductRequestDto))) {
            ProductPageResult result = backendResponse.readEntity(ProductPageResult.class);
            return Response.status(backendResponse.getStatus()).entity(mapper.toSearchProductResponse(result)).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response createProduct(CreateProductRequestDTO createProductRequestDto) {
        try (Response backendResponse = client.createProduct(mapper.map(createProductRequestDto))) {
            Product result = backendResponse.readEntity(Product.class);
            return Response.status(backendResponse.getStatus()).entity(mapper.toCreateProductResponse(result)).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response updateProductById(@PathParam("id") String id, UpdateProductRequestDTO updateProductRequestDto) {
        try (Response backendResponse = client.updateProduct(id, mapper.map(updateProductRequestDto))) {
            Product result = backendResponse.readEntity(Product.class);
            return Response.status(backendResponse.getStatus()).entity(mapper.toUpdateProductResponse(result)).build();
        }
    }

    @DELETE
    @Override
    public Response deleteProductById(@PathParam("id") String id) {
        try (Response backendResponse = client.deleteProduct(id)) {
            return Response.status(backendResponse.getStatus()).build();
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
