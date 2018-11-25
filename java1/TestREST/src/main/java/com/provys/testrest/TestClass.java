/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.testrest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author stehlik
 */
public class TestClass {
    private List<Integer> list = new ArrayList<>(10);

    /**
     * @return the list
     */
    public List<Integer> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Integer> list) {
        this.list = new ArrayList<>(list);
    }
    
    public void add(Integer value) {
        list.add(value);
    }
    
}
