/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.error.ProvysException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SqlAncestor is common ancestor to SqlStatement, SqlCall and ProcCall.
 * Classes are used for passing of SQL statements for execution. They are
 * similar to Statement and other DB access classes, but methods do not require
 * connection, making it possible to implement class as serializable (even
 * though it goes at the price of not verifying that parameters are valid)
 * and then running prepared SQL call using statement
 * 
 * @author stehlik
 * @param <T> is type of binds used in class
 */
public class SqlAncestor<T extends BindVariable> {

    /**
     * sql field contains SQL statement that is to be executed; most often,
     * it is SELECT statement, even though UPDATEs and DELETEs are supported
     * as well
     */
    private final String sql;
    private final List<T> variables;
    private final Map<String, T> variablesByName;

    /**
     * columns field is list of column definitions that will be used to declare
     * expected columns of resulting set
     */
    private final List<ColumnDef> columns;

    public SqlAncestor(String sql, List<T> variables, List<ColumnDef> columns) {
        this.sql = sql;
        this.variables = new ArrayList<>(variables);
        this.variablesByName = new ConcurrentHashMap<>(this.variables.size());
        this.variables.forEach((variable) -> {
            if (variablesByName.containsKey(variable.getName())) {
                throw new BindVariableAlreadyExistsException(variable.getName());
            }
            variablesByName.put(variable.getName(), variable);
        });
        this.columns = new ArrayList<>(columns);
    }

    public SqlAncestor(String sql, Optional<List<T>> variables
            , Optional<List<ColumnDef>> columns) {
        this.sql = sql;
        if (variables.isPresent()) {
            this.variables = new ArrayList<> (variables.get());
            this.variablesByName
                    = new ConcurrentHashMap<>(this.variables.size());
            this.variables.forEach((variable) -> {
                if (variablesByName.containsKey(variable.getName())) {
                    throw new BindVariableAlreadyExistsException(variable.getName());
                }
                variablesByName.put(variable.getName(), variable);
            });
        } else {
            this.variables = new ArrayList<> (0);
            this.variablesByName = new HashMap<>(0);
        }
        if (columns.isPresent()) {
            this.columns = new ArrayList<>(columns.get());
        } else {
            this.columns = new ArrayList<>(0);
        }
    }

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * @return the variables
     */
    public List<T> getVariables() {
        return Collections.unmodifiableList(variables);
    }

    /**
     * @param name name of parameter to be found
     * @return bind value with specified name
     */
    public T getVariable(String name) {
        final T variable = variablesByName.get(name);
        if (variable == null) {
            throw new BindVariableDoesNotExistException(name);
        }
        return variable;
    }

    /**
     * @return the columns
     */
    public List<ColumnDef> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    /**
     * @param index order of column to be returned
     * @return column definition on specified position
     */
    public ColumnDef getColumn(int index) {
        return columns.get(index);
    }

    /**
     * Exception raised when trying to add bind and bind with same name already
     * exists
     */
    @SuppressWarnings("PublicInnerClass")
    static public class BindVariableAlreadyExistsException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        BindVariableAlreadyExistsException(String name) {
            super("Bind variable already exists: " + name);
        }
    }

    /**
     * Exception raised when trying to set value of bind variable that does not
     * exist
     */
    @SuppressWarnings("PublicInnerClass")
    static public class BindVariableDoesNotExistException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        BindVariableDoesNotExistException(String name) {
            super("Bind variable does not exist: " + name);
        }
    }
}
