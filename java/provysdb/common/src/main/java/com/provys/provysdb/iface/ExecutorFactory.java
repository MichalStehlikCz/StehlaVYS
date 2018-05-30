/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.iface;

/**
 * Factory class to produce various executors
 * 
 * @author stehlik
 */
public interface ExecutorFactory {
    public JsonQueryExecutor getJsonQueryExecutor();
    public MapQueryExecutor getMapQueryExecutor();
}
