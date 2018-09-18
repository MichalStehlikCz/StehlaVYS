/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.provysdb.call.BindValue;
import com.provys.sqlbuilder.iface.CodeBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Implementation of code builder - tool to build SQL text with formatting.
 * 
 * @author stehlik
 */
public class CodeBuilderImpl implements CodeBuilder {
    
    final StringBuilder text;
    boolean newLine = false;
    final List<BindValue> bindValues;
    String ident;
    final Stack<String> tempIdents;

    public CodeBuilderImpl() {
        text = new StringBuilder();
        bindValues = new ArrayList<> (10);
        tempIdents = new Stack<>();
    }

    @Override
    public CodeBuilder addLine(String line) {
        if (newLine) {
            text.append(ident);
        }
        text.append(line);
        text.append('\n');
        newLine = true;
        return this;
    }

    @Override
    public CodeBuilder add(String text) {
        if (newLine) {
            this.text.append(text);
            newLine = false;
        }
        this.text.append(text);
        return this;
    }

    @Override
    public CodeBuilder increaseIdentBy(int chars) {
        StringBuilder identBuilder = new StringBuilder().append(ident);
        for (int i=1;i<=chars;i++) {
            identBuilder.append(' ');
        }
        ident = identBuilder.toString();
        return this;
    }

    @Override
    public CodeBuilder decreaseIdentBy(int chars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder decreaseIdentTo(int chars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder addIdent(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder addIdent(String firstIdent, String regularIdent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder addTempIdent(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder addTempIdent(String firstIdent, String regularIdent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder removeTempIdent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder setIdent(int chars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder setIdent(String ident) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodeBuilder addBind(BindValue bindValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addUniqueBind(BindValue bindValue, boolean allowReuse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BindValue> getBindValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
