/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Generic class acting as memory storage for configuration objects.
 * It supplies methods for loading configuration object and accessing loaded
 * instances by Id. Subclasses can supply additional indices to allow access by
 * other properties (e.g. by internal name of object)
 * 
 * @author stehlik
 * @param <T> Configuration object class this storage is used for
 * 
*/
public abstract class ConfObjectRepositoryImpl<T extends ConfObject> implements ConfObjectRepository<T>{

    protected final Map<DtUid, T> mapById;

    public ConfObjectRepositoryImpl() {
        mapById = new ConcurrentHashMap<>(20);
    }
    
    public ConfObjectRepositoryImpl(int initialCacheSize) {
        mapById = new ConcurrentHashMap<>(initialCacheSize);
    }
    
    protected abstract ConfObjectLoader<T> getConfObjectLoader();
    
    protected T add(T confObject) {
        T result = mapById.putIfAbsent(confObject.getId(), confObject);
        return result;
    }

    @Override
    public T get(DtUid id) {
        T result = mapById.get(id);
        if (result == null) {
            load(id);
            result = mapById.get(id);
        }
        return result;
    }

    @Override
    public T getExisting(DtUid id) {
        return mapById.get(id);
    }

    @Override
    public void load(DtUid id) {
        if (!mapById.containsKey(id)){
            this.add(this.getConfObjectLoader().load(id));
        }
    }
    
}
