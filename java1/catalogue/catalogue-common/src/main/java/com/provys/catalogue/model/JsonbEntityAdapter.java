/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.model;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author stehlik
 */
public class JsonbEntityAdapter implements JsonbAdapter<Entity, JsonObject> {
    
    private boolean includeAttrId = false;
    private boolean includeAttr = false;

    public JsonbEntityAdapter() {
    }
    
    public JsonbEntityAdapter(boolean includeAttrId, boolean includeAttr) {
        this.includeAttrId = includeAttrId;
        this.includeAttr = includeAttr;
    }
    
    @Override
    public JsonObject adaptToJson(Entity original) {
        Jsonb jsonb = JsonbBuilder.create();
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("ENTITY_ID", jsonb.toJson(original.getId()))
                .add("NAME_NM", jsonb.toJson(original.getNameNm()));
        if (isIncludeAttrId() && (original.getAttrMap() != null)) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            original.getAttrMap().forEach((nameNm, confAttr) ->
                    arrayBuilder.add((jsonb.toJson(confAttr.getId()))));
            builder.add("ATTRIDS", arrayBuilder);
        }
        if (isIncludeAttr() && (original.getAttrMap() != null)) {
            builder.add("ATTRS", Json.createArrayBuilder(original.getAttrs()));
        }
        return builder.build();
    }

    @Override
    public Entity adaptFromJson(JsonObject adptd) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the includeAttrId
     */
    public boolean isIncludeAttrId() {
        return includeAttrId;
    }

    /**
     * @param includeAttrId the includeAttrId to set
     */
    public void setIncludeAttrId(boolean includeAttrId) {
        this.includeAttrId = includeAttrId;
    }

    /**
     * @return the includeAttr
     */
    public boolean isIncludeAttr() {
        return includeAttr;
    }

    /**
     * @param includeAttr the includeAttr to set
     */
    public void setIncludeAttr(boolean includeAttr) {
        this.includeAttr = includeAttr;
    }
}