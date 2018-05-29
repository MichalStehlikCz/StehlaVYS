/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.api;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.provys.catalogue.model.ConfEntity;
import com.provys.catalogue.model.ConfAttr;
import com.provys.common.datatypes.*;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import com.provys.catalogue.iface.ConfEntityManager;

/**
 *
 * @author stehlik
 */
@Path("/Entity")
@Stateless()
public class ConfEntityService {

    @Inject
    private ConfEntityManager entityManager;

    @GET
    @Path("/{id : \\d+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ConfEntity get(@PathParam("id") String id) {
        return entityManager.get(new DtUid(id));
    }

    @GET
    @Path("/{nameNm : [a-zA-Z][a-zA-Z_0-9]*}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getByNm(@PathParam("nameNm") String nameNm) {
        Jsonb jsonb = JsonbBuilder.create();
        ConfEntity confEntity = entityManager.getByNm(new DtNameNm(nameNm));
        String result = jsonb.toJson(confEntity);
        return result;
    }

    @GET
    @Path("/{id : \\d+}/Attr/{attrNm : [a-zA-Z][a-zA-Z_0-9]*}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ConfAttr getAttr(@PathParam("id") String id
            , @PathParam("attrNm") String attrNm) {
        return entityManager.getAttrByNm(new DtUid(id), new DtNameNm(attrNm));
    }

    @GET
    @Path("/{nameNm : [a-zA-Z][a-zA-Z_0-9]*}/Attr/{attrNm : [a-zA-Z][a-zA-Z_0-9]*}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ConfAttr getAttrByNm(@PathParam("nameNm") String nameNm
            , @PathParam("attrNm") String attrNm) {
        return entityManager.getAttrByNm(new DtNameNm(nameNm)
                , new DtNameNm(attrNm));
    }
}
