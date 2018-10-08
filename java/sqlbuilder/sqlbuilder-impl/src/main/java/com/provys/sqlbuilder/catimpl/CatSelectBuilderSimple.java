/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catimpl;

import com.provys.provysdb.call.SqlCall;
import com.provys.sqlbuilder.catbuilder.CatSelectBuilder;
import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public SqlSelectBuilder addFromElem(SqlFromElem fromElem) {
        sqlSelectBuilder.addFromElem(fromElem);
        return this;
    }

    @Override
    public SqlSelectBuilder addWhereCond(SqlWhereCond whereCond) {
        sqlSelectBuilder.addWhereCond(whereCond);
        return this;
    }

}
