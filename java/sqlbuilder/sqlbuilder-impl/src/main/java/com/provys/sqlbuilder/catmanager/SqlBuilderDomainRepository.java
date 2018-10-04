/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

import com.provys.common.confobj.ConfNMObjectRepository;

/**
 * Domain repository functionality needed to support SqlBuilder.
 * 
 * @author stehlik
 * @param <T> is implementation of domain model, compatible with SqlBuilder
 */
@SuppressWarnings("MarkerInterface")
public interface SqlBuilderDomainRepository<T extends SqlBuilderDomain>
        extends ConfNMObjectRepository<T> {
    
}
