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
        this.value = value;
    }

    public SqlColumnValue(Dt value, String alias) {
        super(alias);
        this.value = value;
    }

    public SqlColumnValue(String value) {
        this.value = new DtVarchar(value);
    }

    public SqlColumnValue(String value, String alias) {
        super(alias);
        this.value = new DtVarchar(value);
    }

    public SqlColumnValue(int value) {
        this.value = new DtInteger(value);
    }

    public SqlColumnValue(int value, String alias) {
        super(alias);
        this.value = new DtInteger(value);
    }

    public SqlColumnValue(float value) {
        this.value = new DtNumber(value);
    }

    public SqlColumnValue(float value, String alias) {
        super(alias);
        this.value = new DtNumber(value);
    }

    public SqlColumnValue(double value) {
        this.value = new DtNumber(value);
    }

    public SqlColumnValue(double value, String alias) {
        super(alias);
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
