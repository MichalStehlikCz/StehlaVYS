/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutor;

import com.provys.provysdb.call.SQLCall;
import java.util.List;
import javax.json.JsonObject;

/**
 *
 * @author micha
 */
public interface JsonQueryExecutorLocal {

    List<JsonObject> executeQuery(SQLCall sqlCall);
    
}
