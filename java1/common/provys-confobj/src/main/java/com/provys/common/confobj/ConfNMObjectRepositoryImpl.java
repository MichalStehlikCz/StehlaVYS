/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtNameNm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author stehlik
 * @param <T> Configuration object class this storage is used for
 * 
 */
abstract public class ConfNMObjectRepositoryImpl<T extends ConfNMObject>
        extends ConfObjectRepositoryImpl<T>
        implements ConfNMObjectRepository<T> {

    private final Map<DtNameNm, T> mapByNm;
    
    public ConfNMObjectRepositoryImpl() {
        mapByNm=new ConcurrentHashMap<>(20);
    }
    
    public ConfNMObjectRepositoryImpl(int initialCacheSize) {
        super(initialCacheSize);
        mapByNm=new ConcurrentHashMap<>(initialCacheSize);
    }
    
    @Override
    protected T add(T confObject) {
        T result = super.add(confObject);
        if (result == null){
          mapByNm.put(confObject.getNameNm(), confObject);
        }
        return result;
    }

    @Override
    abstract protected ConfNMObjectLoader<T> getConfObjectLoader();

    @Override
    public T getByNm(DtNameNm nameNm) {
        T result=mapByNm.get(nameNm);
        if (result==null){
            loadByNm(nameNm);
            result=mapByNm.get(nameNm);
        }
        return result;
    }

    @Override
    public T getExistingByNm(DtNameNm nameNm) {
        return mapByNm.get(nameNm);
    }

    @Override
    public void loadByNm(DtNameNm nameNm){
        if (!mapByNm.containsKey(nameNm)){
            this.add(getConfObjectLoader().loadByNm(nameNm));
        }
    }

}
