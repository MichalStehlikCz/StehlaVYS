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
 * Marks given field as entity key. Together with class level entity mapping
 * using @ProvysEntity and field level mapping using @ProvysAttr annotation it
 * enables loader or manipulator classes to prepare SQL and load / modify data
 * in underlying PROVYS database
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ProvysKey {
}
