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
 * Connects specified field to attribute in PROVYS metadata catalogue. Together
 * with entity level mapping using @ProvysEntity annotation and field level
 * mapping using @ProvysKey annotation it enables loader or manipulator classes
 * to prepare SQL and load / modify data in underlying PROVYS database
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ProvysAttr {

    /**
     * Returns internal name of attribute.
     *
     * @return internal name of attribute
     */
    String value();
}
