/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.testrest;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author stehlik
 */
@Path("/TestService")
@Stateless()
public class TestService {

    @GET
    @Path("")
    @Produces({MediaType.TEXT_PLAIN})
    public String getHello() {
        return "Hello";
    }

    @GET
    @Path("/{nameNm : [a-zA-Z][a-zA-Z_0-9]*}")
    @Produces({MediaType.TEXT_PLAIN})
    public String getHelloWithText(@PathParam("nameNm") String nameNm) {
        return "Hello: "+nameNm;
    }
}
