/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.iface;

/**
 * Interface to class, granting access to all catalogue repositories.
 * 
 * @author stehlik
 */
public interface CatalogueManager {
    EntityRepository getEntityRepository();
    AttrRepository getAttrRepository();
}
