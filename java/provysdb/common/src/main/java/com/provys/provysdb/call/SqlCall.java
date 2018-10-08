/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SqlCall is used for passing of SQL (mostly SELECT) statement for execution.
 * It is similar to Statement, but methods do not require connection, making it
 * possible to implement class as serializable (even though it goes at the price
 * of not verifying that parameters are valid as they are added) and then
 * running prepared SQL call using statement
 * 
 * @author stehlik
 */
public class SqlCall implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * sql field contains SQL statement that is to be executed; most often,
     * it is SELECT statement, even though UPDATEs and DELETEs are supported
     * as well
     */
    private String sql;
    
    /**
     * Values field contains list of bind values to be passed to statement
     */
    private final Map<String, BindValue> values = new ConcurrentHashMap<>(1);
    /**
     * columns field is list of column definitions that will be used to declare
     * expected columns of resulting set
     */
    private final Map<Integer, ColumnDef> columns = new ConcurrentHashMap<>(1);

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * @param sql the sql to set
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * @return the values
     */
    public List<BindValue> getValues() {
        return new ArrayList<>(values.values());
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<BindValue> values) {
        this.values.clear();
        values.forEach((value) -> {this.values.put(value.getName(), value);});
    }

    /**
     * @param value is BindValue to be added to given statement
     */
    public void addValue(BindValue value) {
        values.put(value.getName(), value);
    }

    /**
     * Set value of specified bind.
     * Note that this does not replace addBind - bind already have to exist for
     * setValue to be successful.
     * 
     * @param name is name of the bind to be set
     * @param value is desired value
     */
    public void setValue(String name, Dt value) {
        BindValue bindValue = values.get(name);
        if (bindValue == null) {
            throw new BindValueDoesNotExistException(name);
        }
        bindValue.setValue(value);
    }
    
    /**
     * Set value of specified bind.
     * 
     * @param name is name of the bind to be set
     * @param value is desired value
     */
    public void setValueIfExists(String name, Dt value) {
        BindValue bindValue = values.get(name);
        if (bindValue != null) {
            bindValue.setValue(value);
        }
    }
    
    /**
     * @return the columns
     */
    public Map<Integer, ColumnDef> getColumns() {
        return Collections.unmodifiableMap(columns);
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(Map<Integer, ColumnDef> columns) {
        this.columns.clear();
        this.columns.putAll(columns);
    }
    
    /**
     * @param columns the columns to set
     */
    public void setColumns(List<ColumnDef> columns) {
        this.columns.clear();
        columns.forEach((column) -> {
            this.columns.put(this.columns.size(), column);
        });
    }
    
    /**
     * Adds given column definition to sql command
     * @param columnIndex is index of column to be defined (starting from 0)
     * @param def is definition, describing column data type
     */
    public void addColumn(int columnIndex, ColumnDef def) {
        columns.put(columnIndex, def);
    }
    
    /**
     * Exception raised when trying to set value of bind variable that does not
     * exist
     */
    @SuppressWarnings("PublicInnerClass")
    static public class BindValueDoesNotExistException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        BindValueDoesNotExistException(String name) {
            super("Bind value does not exist: " + name);
        }
    }
}
