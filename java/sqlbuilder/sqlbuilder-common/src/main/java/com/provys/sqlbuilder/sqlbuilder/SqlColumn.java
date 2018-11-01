/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.datatypes.Dt;
import com.provys.provysdb.call.BindVariable;
import com.provys.provysdb.call.ColumnDef;
import java.util.Collections;
import java.util.List;

/**
 * Represents column in select query.
 * 
 * @author stehlik
 */
public interface SqlColumn {
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public static SqlColumn ofValue(Dt value) {
        return SqlColumnValue.of(value);
    }
    
    /**
     * Get column, representing supplied (constant) value with alias.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public static SqlColumn ofValue(Dt value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    
    /**
     * Get column, representing supplied String value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public static SqlColumn ofValue(String value) {
        return SqlColumnValue.of(value);
    }

    /**
     * Get column, representing supplied String value with alias.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public static SqlColumn ofValue(String value, String alias) {
        return SqlColumnValue.of(value, alias);
    }
    
    /**
     * Get column, representing supplied integer value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public static SqlColumn ofValue(int value) {
        return SqlColumnValue.of(value);
    }
    
    /**
     * Get column, representing supplied integer value with alias.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public static SqlColumn ofValue(int value, String alias) {
        return SqlColumnValue.of(value, alias);
    }
    
    /**
     * Get column, representing supplied float value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public static SqlColumn ofValue(float value) {
        return SqlColumnValue.of(value);
    }
    
    /**
     * Get column, representing supplied float value with alias.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public static SqlColumn ofValue(float value, String alias) {
        return SqlColumnValue.of(value, alias);
    }
    
    /**
     * Get column, representing supplied double value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public static SqlColumn ofValue(double value) {
        return SqlColumnValue.of(value);
    }
    
    /**
     * Get column, representing supplied double value with alias.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public static SqlColumn ofValue(double value, String alias) {
        return SqlColumnValue.of(value, alias);
    }
    
    /**
     * Get column, representing single bind variable.
     * 
     * @param bindVariable is bind value, 
     * @return SqlColumn representing single bind variable
     */
    public static SqlColumn ofBind(BindVariable bindVariable) {
        return SqlColumnBind.of(bindVariable);
    }
    
    /**
     * Get column, representing single bind variable with alias.
     * 
     * @param bindVariable is bind variable
     * @param alias is alias to be assigned to column
     * @return SqlColumn representing single bind variable
     */
    public static SqlColumn ofBind(BindVariable bindVariable, String alias) {
        return SqlColumnBind.of(bindVariable, alias);
    }
    
    /**
     * Get column with specified column text and type, build on from
     * element.
     * Column is defined as SQL expression with from element referenced by
     * alias {@literal <<0>>}. {@literal <<0>>} is replaced with referenced
     * table alias on use. It looks for further {@literal <<i>>} expressions
     * with consecutive indices starting from 2 and if it finds them, it
     * replaces those occurences with al<i>i</i> alias - while it might not make
     * much sense now, it is and older construct that was used to construct
     * aliases in attribute text and query and is kept for backward
     * compatibility.
     * Returned column is immutable object; it does not copy supplied
     * {@code SqlFromElem}, instead it keeps reference to it.
     * 
     * @param fromElem is elements in from clause this sqlText depends on
     * @param sqlText is text defining sqlText, using <<0>> to reference
     * supplied from element
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumn ofTextAndTable(SqlFromElem fromElem, String sqlText
            , Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElem, sqlText, type);
    }

    /**
     * Returns column with specified column text and type, build on from
     * elements.
     * Column is defined as SQL expression with from elements referenced by
     * aliases {@literal <<i>>}. {@literal <<i>>} is replaced with referenced
     * table aliases on use, i starting from 0 for the first referenced item.
     * After all supplied tables are used up, it looks for further
     * {@literal <<i>>} expressions with consecutive indices and if it finds
     * them, it replaces those occurences with al<i>i</i> alias - while it might
     * not make much sense now, it is an older construct that was used to
     * construct aliases in attribute text and query and is kept for backward
     * compatibility.
     * Returned column is immutable object; it does not copy supplied
     * {@code SqlFromElem}s, instead it keeps references to them.
     * 
     * @param fromElems is list of elements in from clause this sqlText depends
     * on
     * @param sqlText is text defining sqlText, using <<0>> to reference
     * supplied from element
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumn ofTextAndTables(List<SqlFromElem> fromElems
            , String sqlText, Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElems, sqlText, type);
    }

    /**
     * Get column with specified column text and type, build on from
     * element.
     * Column is defined as SQL expression with from element referenced by
     * alias {@literal <<0>>}. {@literal <<0>>} is replaced with referenced
     * table alias on use. It looks for further {@literal <<i>>} expressions
     * with consecutive indices starting from 2 and if it finds them, it
     * replaces those occurences with al<i>i</i> alias - while it might not make
     * much sense now, it is and older construct that was used to construct
     * aliases in attribute text and query and is kept for backward
     * compatibility.
     * Returned column is immutable object; it does not copy supplied
     * {@code SqlFromElem}, instead it keeps reference to it.
     * 
     * @param fromElem is elements in from clause this sqlText depends on
     * @param sqlText is text defining sqlText, using <<0>> to reference
     * supplied from element
     * @param alias is alias this column will use
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumn ofTextAndTable(SqlFromElem fromElem, String sqlText
            , String alias, Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElem, sqlText, alias, type);
    }

    /**
     * Returns column with specified column text and type, build on from
     * elements.
     * Column is defined as SQL expression with from elements referenced by
     * aliases {@literal <<i>>}. {@literal <<i>>} is replaced with referenced
     * table aliases on use, i starting from 0 for the first referenced item.
     * After all supplied tables are used up, it looks for further
     * {@literal <<i>>} expressions with consecutive indices and if it finds
     * them, it replaces those occurences with al<i>i</i> alias - while it might
     * not make much sense now, it is an older construct that was used to
     * construct aliases in attribute text and query and is kept for backward
     * compatibility.
     * Returned column is immutable object; it does not copy supplied
     * {@code SqlFromElem}s, instead it keeps references to them.
     * 
     * @param fromElems is list of elements in from clause this sqlText depends
     * on
     * @param sqlText is text defining sqlText, using <<0>> to reference
     * supplied from element
     * @param alias is alias this column will use
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumn ofTextAndTables(List<SqlFromElem> fromElems
            , String sqlText, String alias, Class<? extends Dt> type) {
        return SqlColumnTextAndTables.of(fromElems, sqlText, alias, type);
    }

    /**
     * Build SQL code for column (full line).
     * 
     * @param code is CodeBuilder used to build SQL statement
     * @param addAliasClause defines if alias clause should be placed after
     * column code
     */
    public void buildSql(CodeBuilder code, boolean addAliasClause);
    
    /**
     * Build SQL code for column; if possible, build it in line.
     * Note that it is perfectly legal if code is split to multiple lines,
     * for example if column is complete SQL statement. But if possible (e.g.
     * in case it is simple column), it is placed on single line
     * 
     * @param code is CodeBuilder used to build SQL statement
     * @param addAliasClause defines if alias clause should be placed after
     * column code
     */
    public void buildSqlNoNewLine(CodeBuilder code
            , boolean addAliasClause);

    /**
     * Translates SqlColumn to ColumnDef, used by SqlCall.
     * 
     * @return ColumnDef describing given column, null if ColumnDef cannot be
     * constructed from SqlColumn
     */
    public ColumnDef getColumnDef();
   
    /**
     * @return the alias
     */
    public String getAlias();

    /**
     * @return the indexed flag
     */
    public boolean isIndexed();
    
    /**
     * Retrieve list of from elements this column depends on.
     * In default implementation, returns empty list.
     * 
     * @return list of elements this column is connected to
     */
    public default List<SqlFromElem> getFromElems() {
        return Collections.EMPTY_LIST;
    }

}
