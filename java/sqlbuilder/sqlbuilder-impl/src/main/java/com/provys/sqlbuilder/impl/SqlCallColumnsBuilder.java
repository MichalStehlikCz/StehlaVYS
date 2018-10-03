/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.provysdb.call.ColumnDef;
import com.provys.sqlbuilder.iface.SqlColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Builds map of columns requested by SqlCall from list of columns.
 * 
 * @author stehlik
 */
class SqlCallColumnsBuilder implements Consumer<SqlColumn> {
    
    private boolean undefined = false;
    private final List<ColumnDef> columns;

    SqlCallColumnsBuilder(int size) {
        columns = new ArrayList<>(size);
    }

    @Override
    public void accept(SqlColumn column) {
        if ()
        getColumns().add(column.getColumnDef());
    }

    /**
     * @return the columns
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public List<ColumnDef> getColumns() {
        return columns;
    }
    
}
