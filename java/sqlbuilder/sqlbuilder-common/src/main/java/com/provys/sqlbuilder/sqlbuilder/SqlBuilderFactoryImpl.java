/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.BindVariable;
import com.provys.sqlbuilder.sqlbuilder.SqlBuilderFactory;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import com.provys.sqlbuilder.sqlbuilder.SqlOperator2;
import com.provys.sqlbuilder.sqlbuilder.SqlQueryBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlSelectBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCond;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCondAnd;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCondOr;
import java.util.List;

/**
 * Implementation of factory, producing SqlBuilder and related objects.
 * 
 * @author stehlik
 */
class SqlBuilderFactoryImpl {
    
    /**
     * Constructor is here just to prevent instantiation.
     * Class serves single purpose - to provide factories for various objects
     */
    private SqlBuilderFactoryImpl() {
        throw new ProvysException("SqlBuilderFactory is not instantiable");
    }

    @Override
    public SqlSelectBuilder getSimpleSelect(SqlColumn column, String table, String alias) {
        return new SqlSelectBuilderSimple(column, getFromElem(table, alias));
    }

    @Override
    public SqlQueryBuilder getBindQuery(BindVariable bindVariable) {
        return new QueryBuilderSimple(getBindColumn(bindVariable));
    }

    @Override
    public SqlColumn getValue(Dt value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(Dt value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(String value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(String value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(int value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(int value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(float value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(float value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(double value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(double value, String alias) {
        return SqlColumnValue.of(value, alias);
    }
    
    @Override
    public SqlColumn getBindColumn(BindVariable bindVariable) {
        return SqlColumnBind.of(bindVariable);
    }
    
    @Override
    public SqlColumn getBindColumn(BindVariable bindVariable, String alias) {
        return SqlColumnBind.of(bindVariable, alias);
    }

    @Override
    public SqlColumn getColumnWithTextAndTable(SqlFromElem fromElem
            , String sqlText, Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElem, sqlText, type);
    }

    @Override
    public SqlColumn getColumnWithTextAndTables(List<SqlFromElem> fromElems
            , String sqlText, Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElems, sqlText, type);
    }

    @Override
    public SqlColumn getColumnWithTextAndTable(SqlFromElem fromElem
            , String sqlText, String alias, Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElem, sqlText, alias, type);
    }

    @Override
    public SqlColumn getColumnWithTextAndTables(List<SqlFromElem> fromElems
            , String sqlText, String alias, Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElems, sqlText, alias, type);
    }

    @Override
    public SqlWhereCondAnd getWhereCondAnd() {
        return new WhereCondAndImpl();
    }

    @Override
    public SqlWhereCondOr getWhereCondOr() {
        return new WhereCondOrImpl();
    }

    @Override
    public SqlWhereCond getWhereCondTwoOp(SqlColumn column1
            , SqlOperator2 operator, SqlColumn column2) {
        return new WhereCondTwoOp(column1, operator, column2);
    }

    @Override
    public SqlWhereCond getWhereCondTwoOp(SqlColumn column1
            , SqlOperator2 operator, SqlColumn column2, boolean negative) {
        return new WhereCondTwoOp(column1, operator, column2, negative);
    }
}
