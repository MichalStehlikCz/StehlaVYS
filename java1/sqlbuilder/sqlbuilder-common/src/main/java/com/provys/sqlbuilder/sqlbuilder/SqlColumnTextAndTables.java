/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Column defined as SQL expression with alias {@literal <<i>>}.
 * {@literal <<i>>} is replaced with referenced table alias on use, i starting
 * from 0 for the first referenced item (e.g. if you use single fromElem
 * constructor). After all supplied tables are used up, it looks for further
 * {@literal <<i>>} expressions with consecutive indices and if it finds them,
 * it replaces those occurences with al<i>i</i> alias - while it might not make
 * much sense now, it is older construct that was used to construct aliases in
 * attribute text and query and is kept for backward compatibility.
 * {@code SqlColumnTextAndTables} is immutable object; it does not copy
 * SqlFromElem, instead it keeps reference to it.
 * 
 * @author stehlik
 */
class SqlColumnTextAndTables extends AbstractSqlColumn implements SqlColumn {
    
    private final String sqlText; // text representing table sqlText, alias referenced by
                         // <<i>> where i is alias index
    private final List<SqlFromElem> fromElems; // list of tables sqlText is built
                         // on
    
    /**
     * Returns column with specified column text and type, build on from
     * element.
     * 
     * @param fromElem is element in from clause this sqlText depends on
     * @param column is text defining sqlText, using <<0>> to reference supplied
     * from element
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumnTextAndTables of(SqlFromElem fromElem
            , String sqlText, Class<? extends Dt> type) {
        List<SqlFromElem> fromElems = new ArrayList<> (1);
        fromElems.add(fromElem);
        return new SqlColumnTextAndTables(fromElems, sqlText, type);
    }

    /**
     * Returns column with specified column text and type, build on from
     * elements.
     * 
     * @param fromElems is list of elements in from clause this sqlText depends
     * on
     * @param column is text defining sqlText, using <<0>> to reference supplied
     * from element
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumnTextAndTables of(List<SqlFromElem> fromElems
            , String sqlText, Class<? extends Dt> type) {
        return new SqlColumnTextAndTables(fromElems, sqlText, type);
    }

    /**
     * Returns column with specified column text, alias and type, build on from
     * element.
     * 
     * @param fromElem is element in from clause this sqlText depends on
     * @param column is text defining sqlText, using <<0>> to reference supplied
     * from element
     * @param alias is alias this column will use
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumnTextAndTables of(SqlFromElem fromElem
            , String sqlText, String alias, Class<? extends Dt> type) {
        List<SqlFromElem> fromElems = new ArrayList<> (1);
        fromElems.add(fromElem);
        return new SqlColumnTextAndTables(fromElems, sqlText, alias, type);
    }

    /**
     * Returns column with specified column text, alias and type, build on from
     * elements.
     * 
     * @param fromElems is list of elements in from clause this sqlText depends
     * on
     * @param column is text defining sqlText, using <<0>> to reference supplied
     * from element
     * @param type is data type associated with sqlText
     * @return sqlText with specified characteristics
     */
    public static SqlColumnTextAndTables of(List<SqlFromElem> fromElems
            , String sqlText, String alias, Class<? extends Dt> type) {
        return new SqlColumnTextAndTables(fromElems, sqlText, alias, type);
    }

    private SqlColumnTextAndTables(List<SqlFromElem> fromElems, String sqlText
            , Class<? extends Dt> type) {
        super(type);
        this.sqlText = sqlText;
        this.fromElems = new ArrayList<>(fromElems);
    }

    private SqlColumnTextAndTables(List<SqlFromElem> fromElems, String sqlText
            , Class<? extends Dt> type, boolean indexed) {
        super(type, indexed);
        this.sqlText = sqlText;
        this.fromElems = new ArrayList<>(fromElems);
    }

    private SqlColumnTextAndTables(List<SqlFromElem> fromElems, String sqlText
            , String alias, Class<? extends Dt> type) {
        super(alias, type);
        this.sqlText = sqlText;
        this.fromElems = new ArrayList<>(fromElems);
    }

    private SqlColumnTextAndTables(List<SqlFromElem> fromElems, String sqlText
            , String alias, Class<? extends Dt> type, boolean indexed) {
        super(alias, type, indexed);
        this.sqlText = sqlText;
        this.fromElems = new ArrayList<>(fromElems);
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        AliasReplacer replacer = new AliasReplacer(sqlText);
        fromElems.forEach(replacer);
        String sqlCode = replacer.getSqlText();
        // and generate further aliases (doesn't make much sense, but long time
        // ago it seemed to make sense and it is thus used in SQL attributes and
        // queries
        int aliasNum = fromElems.size();
        while (sqlCode.contains("<<" + aliasNum + ">>")) {
            sqlCode = sqlCode.replace("<<" + aliasNum + ">>", "al" + aliasNum);
            aliasNum++;
        }
        code.appendWrapped(sqlCode);
        if (addAliasClause) {
            appendAlias(code);
        }
    }

    @Override
    public List<SqlFromElem> getFromElems() {
        return Collections.unmodifiableList(fromElems);
    }

    /**
     * Class is used to replace {@literal <<i>>} placeholders with aliases.
     */
    private static class AliasReplacer implements Consumer<SqlFromElem> {

        private String sqlText;
        private int index;
        
        AliasReplacer(String column) {
            this.sqlText = column;
            this.index = 0;
        }

        @Override
        public void accept(SqlFromElem fromElem) {
            if (fromElem.getAlias() == null) {
                if (sqlText.contains("<<" + index + ">>")) {
                    throw new FromAliasMissingException();
                }
            }
            sqlText = sqlText.replace("<<" + index + ">>", fromElem.getAlias());
            index++;
        }
        
        public String getSqlText() {
            return sqlText;
        }

    }
    /**
     * Cannot build sqlText as referenced from element does not provide alias.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class FromAliasMissingException extends ProvysException {

        private static final long serialVersionUID = 1L;

        FromAliasMissingException() {
            super("From element does not provide alias needed to build column");
        }
    }
    
}
