/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.BindParameter;
import com.provys.provysdb.call.BindValue;
import com.provys.provysdb.call.BindVariable;
import com.provys.sqlbuilder.iface.CodeBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of code builder - tool to build SQL text with formatting.
 * 
 * @author stehlik
 */
class CodeBuilderImpl implements CodeBuilder {
    
    final StringBuilder text;
    private boolean newLine = false;
    final Map<String, BindVariable> bindVariables;
    private IdentStatus ident = new IdentStatus();
    final Stack<IdentStatus> tempIdents = new Stack<>();

    /**
     * Default constructor for CodeBuilder.
     * Sets all fields to their default values.
     */
    public CodeBuilderImpl() {
        text = new StringBuilder(100);
        bindVariables = new ConcurrentHashMap<> (10);
    }

    @Override
    public CodeBuilder append(String text) {
        if (isNewLine()) {
            if (getFirstIdent() != null) {
                this.text.append(getFirstIdent());
                this.setFirstIdent(null);
            } else {
                this.text.append(getIdent());
            }
            setNewLine(false);
        }
        this.text.append(text);
        return this;
    }

    @Override
    public CodeBuilder appendWrapped(String text) {
       return append(text.replace("\n", String.format("%1$-"
                + this.ident.getIdent().length() + "s", "\n")));
    }

    @Override
    public CodeBuilder appendLine() {
        setNewLine(true);
        return this;
    }

    @Override
    public CodeBuilder appendLine(String line) {
        append(line);
        return appendLine();
    }

    @Override
    public CodeBuilder increaseIdent(int chars) {
        return increaseIdent(" ", chars);
    }

    @Override
    public CodeBuilder decreaseIdent(int chars) {
        if (chars < 0) {
            throw new DecreaseByNegativeException();
        }
        if (chars>this.ident.getIdent().length()) {
            throw new NegativeIdentLengthException();
        }
        setIdent(this.ident.getIdent().substring(0
                , this.ident.getIdent().length() - chars));
        return this;
    }

    @Override
    public CodeBuilder decreaseIdentTo(int chars) {
        if (chars < 0) {
            throw new NegativeIdentLengthException();
        }
        if (chars > this.ident.getIdent().length()) {
            throw new DecreaseByNegativeException();
        }
        setIdent(this.ident.getIdent().substring(0, chars));
        return this;
    }

    private String getIdent() {
        return this.ident.getIdent();
    }

    @Override
    public CodeBuilder setIdent(int chars) {
        setIdent("");
        return increaseIdent(chars);
    }

    private CodeBuilder setIdent(IdentStatus ident) {
        this.ident = ident;
        return this;
    }

    @Override
    public CodeBuilder setIdent(String ident) {
        this.ident = new IdentStatus(ident);
        return this;
    }

    @Override
    public CodeBuilder setIdent(String ident, int chars) {
        if (ident.length() > chars) {
            throw new SuppliedLengthTooShortException();
        }
        return setIdent(String.format("%1$" + chars + "s", ident));
    }

    @Override
    public CodeBuilder setIdent(String firstIdent, String regularIdent) {
        this.ident = new IdentStatus(firstIdent, regularIdent);
        return this;
    }
    
    @Override
    public CodeBuilder setIdent(String firstIdent, String regularIdent
            , int chars) {
        if ((firstIdent.length() > chars) | (regularIdent.length() > chars)) {
            throw new SuppliedLengthTooShortException();
        }
        this.setIdent(String.format("%1$" + chars + "s", regularIdent)
                , String.format("%1$" + chars + "s", firstIdent));
        return this;
    }
    
    private CodeBuilder setIdent(String firstIdent, String regularIdent
            , int chars, CombinationType combinationType) {
        if ((firstIdent.length() > chars) | (regularIdent.length() > chars)) {
            throw new SuppliedLengthTooShortException();
        }
        this.ident = new IdentStatus(
                String.format("%1$" + chars + "s", regularIdent)
              , String.format("%1$" + chars + "s", firstIdent)
              , combinationType);
        return this;
    }
    
    private String getFirstIdent() {
        return this.ident.getFirstIdent();
    }

    /**
     * @param firstIdent the firstIdent to set
     */
    public void setFirstIdent(String firstIdent) {
        this.ident.setFirstIdent(firstIdent);
    }

    @Override
    public CodeBuilder appendIdent(String text) {
        setIdent(getIdent() + text);
        return this;
    }

    @Override
    public CodeBuilder appendIdent(String firstIdent, String regularIdent) {
        // SetIdent cancels first ident - thus, we have to save first ident to
        // local variable
        setIdent(getIdent() + firstIdent, getIdent() + regularIdent);
        return this;
    }

    @Override
    public CodeBuilder increaseIdent(String ident, int increaseBy) {
        if (increaseBy < 0) {
            throw new IncreaseByNegativeException();
        }
        setIdent(ident, getIdent().length()+increaseBy);
        return this;
    }

    @Override
    public CodeBuilder increaseIdent(String firstIdent, String regularIdent
            , int increaseBy) {
        if (increaseBy < 0) {
            throw new IncreaseByNegativeException();
        }
        setIdent(firstIdent, regularIdent, getIdent().length()+increaseBy);
        return this;
    }

    /**
     * Put current ident on temporary idents stack.
     * Note that it should never be used without subsequent replacement of
     * ident
     */
    private void putTempIdent() {
        tempIdents.push(this.ident);
    }

    @Override
    public CodeBuilder setTempIdent(String ident) {
        putTempIdent();
        return setIdent(ident);
    }

    @Override
    public CodeBuilder setTempIdent(String firstIdent, String regularIdent) {
        putTempIdent();
        return setIdent(firstIdent, regularIdent);
    }

    @Override
    public CodeBuilder appendTempIdent(String text) {
        putTempIdent();
        return appendIdent(text);
    }

    @Override
    public CodeBuilder appendTempIdent(String firstIdent, String regularIdent) {
        putTempIdent();
        return appendIdent(firstIdent, regularIdent);
    }

    @Override
    public CodeBuilder increaseTempIdent(int increaseBy) {
        return increaseTempIdent("", increaseBy);
    }

    @Override
    public CodeBuilder increaseTempIdent(String ident, int increaseBy) {
        if (increaseBy < 0) {
            throw new IncreaseByNegativeException();
        }
        putTempIdent();
        setIdent(ident, getIdent().length()+increaseBy);
        return this;
    }

    private CodeBuilder increaseTempIdent(String firstIdent, String regularIdent
            , int increaseBy, CombinationType combinationType) {
        if (increaseBy < 0) {
            throw new IncreaseByNegativeException();
        }
        putTempIdent();
        setIdent(firstIdent, regularIdent, getIdent().length()+increaseBy
                , combinationType);
        return this;
    }

    @Override
    public CodeBuilder increaseTempIdent(String firstIdent, String regularIdent
            , int increaseBy) {
        return increaseTempIdent(firstIdent, regularIdent, increaseBy
                , CombinationType.NONE);
    }

    @Override
    public CodeBuilder increaseTempIdentAnd() {
        if (this.ident.getCombinationType() == CombinationType.AND) {
            this.ident.increaseTempLevel();
        } else {
            if (this.ident.getCombinationType() == CombinationType.OR) {
                appendLine("(");
                this.ident.setAddBracket(true);
            }
            increaseTempIdent("", "AND", 2, CombinationType.AND);
        }
        return this;
    }

    @Override
    public CodeBuilder increaseTempIdentOr() {
        if (this.ident.getCombinationType() == CombinationType.OR) {
            this.ident.increaseTempLevel();
        } else {
            if (this.ident.getCombinationType() == CombinationType.AND) {
                appendLine("(");
                this.ident.setAddBracket(true);
            }
            increaseTempIdent("", "OR", 2, CombinationType.OR);
        }
        return this;
    }

    @Override
    public CodeBuilder removeTempIdent() {
        if (this.ident.getTempLevel()>0) {
            this.ident.decreaseTempLevel();
        } else {
            setIdent(tempIdents.pop());
            if (this.ident.isAddBracket()) {
                appendLine(")");
                this.ident.setAddBracket(false);
            }
        }
        return this;
    }

    @Override
    public synchronized CodeBuilder addBind(BindVariable bindVariable) {
        BindVariable existingBind = bindVariables.putIfAbsent(bindVariable.getName()
                , bindVariable);
        if (existingBind != null) {
            if (!existingBind.equals(bindVariable)) {
                throw new DuplicateBindException(bindVariable.getName());
            }
        }
        return this;
    }

    @Override
    public CodeBuilder addBind(List<BindVariable> bindVariables) {
        bindVariables.forEach((bindVariable) -> {this.addBind(bindVariable);});
        return this;
    }

    @Override
    public synchronized String addUniqueBind(BindVariable bindVariable
            , boolean allowReuse) {
        String name = bindVariable.getName();
        int index = 1;
        while ((this.bindVariables.containsKey(name)) && (!allowReuse
                || !this.bindVariables.get(name).equals(bindVariable))) {
          name = bindVariable.getName() + ++index;
        }
        if (!this.bindVariables.containsKey(name)) {
            if (index == 1) {
                this.bindVariables.putIfAbsent(name, bindVariable);
            } else {
                if (bindVariable instanceof BindParameter) {
                    this.bindVariables.putIfAbsent(name, new BindParameter(name
                            , ((BindParameter) bindVariable).getValue()
                            , ((BindParameter) bindVariable).getMode()));
                } else if (bindVariable instanceof BindVariable) {
                    this.bindVariables.putIfAbsent(name, new BindValue(name
                        , ((BindValue) bindVariable).getValue()));
                } else {
                    this.bindVariables.putIfAbsent(name, new BindVariable(name
                        , bindVariable.getTypeClass()));
                }
            }
            
        }
        
        return name;
    }

    @Override
    public String getCode() {
        return this.text.toString();
    }

    @Override
    public List<BindVariable> getBindVariables() {
        return new ArrayList<> (bindVariables.values());
    }
    
    /**
     * @return the newLine
     */
    public boolean isNewLine() {
        return newLine;
    }

    /**
     * @param newLine the newLine to set
     */
    public void setNewLine(boolean newLine) {
        this.newLine = newLine;
    }

    /**
     * Exception raised when parameter to increaseIdentBy is negative
     */
    @SuppressWarnings("PublicInnerClass")
    static public class IncreaseByNegativeException extends ProvysException {

        private static final long serialVersionUID = 1L;

        IncreaseByNegativeException() {
            super("Cannot increase ident by negative amount");
        }
    }

    /**
     * Exception raised when parameter to decreaseIdentBy is negative
     */
    @SuppressWarnings("PublicInnerClass")
    static public class DecreaseByNegativeException extends ProvysException {

        private static final long serialVersionUID = 1L;

        DecreaseByNegativeException() {
            super("Cannot decrease ident by negative amount");
        }
    }

    /**
     * Operation would result in negative ident length
     */
    @SuppressWarnings("PublicInnerClass")
    static public class NegativeIdentLengthException extends ProvysException {

        private static final long serialVersionUID = 1L;

        NegativeIdentLengthException() {
            super("Cannot decrease ident length to negative value");
        }
    }

    /**
     * Bind with given name defined twice with different types / values
     */
    @SuppressWarnings("PublicInnerClass")
    static public class DuplicateBindException extends ProvysException {

        private static final long serialVersionUID = 1L;

        DuplicateBindException(String bindName) {
            super("Duplicate definition of bind "+bindName);
        }
    }

    /**
     * Supplied text is longer than required ident level
     */
    @SuppressWarnings("PublicInnerClass")
    static public class SuppliedLengthTooShortException extends ProvysException {

        private static final long serialVersionUID = 1L;

        SuppliedLengthTooShortException() {
            super("Required ident length is shorter than supplied text");
        }
    }
    private enum CombinationType{NONE, AND, OR}

    /**
     * Holds identation state of CodeBuilder.
     */
    private class IdentStatus {
        protected String ident;
        protected String firstIdent;
        protected CombinationType combinationType = CombinationType.NONE;
        protected int tempLevel = 0;
        protected boolean addBracket = false;

        IdentStatus() {
            this.ident = "";
        }

        IdentStatus(String ident) {
            this.ident = ident;
        }

        IdentStatus(String firstIdent, String regularIdent) {
            this.ident = regularIdent;
            this.firstIdent = firstIdent;
        }

        IdentStatus(String firstIdent, String regularIdent
                , CombinationType combinationType) {
            this.ident = regularIdent;
            this.firstIdent = firstIdent;
            this.combinationType = combinationType;
        }

        /**
         * @return the ident
         */
        public String getIdent() {
            return ident;
        }

        /**
         * @param ident the ident to set
         */
        public void setIdent(String ident) {
            this.ident = ident;
        }

        /**
         * @return the firstIdent
         */
        public String getFirstIdent() {
            return firstIdent;
        }

        /**
         * @param firstIdent the firstIdent to set
         */
        public void setFirstIdent(String firstIdent) {
            this.firstIdent = firstIdent;
        }

        /**
         * @return the combinationType
         */
        public CombinationType getCombinationType() {
            return combinationType;
        }

        /**
         * @param combinationType the combinationType to set
         */
        public void setCombinationType(CombinationType combinationType) {
            this.combinationType = combinationType;
        }

        /**
         * @return the tempLevel
         */
        public int getTempLevel() {
            return tempLevel;
        }

        /**
         * @param tempLevel the tempLevel to set
         */
        public void setTempLevel(int tempLevel) {
            this.tempLevel = tempLevel;
        }
        
        /**
         * Increases temporary level by one.
         */
        public void increaseTempLevel() {
            this.tempLevel++;
        }

        /**
         * Decreases temporary level by one.
         */
        public void decreaseTempLevel() {
            this.tempLevel--;
        }

        /**
         * @return the addBracket
         */
        public boolean isAddBracket() {
            return addBracket;
        }

        /**
         * @param addBracket the addBracket to set
         */
        public void setAddBracket(boolean addBracket) {
            this.addBracket = addBracket;
        }
    }
}
