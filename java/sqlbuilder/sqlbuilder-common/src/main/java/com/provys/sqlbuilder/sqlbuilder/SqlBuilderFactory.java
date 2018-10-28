/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.datatypes.Dt;
import com.provys.provysdb.call.BindVariable;
import java.util.List;

/**
 * Creates instances of basic SqlBuilder objects.
 * 
 * @author stehlik
 */
public interface SqlBuilderFactory {

    /**
     * Create simple SqlSelectBuilder based on supplied table name and alias.
     * 
     * @param table is table name or expression to be used in stead in FROM
     * clause
     * @param alias is alias to be given to this table
     * @return SqlSelectBuilder object based on supplied table
     */
    public SqlSelectBuilder getSimpleSelect(String table, String alias);

    /**
     * Create simple SqlSelectBuilder based on supplied column, table name
 and alias.
     * 
     * @param column is column to be selected from resulting select expression
     * @param table is table name or expression to be used in stead in FROM
     * clause
     * @param alias is alias to be given to this table
     * @return SqlSelectBuilder object based on supplied table
     */
    public SqlSelectBuilder getSimpleSelect(SqlColumn column, String table
            , String alias);
    
    /**
     * Create simple SqlQueryBuilder based on bind variable, representing key.
     * 
     * @param bindVariable is supplied bind variable, to be used for column
     * @return 
     */
    public SqlQueryBuilder getBindQuery(BindVariable bindVariable);

    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(Dt value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(Dt value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(String value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(String value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(int value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(int value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(float value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(float value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(double value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(double value, String alias);
    
    /**
     * Get column, representing single bind variable.
     * 
     * @param bindVariable is bind value, 
     * @return SqlColumn representing single bind variable
     */
    public SqlColumn getBindColumn(BindVariable bindVariable);
    
    /**
     * Get column, representing single bind variable.
     * 
     * @param bindVariable is bind variable
     * @param alias is alias to be assigned to column
     * @return SqlColumn representing single bind variable
     */
    public SqlColumn getBindColumn(BindVariable bindVariable, String alias);
    
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
    public SqlColumn getColumnWithTextAndTable(SqlFromElem fromElem
            , String sqlText, Class<? extends Dt> type);

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
    public SqlColumn getColumnWithTextAndTables(List<SqlFromElem> fromElems
            , String sqlText, Class<? extends Dt> type);

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
    public SqlColumn getColumnWithTextAndTable(SqlFromElem fromElem
            , String sqlText, String alias, Class<? extends Dt> type);

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
    public SqlColumn getColumnWithTextAndTables(List<SqlFromElem> fromElems
            , String sqlText, String alias, Class<? extends Dt> type);

    /**
     * Get AND where condition.
     * 
     * @return empty AND where condition.
     */
    public SqlWhereCondAnd getWhereCondAnd();

    /**
     * Get OR where condition.
     * 
     * @return empty OR where condition.
     */
    public SqlWhereCondOr getWhereCondOr();
    
    /**
     * Get where condition with two operands compared by operator.
     * 
     * @param column1 is first comparison operand
     * @param operator is used operator
     * @param column2 is second comparison operand
     * @return condition that compares supplied columns
     */
    public SqlWhereCond getWhereCondTwoOp(SqlColumn column1
            , SqlOperator2 operator, SqlColumn column2);

    /**
     * Get where condition with two operands compared by operator, potentially
     * negative.
     * 
     * @param column1 is first comparison operand
     * @param operator is used operator
     * @param column2 is second comparison operand
     * @param negative allows to specify that comparison should be negative.
     * Note that if columns are not sure to be NOT NULL, this might result
     * in adding OR and NULL evaluation to ensure resulting expression is
     * negation of supplied one
     * @return condition that compares supplied columns
     */
    public SqlWhereCond getWhereCondTwoOp(SqlColumn column1
            , SqlOperator2 operator, SqlColumn column2, boolean negative);

}
