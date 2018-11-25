/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public class SqlCall extends SqlAncestor<BindValue> {

    public static SqlCall createFromStatement(SqlStatement statement
            , Map<String, Dt> values) {
        List<BindValue> variables = new ArrayList<>(
                statement.getVariables().size());
        statement.getVariables().forEach((variable) -> {
            if (values.containsKey(variable.getName())) {
                variables.add(new BindValue(variable.getName()
                        , values.get(variable.getName())));
            } else {
                // it can be ok if we actually had value in statement
                if (variable instanceof BindValue) {
                    variables.add((BindValue) variable);
                } else {
                    throw new BindValueMissingException(variable.getName());
                }
            }
        });
        return new SqlCall(statement.getSql(), variables
                , statement.getColumns());
    }

    public SqlCall(String sql, List<BindValue> variables, List<ColumnDef> columns) {
        super(sql, variables, columns);
    }

    public SqlCall(String sql, Optional<List<BindValue>> variables
            , Optional<List<ColumnDef>> columns) {
        super(sql, variables, columns);
    }

    /**
     * Exception raised when bind value for some variable is not supplied
     */
    @SuppressWarnings("PublicInnerClass")
    static public class BindValueMissingException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        BindValueMissingException(String name) {
            super("Bind value not supplied: " + name);
        }
    }
}
