/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;
import com.provys.common.error.ProvysException;

/**
 * Generic loader ancestor for configuration objects.
 * Its only functionality is that it supplies interface to load object of
 * given type from chosen source. Particular implementations than allow either
 * load from database or retrieve object from particular service
 * 
 * @author stehlik
 * @param <T> Configuration object class this loader retrieves from database
 * 
 */
public interface ConfObjectLoader<T extends ConfObject> {
    
    public T load(DtUid id);
    
    /**
     * Exception raised when object is not found using Id.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ConfObjectNotFoundUsingIdException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        public ConfObjectNotFoundUsingIdException(Class<?> confObjectClass
                , DtUid id) {
            super("Object not found using Id, class "
                    + confObjectClass.getSimpleName()
                    + ", Id=" + id.toString());
        }
    }
    
    /**
     * Exception raised when more objects are not found using Id.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ConfObjectNotUniqueUsingIdException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        public ConfObjectNotUniqueUsingIdException(Class<?> confObjectClass
                , DtUid id) {
            super("Object not found using Id, class "
                    + confObjectClass.getSimpleName()
                    + ", Id=" + id.toString());
        }
    }
    
}
