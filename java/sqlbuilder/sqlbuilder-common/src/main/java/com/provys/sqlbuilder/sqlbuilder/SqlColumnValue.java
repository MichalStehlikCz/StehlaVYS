/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.datatypes.Dt;
import com.provys.common.datatypes.DtInteger;
import com.provys.common.datatypes.DtNumber;
import com.provys.common.datatypes.DtVarchar;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;

/**
 * Represents SQL literal.
 * 
 * @author stehlik
 */
class SqlColumnValue extends AbstractSqlColumn {
    
    private final Dt value;
    
    public static SqlColumnValue of(Dt value) {
        return new SqlColumnValue(value);
    }

    public static SqlColumnValue of(Dt value, String alias) {
        return new SqlColumnValue(value, alias);
    }

    public static SqlColumnValue of(String value) {
        return new SqlColumnValue(DtVarchar.of(value));
    }

    public static SqlColumnValue of(String value, String alias) {
        return new SqlColumnValue(DtVarchar.of(value), alias);
    }

    public static SqlColumnValue of(int value) {
        return new SqlColumnValue(DtInteger.of(value));
    }

    public static SqlColumnValue of(int value, String alias) {
        return new SqlColumnValue(DtInteger.of(value), alias);
    }

    public static SqlColumnValue of(float value) {
        return new SqlColumnValue(DtNumber.of(value));
    }

    public static SqlColumnValue of(float value, String alias) {
        return new SqlColumnValue(DtNumber.of(value), alias);
    }

    public static SqlColumnValue of(double value) {
        return new SqlColumnValue(DtNumber.of(value));
    }

    public static SqlColumnValue of(double value, String alias) {
        return new SqlColumnValue(DtNumber.of(value), alias);
    }

    private SqlColumnValue(Dt value) {
        super(Dt.class);
        this.value = value;
    }

    private SqlColumnValue(Dt value, String alias) {
        super(alias, Dt.class);
        this.value = value;
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        code.append(this.value.toSqlLiteral());
        if (addAliasClause) {
            appendAlias(code);
        }
    }
}
