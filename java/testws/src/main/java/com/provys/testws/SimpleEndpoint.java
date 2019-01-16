package com.provys.testws;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@ApplicationScoped
@Path("/simple")
public class SimpleEndpoint {

    @GET
    @Produces("text/plain")
    @Operation(
            summary = "Get text",
            description = "Return simple plain text",
            responses = {
                    @ApiResponse(
                            description = "Text",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(
                                            implementation = String.class
                                    )))})
    @Nonnull
    public Response getText() {
        return Response.ok("Text").build();
    }
}
