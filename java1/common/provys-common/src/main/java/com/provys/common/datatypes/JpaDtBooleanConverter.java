/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter supports storage of {@code DtBoolean} properties to database.
 * Mapping respects PROVYS mapping of boolean type to char
 *
 * @author stehlik
 */
@Converter(autoApply = true)
public class JpaDtBooleanConverter
        implements AttributeConverter<DtBoolean, String> {

    @Override
    public String convertToDatabaseColumn(DtBoolean value) {
        return value.toStringValue();
    }

    @Override
    public DtBoolean convertToEntityAttribute(String dbValue) {
        return DtBoolean.fromStringValue(dbValue);
    }
}
