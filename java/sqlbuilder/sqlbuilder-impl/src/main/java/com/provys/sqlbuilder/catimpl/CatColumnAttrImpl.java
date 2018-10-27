/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catimpl;

import com.provys.common.datatypes.DtNameNm;
import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.ColumnDef;
import com.provys.sqlbuilder.catbuilder.CatColumnAttr;
import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;

/**
 * Class represents column defined by attribute.
 * It is related to table in from clause.
 * 
 * @author stehlik
 */
class CatColumnAttrImpl implements CatColumnAttr {
    
    private final CatBuilderAttr attr;
    private final SqlColumn sqlColumn;

    /**
     * Check consistency between from element and attribute.
     */
    private static void checkFromElem(SqlFromElem fromElem, CatBuilderAttr attr) {
        if (!(fromElem instanceof CatFromElemEntity)) {
            throw new WrongFromElemTypeException(fromElem);
        }
        if (!((CatFromElemEntity) fromElem).getEntity().equals(
                attr.getEntity())) {
            throw new ColumnEntityMismatchException(
                    ((CatFromElemEntity) fromElem).getEntity()
                    , attr.getEntity());
        }
    }

    /**
     * Produce {@code CatColumnAttrImpl} for specified from element and attribute.
     * Uses lowercase of attribute internal name as column alias.
     * 
     * @param fromElem is from element new column will be associated with
     * @param attr is attribute new column is based on
     * @return CatColumnAttrImpl for given base from element and attribute
     */
    public static CatColumnAttrImpl forAttr(SqlFromElem fromElem
            , CatBuilderAttr attr) {
        return forAttr(fromElem, attr
                , attr.getNm().getValue().toLowerCase());
    }

    /**
     * Produce {@code CatColumnAttrImpl} for specified from element and attribute
     * with given alias.
     * 
     * @param fromElem is from element new column will be associated with
     * @param attr is attribute new column is based on
     * @param alias is alias to be assigned to new column
     * @return CatColumnAttrImpl for given base from element and attribute
     */
    public static CatColumnAttrImpl forAttr(SqlFromElem fromElem
            , CatBuilderAttr attr, String alias) {
        checkFromElem(fromElem, attr);
        return new CatColumnAttrImpl(fromElem, attr, alias);
    }

    /**
     * Produce {@code CatColumnAttrImpl} for specified from element and attribute
     * internal name.
     * Uses lowercase of attribute internal name as column alias.
     * 
     * @param fromElem is from element new column will be associated with
     * @param attrNm is internal name of attribute new column is based on
     * @return CatColumnAttrImpl for given base from element and attribute
     */
    public static CatColumnAttrImpl forAttrNm(SqlFromElem fromElem
            , String attrNm) {
        return forAttrNm(fromElem, attrNm, attrNm.toLowerCase());
    }

    /**
     * Produce {@code CatColumnAttrImpl} for specified from element and attribute
     * internal name with given alias.
     * 
     * @param fromElem is from element new column will be associated with
     * @param attrNm is internal name of attribute new column is based on
     * @param alias is alias to be assigned to new column
     * @return CatColumnAttrImpl for given base from element and attribute
     */
    public static CatColumnAttrImpl forAttrNm(SqlFromElem fromElem
            , String attrNm, String alias) {
        if (!(fromElem instanceof CatFromElemEntity)) {
            throw new WrongFromElemTypeException(fromElem);
        }
        return new CatColumnAttrImpl(fromElem, ((CatFromElemEntity) fromElem)
                .getEntity().getAttrByNm(new DtNameNm(attrNm)), alias);
    }

    /**
     * Base {@code CatColumnAttr} constructor.
     * Just sets all properties and build supporting simple column based on
     * properties of used attribute (datatype, indexed flag)
    */
    private CatColumnAttrImpl(SqlFromElem fromElem, CatBuilderAttr attr
            , String alias) {
        this.sqlColumn = new SqlColumnSimpleWithTable(attr.getSql(false), alias
                , attr.getTypeClass(), attr.isIndexed(), fromElem);
        this.attr = attr;
    }

    @Override
    public void buildSql(CodeBuilder code, boolean addAliasClause) {
        sqlColumn.buildSql(code, addAliasClause);
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        sqlColumn.buildSqlNoNewLine(code, addAliasClause);
    }

    @Override
    public ColumnDef getColumnDef() {
        return sqlColumn.getColumnDef();
    }

    @Override
    public String getAlias() {
        return sqlColumn.getAlias();
    }

    @Override
    public boolean isIndexed() {
        return sqlColumn.isIndexed();
    }

    @Override
    public CatBuilderAttr getAttr() {
        return attr;
    }

    /**
     * Thrown when trying to attach {@code CatColumnAttrImpl} to from element other
     * than {@code CatFromElemEntity}.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class WrongFromElemTypeException extends ProvysException {

        private static final long serialVersionUID = 1L;

        WrongFromElemTypeException(SqlFromElem fromElem) {
            super("Attribute column can only be attached to entity from"
                    + " element, not " + fromElem.getClass().getSimpleName());
        }
    }

    /**
     * Thrown when from element's and attribute's entities do not match.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ColumnEntityMismatchException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ColumnEntityMismatchException(CatBuilderEntity fromEntity
                , CatBuilderEntity attrEntity) {
            super("From element and attribute entities do not match (from element "
                    + fromEntity.getNameNm().getValue() + ", attr "
                    + attrEntity.getNameNm().getValue() + ")");
        }
    }
}
