/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

import com.provys.common.confobj.ConfNMObjectRepository;

/**
 *
 * @author stehlik
 * @param <T> is implementation of entity model, compatible with SqlBuilder
 */
@SuppressWarnings("MarkerInterface")
public interface SqlBuilderEntityRepository<T extends SqlBuilderEntity>
        extends ConfNMObjectRepository<T> {
    
}
