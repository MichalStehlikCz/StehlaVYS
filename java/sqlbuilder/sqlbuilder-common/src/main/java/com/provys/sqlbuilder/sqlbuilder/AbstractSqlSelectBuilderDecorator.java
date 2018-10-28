/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.call.SqlStatement;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import com.provys.sqlbuilder.sqlbuilder.SqlSelectBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCond;
import java.util.List;

/**
 * Abstract ancestor to make implementation of SqlSelectBuilderDecorator easier.
 * Provides redirects for all calls required by SqlSelectBuilder interface to
 * contained builder object.
 * 
 * @author stehlik
 * @param <B> is base (wrapped) {@code SqlSelectBuilder}
 * @param <D> is self reference to decorator, derived from this parametrized
 * type
 */
abstract public class AbstractSqlSelectBuilderDecorator<B extends SqlSelectBuilder
        , D extends AbstractSqlSelectBuilderDecorator<B, D>>
        implements SqlSelectBuilder {
    
    private final B sqlSelectBuilder;
    
    public AbstractSqlSelectBuilderDecorator(B sqlSelectBuilder) {
        this.sqlSelectBuilder = sqlSelectBuilder;
    }

    @Override
    public void buildSql(CodeBuilder code) {
        sqlSelectBuilder.buildSql(code);
    }

    @Override
    public List<SqlColumn> getColumns() {
        return sqlSelectBuilder.getColumns();
    }

    @Override
    public SqlStatement getSqlStatement() {
        return sqlSelectBuilder.getSqlStatement();
    }

    @Override
    public SqlCall getSqlCall() {
        return sqlSelectBuilder.getSqlCall();
    }

    @Override
    public D addColumn(SqlColumn sqlColumn) {
        sqlSelectBuilder.addColumn(sqlColumn);
        return getSelf();
    }

    @Override
    public SqlFromElem getFromElemByAlias(String alias) {
        return sqlSelectBuilder.getFromElemByAlias(alias);
    }

    @Override
    public SqlFromElem getFromElemByAliasIfExists(String alias) {
        return sqlSelectBuilder.getFromElemByAliasIfExists(alias);
    }

    @Override
    public D addFromElem(SqlFromElem fromElem) {
        sqlSelectBuilder.addFromElem(fromElem);
        return getSelf();
    }

    @Override
    public D addWhereCond(SqlWhereCond whereCond) {
        sqlSelectBuilder.addWhereCond(whereCond);
        return getSelf();
    }
    
    /**
     * Return reference to base {@code SqlSelectBuilder}.
     * Please, not that it is not immutable object and thus it is recommended
     * for descendants not to grant access to this object publicly
     * 
     * @return reference to base SqlSelectBuilder object
     */
    protected B getSqlSelectBuilder() {
        return sqlSelectBuilder;
    }
    
    /**
     * Function to get self reference with proper type.
     * Must be redefined in all non-abstract descendants as
     * {@code return this;}. Is used to circumvent fact that compiler does not
     * know that D parameter is self reference.
     * 
     * @return self reference.
     */
    abstract protected D getSelf();
}
