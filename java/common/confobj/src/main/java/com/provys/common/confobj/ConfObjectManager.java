/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import oracle.sql.ROWID;

/**
 *
 * @author stehlik
 * @param <T> Configuration object class this storage is used for
 * 
*/
public abstract class ConfObjectManager<T extends ConfObject>{

    protected final Map<DtUid, T> mapById;
    protected final Map<ROWID, T> mapByRowid;

    public ConfObjectManager() {
        mapById = new ConcurrentHashMap<>(20);
        mapByRowid = new ConcurrentHashMap<>(20);
    }
    
    public ConfObjectManager(int initialCacheSize) {
        mapById = new ConcurrentHashMap<>(initialCacheSize);
        mapByRowid = new ConcurrentHashMap<>(initialCacheSize);
    }
    
    protected abstract ConfObjectLoader<T> getConfObjectLoader();
    
    protected T add(ObjectWithRowid<T> confObjectWithRowid) {
        T result = mapById.putIfAbsent(confObjectWithRowid.getObject().getId()
                , confObjectWithRowid.getObject());
        if (result == null) {
            mapByRowid.put(confObjectWithRowid.getRowid()
                    , confObjectWithRowid.getObject());
        }
        return result;
    }

    public T get(DtUid id) {
        T result = mapById.get(id);
        if (result == null) {
            load(id);
            result = mapById.get(id);
        }
        return result;
    }

    public T getExisting(DtUid id) {
        return mapById.get(id);
    }

    public void load(DtUid id) {
        if (!mapById.containsKey(id)){
            ObjectWithRowid<T> confObjectWithRowid = this.getConfObjectLoader().load(id);
            this.add(confObjectWithRowid);
        }
    }
    
}
