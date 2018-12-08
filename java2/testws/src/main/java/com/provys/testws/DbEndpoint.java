package com.provys.testws;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/db")
public class DbEndpoint {

    @Inject
    DbService dbService;

    @GET
    @Path("/user")
    @Produces("text/plain")
    @Operation(
            summary = "User used to access DB",
            description = "Get Oracle user used to access Provys database",
            responses = {
                    @ApiResponse(
                            description = "User",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(
                                            implementation = String.class,
                                            maxLength = 30
                                    )))})
    public Response getUser() {
        return Response.ok(dbService.getUser()).build();
    }

    @GET
    @Path("/url")
    @Produces("text/plain")
    @Operation(
            summary = "Url used to access DB",
            description = "Get Url used to access Provys database",
            responses = {
                    @ApiResponse(
                            description = "Url",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(
                                            implementation = String.class
                                    )))})
    public Response getUrl() {
        return Response.ok(dbService.getUrl()).build();
    }

    @GET
    @Path("/dual")
    @Produces("text/plain")
    @Operation(
            summary = "Run SELECT 1 FROM dual",
            description = "Run basic query against PROVYS database; used to verify that connection to database works "
                    +"and it is possible to execute at least simple queries",
            responses = {
                    @ApiResponse(
                            description = "Value 1",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(
                                            implementation = Integer.class
                                    )))})
    public Response getText() {
        return Response.ok(dbService.runSelectFromDual()).build();
    }
}
