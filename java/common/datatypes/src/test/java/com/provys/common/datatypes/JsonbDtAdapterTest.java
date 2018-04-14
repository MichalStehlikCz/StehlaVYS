/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;
import junitparams.Parameters;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Generic ancestor for testing Dt adapter classes
 * @param <T> Dt ancestor representing original object type
 * @param <U> target adapted type
 * @param <V> adapter type being tested
 * @author stehlik
 */
abstract public class JsonbDtAdapterTest<T extends Dt, U, V
        extends JsonbAdapter<T, U>> {
    
    /**
     * Adapter used to running tests. It is instantiated in setUp (before test)
     * method of subclasses
     */
    protected V adapter;

    /**
     * Test of adaptToJson method, of class JsonbDtBooleanAdapter.
     * @param original object supplied to adapter
     * @param expectedAdapted is used for comparison with actual adaptor result
     * @throws java.lang.Exception - in reality, specialised types do not throw
     * unchecked exceptions, but...
     */
    @Test
    @Parameters(method = "parametersForAdapter")
    public void testAdaptToJson(T original, U expectedAdapted) throws Exception {
        if (adapter == null) {
            throw new RuntimeException("Uninitialised adapter");
        }
        U adapted = adapter.adaptToJson(original);
        assertEquals("Adapted and desired output do not match"
                , expectedAdapted, adapted);
    }

    /**
     * Test of adaptFromJson method, of class JsonbDtBooleanAdapter.
     * @param expectedOriginal expected result of reverse adaptation
     * @param adapted supplied object for reverse adaptation
     * @throws java.lang.Exception - in reality, specialised types do not throw
     * unchecked exceptions, but...
     */
    @Test
    @Parameters(method = "parametersForAdapter")
    public void testAdaptFromJson(T expectedOriginal, U adapted) throws Exception {
        if (adapter == null) {
            throw new RuntimeException("Uninitialised adapter");
        }
        T original = adapter.adaptFromJson(adapted);
        assertEquals("Adapted and desired output do not match", expectedOriginal, original);
    }
    
}
