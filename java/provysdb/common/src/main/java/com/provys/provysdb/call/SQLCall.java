/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQLCall is used for passing of SQL (mostly SELECT) statement for execution.
 * It is similar to Statement, but methods do not require connection, making it
 * possible to implement class as serializable (even though it goes at the price
 * of not verifying that parameters are valid as they are added) and then
 * running prepared SQL call using statement
 * 
 * @author stehlik
 */
public class SQLCall implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * sql field contains SQL statement that is to be executed; most often,
     * it is SELECT statement, even though UPDATEs and DELETEs are supported
     * as well
     */
    private String sql;
    
    /**
     * values field contains list of bind values to be passed to statement
     */
    private List<BindValue> values;
    /**
     * columns field is list of column definitions that will be used to declare
     * expected columns of resulting set
     */
    private Map<Integer, ColumnDef> columns;

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
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<BindValue> values) {
        this.values = values;
    }

    /**
     * @param value is BindValue o be added to given statement
     */
    public void addValue(BindValue value) {
        if (values == null) {
            values=new ArrayList<>(10);
        }
        values.add(value);
    }

    /**
     * @return the columns
     */
    public Map<Integer, ColumnDef> getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(Map<Integer, ColumnDef> columns) {
        this.columns = columns;
    }
    
    /**
     * Adds given column definition to sql command
     * @param columnIndex is index of column to be defined (starting from 1)
     * @param def is definition, describing column data type
     */
    public void addColumn(int columnIndex, ColumnDef def) {
        if (columns == null) {
            columns=new HashMap<>();
        }
        columns.put(columnIndex, def);
    }
    
}
