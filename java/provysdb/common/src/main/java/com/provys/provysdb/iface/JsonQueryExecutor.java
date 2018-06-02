/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.iface;

import java.util.List;
import javax.json.JsonObject;

/**
 *
 * @author stehlik
 */
public interface JsonQueryExecutor extends QueryExecutor {

    List<JsonObject> executeQuery();
    List<JsonObject> getData();
}
