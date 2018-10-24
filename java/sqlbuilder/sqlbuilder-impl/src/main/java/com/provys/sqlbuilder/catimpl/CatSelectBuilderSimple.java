/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catimpl;

import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.call.SqlStatement;
import com.provys.sqlbuilder.catbuilder.CatSelectBuilder;
import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlSelectBuilder;
import com.provys.sqlbuilder.iface.SqlWhereCond;
import com.provys.sqlbuilder.impl.SqlSelectBuilderSimple;
import java.util.List;

/**
 * Extension of SqlSelectBuilder, working with catalogue objects.
 * 
 * @author stehlik
 */
public class CatSelectBuilderSimple implements CatSelectBuilder {
    
    final private SqlSelectBuilderSimple sqlSelectBuilder;
    
    public CatSelectBuilderSimple(SqlSelectBuilderSimple sqlSelectBuilder) {
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
    public CatSelectBuilder addColumn(SqlColumn sqlColumn) {
        sqlSelectBuilder.addColumn(sqlColumn);
        return this;
    }

    @Override
    public CatSelectBuilder addColumn(CatBuilderAttr attr) {
        //sqlSelectBuilder.addColumn(new CatColumnAttr(attr));
        return this;
    }

    @Override
    public CatSelectBuilder addColumn(CatBuilderAttr attr, String alias) {
        sqlSelectBuilder.addColumn(new CatColumnAttr(attr, alias
                , getFromElemByEntity(attr.getEntity())));
        return this;
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
    public SqlFromElem getFromElemByEntity(CatBuilderEntity entity) {
        for (SqlFromElem fromElem : sqlSelectBuilder.getFromElems()) {
            if (fromElem instanceof CatFromElemEntity) {
                if (((CatFromElemEntity) fromElem).getEntity().equals(entity)) {
                    return fromElem;
                }
            }
        }
        throw new ElemNotFoundByEntityException(entity);
    }

    @Override
    public SqlSelectBuilder addFromElem(SqlFromElem fromElem) {
        sqlSelectBuilder.addFromElem(fromElem);
        return this;
    }

    @Override
    public SqlSelectBuilder addWhereCond(SqlWhereCond whereCond) {
        sqlSelectBuilder.addWhereCond(whereCond);
        return this;
    }

    /**
     * FROM element was looked up but not found using alias.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ElemNotFoundByEntityException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ElemNotFoundByEntityException(CatBuilderEntity entity) {
            super("FROM element not found using entity:"
                    + entity.getNameNm().toString());
        }
    }

}
