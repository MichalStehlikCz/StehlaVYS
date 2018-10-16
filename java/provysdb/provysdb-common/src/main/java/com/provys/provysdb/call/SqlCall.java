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
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;

/**
 * SqlCall is used for passing of SQL (mostly SELECT) statement for execution.
 * It is similar to Statement, but methods do not require connection, making it
 * possible to implement class as serializable (even though it goes at the price
 * of not verifying that parameters are valid as they are added) and then
 * running prepared SQL call using statement
 * 
 * @author stehlik
 */
@JsonbTypeSerializer(JsonbSqlCallSerializer.class)
@JsonbTypeDeserializer(JsonbSqlCallDeserializer.class)
public class SqlCall {

    /**
     * sql field contains SQL statement that is to be executed; most often,
     * it is SELECT statement, even though UPDATEs and DELETEs are supported
     * as well
     */
    private String sql;
    private final List<BindValue> values;
    private final Map<String, BindValue> valuesByName;

    /**
     * columns field is list of column definitions that will be used to declare
     * expected columns of resulting set
     */
    private final List<ColumnDef> columns;

    public SqlCall(String sql, List<BindValue> values, List<ColumnDef> columns) {
        this.sql = sql;
        this.values = new ArrayList<>(values);
        this.valuesByName = new ConcurrentHashMap<>(this.values.size());
        this.values.forEach((value) -> {
            if (valuesByName.containsKey(value.getName())) {
                throw new BindValueAlreadyExistsException(value.getName());
            }
            valuesByName.put(value.getName(), value);
        });
        this.columns = new ArrayList<>(columns);
    }

    public SqlCall(String sql, Optional<List<BindValue>> values
            , Optional<List<ColumnDef>> columns) {
        this.sql = sql;
        if (values.isPresent()) {
            this.values = new ArrayList<> (values.get());
            this.valuesByName = new ConcurrentHashMap<>(this.values.size());
            this.values.forEach((value) -> {
                if (valuesByName.containsKey(value.getName())) {
                    throw new BindValueAlreadyExistsException(value.getName());
                }
                valuesByName.put(value.getName(), value);
            });
        } else {
            this.values = new ArrayList<> (0);
            this.valuesByName = new HashMap<>(0);
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
     * @param sql the sql to set
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * @return the values
     */
    public List<BindValue> getValues() {
        return Collections.unmodifiableList(values);
    }

    /**
     * @param name name of parameter to be found
     * @return bind value with specified name
     */
    public BindValue getValue(String name) {
        final BindValue value = valuesByName.get(name);
        if (value == null) {
            throw new BindValueDoesNotExistException(name);
        }
        return value;
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
    static public class BindValueAlreadyExistsException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        BindValueAlreadyExistsException(String name) {
            super("Bind value already exists: " + name);
        }
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
