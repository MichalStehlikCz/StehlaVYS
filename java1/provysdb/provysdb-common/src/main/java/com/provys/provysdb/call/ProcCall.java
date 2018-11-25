/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ProcCall is used for passing of PL/SQL statements for execution.
 * It is similar to CallableStatement, but methods do not require connection,
 * making it possible to implement class as serializable (even though it goes
 * at the price of not verifying that parameters are valid as they are added)
 * and then running prepared SQL call using statement
 * 
 * @author stehlik
 */
public class ProcCall implements Serializable {
    
    /**
     * sql field contains PL/SQL statement that is to be executed; most often,
     * it is anonymous PL/SQL block
     */
    private String sql;
    
    /**
     * parameters field contains list of bind parameters to be passed to
     * statement and retrieved after execution
     */
    private List<BindParameter> parameters;

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * @param sql the sql to set
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * @return the parameters
     */
    public List<BindParameter> getParameters() {
        return parameters;
    }

    /**
     * @param parameters to set / read after execution
     */
    public void setParameters(List<BindParameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * @param parameter is BindParameter to be added to given statement
     */
    public void addParameter(BindParameter parameter) {
        if (parameters == null) {
            parameters=new ArrayList<>(10);
        }
        parameters.add(parameter);
    }

}
