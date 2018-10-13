/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

/**
 * Custom JSON-B deserializer for SqlCall.
 * There are problems with arrays of objects with subtypes, as array
 * deserializer does not invoke annotation-based deserializers for items
 * 
 * @author stehlik
 */
public class JsonbSqlCallDeserializer {
    
}
