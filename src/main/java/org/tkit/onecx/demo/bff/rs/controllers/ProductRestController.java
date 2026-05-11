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
    public Response createproduct() {
        // TODO implement backend call and mapper conversion.
        return null;
    }

    @GET
    @Path("/internal/products/{id}")
    public Response getproductbyid(@PathParam("id") String id) {
        // TODO implement backend call and mapper conversion.
        return null;
    }

    @PUT
    @Path("/internal/products/{id}")
    public Response updateproduct(@PathParam("id") String id) {
        // TODO implement backend call and mapper conversion.
        return null;
    }

    @DELETE
    @Path("/internal/products/{id}")
    public Response deleteproduct(@PathParam("id") String id) {
        // TODO implement backend call and mapper conversion.
        return null;
    }

    @POST
    @Path("/internal/products/search")
    public Response searchproducts() {
        // TODO implement backend call and mapper conversion.
        return null;
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
