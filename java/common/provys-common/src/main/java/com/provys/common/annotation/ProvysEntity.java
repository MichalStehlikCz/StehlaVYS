/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.annotation;

import java.lang.annotation.*;

/**
 *
 * @author stehlik
 * 
 * Connects specified class to entity in PROVYS metadata catalogue. Together
 * with field level mappings using @ProvysKey and @ProvysAttr annotations it
 * enables loader or manipulator classes to prepare SQL and load / modify
 * data in underlying PROVYS database
 */
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.TYPE)
public @interface ProvysEntity {
    /**
     * Returns internal name of entity.
     * @return internal name of entity
     */
    String value();
}
