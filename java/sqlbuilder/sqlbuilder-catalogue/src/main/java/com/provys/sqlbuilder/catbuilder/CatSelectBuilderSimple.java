/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catbuilder;

import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.BindVariable;
import com.provys.sqlbuilder.catbuilder.CatColumnAttr;
import com.provys.sqlbuilder.catbuilder.CatSelectBuilder;
import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import com.provys.sqlbuilder.sqlbuilder.SqlOperator2;
import com.provys.sqlbuilder.sqlbuilder.SqlSelectBuilder;
import com.provys.sqlbuilder.sqlbuilder.AbstractSqlSelectBuilderDecorator;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCond;

/**
 * Extension of SqlSelectBuilder, working with catalogue objects.
 * 
 * @author stehlik
 */
class CatSelectBuilderSimple extends AbstractSqlSelectBuilderDecorator<
        SqlSelectBuilder, CatSelectBuilderSimple> implements CatSelectBuilder {
    
    public static CatSelectBuilderSimple ofEntity(CatBuilderEntity entity) {
        CatSelectBuilderSimple selectBuilder = new CatSelectBuilderSimple(
                SqlSelectBuilder.create());
        selectBuilder.addFromElem(CatFromElemEntity.of(entity));
    }

    public CatSelectBuilderSimple(SqlSelectBuilder sqlSelectBuilder) {
        super(sqlSelectBuilder);
    }

    @Override
    public CatColumnAttr getAttrColumn(CatBuilderAttr attr) {
        return CatColumnAttrImpl.forAttr(getFromElemByEntity(
                attr.getEntity()), attr);
    }

    @Override
    public CatSelectBuilder addAttrColumn(CatBuilderAttr attr, String alias) {
        getSqlSelectBuilder().addColumn(CatColumnAttrImpl.forAttr(
                getFromElemByEntity(attr.getEntity()), attr, alias));
        return this;
    }

    @Override
    public CatSelectBuilder addAttrColumn(String tableAlias
            , CatBuilderAttr attr) {
        getSqlSelectBuilder().addColumn(CatColumnAttrImpl.forAttr(
                getSqlSelectBuilder().getFromElemByAlias(tableAlias), attr));
        return this;
    }

    @Override
    public CatSelectBuilder addAttrColumn(String tableAlias
            , CatBuilderAttr attr, String alias) {
        getSqlSelectBuilder().addColumn(CatColumnAttrImpl.forAttr(
                getSqlSelectBuilder().getFromElemByAlias(tableAlias), attr
                    , alias));
        return this;
    }

    @Override
    public CatSelectBuilder addAttrColumn(String tableAlias
            , String attrNm) {
        getSqlSelectBuilder().addColumn(CatColumnAttrImpl.forAttrNm(
                getSqlSelectBuilder().getFromElemByAlias(tableAlias), attrNm));
        return this;
    }

    @Override
    public CatSelectBuilder addAttrColumn(String tableAlias
            , String attrNm, String alias) {
        getSqlSelectBuilder().addColumn(CatColumnAttrImpl.forAttrNm(
                getSqlSelectBuilder().getFromElemByAlias(tableAlias), attrNm
                , alias));
        return this;
    }

    @Override
    public CatSelectBuilder addAttrColumn(CatBuilderAttr attr) {
        getSqlSelectBuilder().addColumn(CatColumnAttrImpl.forAttr(
                getFromElemByEntity(
                attr.getEntity()), attr));
        return this;
    }

    @Override
    public SqlFromElem getFromElemByEntity(CatBuilderEntity entity) {
        for (SqlFromElem fromElem : getSqlSelectBuilder().getFromElems()) {
            if (fromElem instanceof CatFromElemEntity) {
                if (((CatFromElemEntity) fromElem).getEntity().equals(entity)) {
                    return fromElem;
                }
            }
        }
        throw new ElemNotFoundByEntityException(entity);
    }

    @Override
    public CatSelectBuilder addAttrWhereCond(CatBuilderAttr attr
            , SqlOperator2 operator, BindVariable value) {
        getSqlSelectBuilder().addWhereCond(SqlWhereCond.ofTwoOperands(
                CatColumnAttrImpl.forAttr(getFromElemByEntity(attr.getEntity())
                        , attr), operator, SqlColumn.ofBind(value)));
        return this;
    }

    @Override
    protected CatSelectBuilderSimple getSelf() {
        return this;
    }

    @Override
    public CatColumnAttr getAttrColumn(CatBuilderAttr attr, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CatColumnAttr getAttrColumn(String tableAlias, CatBuilderAttr attr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CatColumnAttr getAttrColumn(String tableAlias, CatBuilderAttr attr, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CatColumnAttr getAttrColumn(String tableAlias, String attrNm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CatColumnAttr getAttrColumn(String tableAlias, String attrNm, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
