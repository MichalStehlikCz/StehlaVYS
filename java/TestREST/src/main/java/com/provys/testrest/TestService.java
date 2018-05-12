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
@Path("")
@Stateless()
public class TestService {

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    public TestClass getHello() {
        TestClass data = new TestClass();
        data.add(1);
        data.add(2);
        return data;
    }

    @POST
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    public TestClass useHello(TestClass data) {
        return data;
    }
}
