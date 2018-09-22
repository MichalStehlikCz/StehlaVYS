/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.BindValue;
import com.provys.sqlbuilder.iface.CodeBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of code builder - tool to build SQL text with formatting.
 * 
 * @author stehlik
 */
public class CodeBuilderImpl implements CodeBuilder {
    
    final StringBuilder text;
    private boolean newLine = false;
    final Map<String, BindValue> bindValues;
    private String ident;
    private String firstIdent;
    final Stack<String> tempIdents;

    public CodeBuilderImpl() {
        text = new StringBuilder(100);
        bindValues = new ConcurrentHashMap<> (10);
        tempIdents = new Stack<>();
        ident = "";
    }

    @Override
    public CodeBuilder addLine(String line) {
        add(line);
        setNewLine(true);
        return this;
    }

    @Override
    public CodeBuilder add(String text) {
        if (isNewLine()) {
            if (this.getFirstIdent() != null) {
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
    public CodeBuilder increaseIdentBy(int chars) {
        if (chars < 0) {
            throw new IncreaseByNegativeException();
        }
        StringBuilder identBuilder = new StringBuilder(chars+getIdent().length())
                .append(getIdent());
        for (int i=1;i<=chars;i++) {
            identBuilder.append(' ');
        }
        setIdent(identBuilder.toString());
        return this;
    }

    @Override
    public CodeBuilder decreaseIdentBy(int chars) {
        if (chars < 0) {
            throw new DecreaseByNegativeException();
        }
        if (chars>getIdent().length()) {
            throw new NegativeIdentLengthException();
        }
        setIdent(getIdent().substring(0, getIdent().length() - chars));
        return this;
    }

    @Override
    public CodeBuilder decreaseIdentTo(int chars) {
        if (chars < 0) {
            throw new NegativeIdentLengthException();
        }
        if (chars > getIdent().length()) {
            throw new DecreaseByNegativeException();
        }
        setIdent(getIdent().substring(0, chars));
        return this;
    }

    @Override
    public CodeBuilder setIdent(int chars) {
        ident = "";
        this.setFirstIdent(null);
        return increaseIdentBy(chars);
    }

    @Override
    public String getIdent() {
        return ident;
    }

    @Override
    public CodeBuilder setIdent(String ident) {
        this.ident = ident;
        this.setFirstIdent(null);
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
        this.setIdent(regularIdent);
        this.setFirstIdent(firstIdent);
        return this;
    }
    
    @Override
    public CodeBuilder setIdent(String firstIdent, String regularIdent
            , int chars) {
        if ((firstIdent.length() > chars) | (regularIdent.length() > chars)) {
            throw new SuppliedLengthTooShortException();
        }
        this.setIdent(String.format("%1$" + chars + "s", regularIdent));
        this.setFirstIdent(String.format("%1$" + chars + "s", firstIdent));
        return this;
    }
    
    /**
     * @return the nextIdent
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

    @Override
    public CodeBuilder addIdent(String text) {
        setIdent(getIdent() + text);
        return this;
    }

    @Override
    public CodeBuilder addIdent(String firstIdent, String regularIdent) {
        // SetIdent cancels first ident - thus, we have to save first ident to
        // local variable
        String newFirstIdent = getIdent() + firstIdent;
        setIdent(getIdent() + regularIdent);
        setFirstIdent(newFirstIdent);
        return this;
    }

    @Override
    public CodeBuilder setTempIdent(String ident) {
        tempIdents.push(getIdent());
        return setIdent(ident);
    }

    @Override
    public CodeBuilder setTempIdent(String firstIdent, String regularIdent) {
        tempIdents.push(getIdent());
        return setIdent(firstIdent, regularIdent);
    }

    @Override
    public CodeBuilder addTempIdent(String text) {
        tempIdents.push(getIdent());
        return addIdent(text);
    }

    @Override
    public CodeBuilder addTempIdent(String firstIdent, String regularIdent) {
        tempIdents.push(getIdent());
        return addIdent(firstIdent, regularIdent);
    }

    @Override
    public CodeBuilder removeTempIdent() {
        return setIdent(tempIdents.pop());
    }

    @Override
    public synchronized CodeBuilder addBind(BindValue bindValue) {
        BindValue existingBind = bindValues.putIfAbsent(bindValue.getName()
                , bindValue);
        if (existingBind != null) {
            if (!existingBind.equals(bindValue)) {
                throw new DuplicateBindException(bindValue.getName());
            }
        }
        return this;
    }

    @Override
    public CodeBuilder addBind(List<BindValue> bindValues) {
        bindValues.forEach((bindValue) -> {this.addBind(bindValue);});
        return this;
    }

    @Override
    public synchronized String addUniqueBind(BindValue bindValue, boolean allowReuse) {
        String name = bindValue.getName();
        int index = 1;
        while ((this.bindValues.containsKey(name))
            & (!allowReuse | !this.bindValues.get(name).equals(bindValue))) {
          name = bindValue.getName() + ++index;
        }
        if (!this.bindValues.containsKey(name)) {
            if (index == 1) {
                this.bindValues.putIfAbsent(name, bindValue);
            } else {
                this.bindValues.putIfAbsent(name, new BindValue(name
                        , bindValue.getType(), bindValue.getValue()));
            }
            
        }
        
        return name;
    }

    @Override
    public String getCode() {
        return this.text.toString();
    }

    @Override
    public List<BindValue> getBindValues() {
        return new ArrayList<> (bindValues.values());
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

}
