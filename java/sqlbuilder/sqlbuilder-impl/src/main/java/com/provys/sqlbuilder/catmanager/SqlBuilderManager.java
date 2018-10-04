/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

/**
 * Interface to class, granting access to all SqlBuilder catalogue repositories.
 * 
 * @author stehlik
 */
public interface SqlBuilderManager {

    /**
     * Function retrieves SqlBuilder compatible entity repository.
     * 
     * @return Entity repository
     */
    SqlBuilderEntityRepository<?> getEntityRepository();

    /**
     * Function retrieves SqlBuilder compatible attribute repository.
     * 
     * @return Attr repository
     */
    SqlBuilderAttrRepository<?> getAttrRepository();
    
    /**
     * Function retrieves SqlBuilder compatible domain repository.
     * 
     * @return Domain repository
     */
    SqlBuilderDomainRepository<?> getDomainRepository();
}
