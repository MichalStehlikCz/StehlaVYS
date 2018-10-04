/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.datatypes.Dt;
import com.provys.common.datatypes.DtInteger;
import com.provys.common.datatypes.DtNumber;
import com.provys.common.datatypes.DtVarchar;
import com.provys.sqlbuilder.iface.CodeBuilder;

/**
 * Represents SQL literal.
 * 
 * @author stehlik
 */
public class SqlColumnValue extends SqlColumnAncestor {
    
    final Dt value;
    
    public SqlColumnValue(Dt value) {
        super(Dt.class);
        this.value = value;
    }

    public SqlColumnValue(Dt value, String alias) {
        super(alias, Dt.class);
        this.value = value;
    }

    public SqlColumnValue(String value) {
        super(DtVarchar.class);
        this.value = new DtVarchar(value);
    }

    public SqlColumnValue(String value, String alias) {
        super(alias, DtVarchar.class);
        this.value = new DtVarchar(value);
    }

    public SqlColumnValue(int value) {
        super(DtInteger.class);
        this.value = new DtInteger(value);
    }

    public SqlColumnValue(int value, String alias) {
        super(alias, DtInteger.class);
        this.value = new DtInteger(value);
    }

    public SqlColumnValue(float value) {
        super(DtNumber.class);
        this.value = new DtNumber(value);
    }

    public SqlColumnValue(float value, String alias) {
        super(alias, DtNumber.class);
        this.value = new DtNumber(value);
    }

    public SqlColumnValue(double value) {
        super(DtNumber.class);
        this.value = new DtNumber(value);
    }

    public SqlColumnValue(double value, String alias) {
        super(alias, DtNumber.class);
        this.value = new DtNumber(value);
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        code.append(this.value.toSqlLiteral());
        if (addAliasClause) {
            appendAlias(code);
        }
    }
}
