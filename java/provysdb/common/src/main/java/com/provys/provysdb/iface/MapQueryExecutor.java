/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.iface;

import com.provys.provysdb.call.SQLCall;
import java.util.List;
import java.util.Map;

/**
 *
 * @author stehlik
 */
public interface MapQueryExecutor {

    List<Map<String, Object>> executeQuery(SQLCall sqlCall);

}
