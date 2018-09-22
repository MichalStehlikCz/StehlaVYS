/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.provysdb.call.BindValue;
import java.util.List;

/**
 * SqlBuilder class provides means to easily build SQL SELECT statements.
 * SqlBuilder allows to build queries both using native SQL and to exploit
 * PROVYS metadata catalogue. Latter is reason why 3rd party library cannot be
 * reasonably utilised - as no 3rd part library has link to catalogue module.
 *  
 * Whole concept works as follows
 * - SQLBuilder represents Entity + key, where key might be reference to
 * column in table or bind variable
 * - it is possible to attach condition to SQLBuilder
 * - it is possible to use SQLBuilder + relation as condition in SQLBuilder
 * - it is possible to attach additional column to SQLBuilder
 * - it is possible to retrieve select statement, list of binds and columns from
 * SQLBuilder
 * 
 * @author stehlik
 */
interface SqlBuilder {

    public String getSql();
    public List<BindValue> getBinds();
    
//    public void Retype(ConfRel relation);
    
}
