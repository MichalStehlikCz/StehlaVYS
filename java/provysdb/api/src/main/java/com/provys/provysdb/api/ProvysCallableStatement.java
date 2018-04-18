/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

import java.sql.CallableStatement;

/**
 * Interface extends CallableStatement for use in PROVYS system. It is
 * implemented by ProvysCallableStatementImpl class in datasource module
 *
 * @author stehlik
 */
public interface ProvysCallableStatement extends CallableStatement {

}
