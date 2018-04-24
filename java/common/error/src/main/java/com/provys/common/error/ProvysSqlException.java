/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.error;

import java.sql.SQLException;

/**
 *
 * @author stehlik
 */
public class ProvysSqlException extends ProvysException{
    public ProvysSqlException(SQLException cause) {
        super("SQL Error", cause);
    }
}
