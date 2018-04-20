/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlbuilder;

import java.util.List;

/**
 * SqlBuilder class provides means to easily build SQL statements.
 * Its specialised ancestors are used to build SELECT / INSERT / UPDATE / DELETE
 * statements. SqlBuilder allows to build queries both using native SQL
 * and to exploit PROVYS metadata catalogue. Latter is reason why 3rd party
 * library cannot be reasonably utilised - as no 3rd part library has link to
 * catalogue module.
 * 
 * @author stehlik
 */
abstract public class SqlBuilder {

    String key;
    String table;
    String alias;
    List<SqlBuilder> subqueries;
    List<String> wherecond;

}
