/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.loader;

import com.provys.common.annotation.ProvysAttr;
import com.provys.common.annotation.ProvysEntity;
import com.provys.common.annotation.ProvysKey;
import com.provys.common.confobj.ConfObject;
import com.provys.common.confobj.ConfObjectLoader;
import com.provys.common.datatypes.Dt;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.iface.ExecutorFactory;
import com.provys.provysdb.iface.MapQueryExecutor;
import com.provys.sqlbuilder.catbuilder.CatSelectBuilder;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import com.provys.sqlbuilder.catbuilder.CatlBuilderFactory;
import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.catmanager.CatBuilderManager;

/**
 * Loader implementation that uses ProvysDb as data source.
 * Class T should use Provys annotations as defined in common annotations.
 * Executor factory is injected - it is user's responsibility to package local
 * or client implementation of provysdb.
 * 
 * @author stehlik
 * @param <T> class this loader is used to load
 */
public class ConfObjectLoaderDB<T extends ConfObject>
        implements ConfObjectLoader<T> {
    
    /**
     * Factory used to retrieve executors running queries against database
     */
    @Inject
    protected ExecutorFactory executorFactory;
    
    /**
     * Manager used to retrieve entities and attributes referenced in
     * annotations.
     */
    @Inject
    protected CatBuilderManager entityManager;
    
    /**
     * Enables building of SELECT statement to retrieve data from database.
     */
    @Inject
    protected CatlBuilderFactory sqlBuilderFactory;
    
    final private Class<T> confObjectClass;
    private SqlCall idCall;
    final private Map<String, Field> confObjectFields = new HashMap<>(10);
    
    public ConfObjectLoaderDB(Class<T> confObjectClass) {
        this.confObjectClass = confObjectClass;
        // find entity corresponding to T
        ProvysEntity entityAnnotation = 
                this.confObjectClass.getAnnotation(ProvysEntity.class);
        if (entityAnnotation == null) {
            throw new EntityAnnotationMissingException(confObjectClass);
        }
        CatBuilderEntity entity = entityManager.getEntityRepository().getByNm(
                new DtNameNm(entityAnnotation.value()));
        CatSelectBuilder selectBuilder = sqlBuilderFactory.getSelectBuilder(
                entity);
        // and find attributes and their corresponding fields
        entityManager.getAttrRepository().loadByEntityId(entity.getId());
        Class<?> currentClass = confObjectClass;
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                ProvysAttr attrAnnotation = field.getAnnotation(
                        ProvysAttr.class);
                if (attrAnnotation != null) {
                    // add field to properties to be set
                    CatBuilderAttr attr = entity.getAttrByNm(
                            new DtNameNm(attrAnnotation.value()));
                    selectBuilder.addColumn(attr);
                    confObjectFields.put(attr.getNm().toString(), field);
                }
                ProvysKey keyAnnotation = field.getAnnotation(ProvysKey.class);
                if (keyAnnotation != null) {
                    // set key
                }
            }
            currentClass = currentClass.getSuperclass();
        }
    }

    protected T createObject(Map<String, Dt> data) {
        T object;
        // create new instance
        try {
            object = confObjectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new InstantiationFailedException(confObjectClass, ex);
        }
        // and set all relevant fields
        data.forEach((name, value) -> {
            Field field = confObjectFields.get(name);
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new SetFieldValueFailedException(confObjectClass, ex);
            }
            field.setAccessible(accessible);
        });
        return object;
    }

    /**
     * We need synchronisation as otherwise other thread might override id bind.
     * Concurrency is suboptimal as in fact, we would only need lock until
     * variable is bound, not for the whole execution of query. But DBLoader
     * should not be used that often, as data should remain cached, thus who
     * cares...
     * If it is ever used for normal (not kept forever) data, it will have to be
     * modified - probably SqlCall shall be cloned before binding value, as
     * cloning SqlCall will be cheaper than blocking concurrent execution.
     * 
     * @param id is PROVYS Uid of object to be loaded
     * @return result of query, executed against database
     */
    private synchronized List<Map<String, Dt>> loadData(DtUid id) {
        idCall.setValue("id", id);
        MapQueryExecutor executor = executorFactory.getMapQueryExecutor(idCall);
        return executor.executeQuery();
    }

    @Override
    public T load(DtUid id) {
        List<Map<String, Dt>> queryResult = loadData(id);
        if (queryResult.isEmpty()) {
            throw new ConfObjectNotFoundUsingIdException(confObjectClass, id);
        } else if (queryResult.size() > 1) {
            throw new ConfObjectNotUniqueUsingIdException(confObjectClass, id);
        }
        return createObject(queryResult.get(0));
    }
    
    /**
     * Exception raised when ProvysEntity annotation is not found on class T.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class EntityAnnotationMissingException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        EntityAnnotationMissingException(Class<?> confObjectClass) {
            super("ProvysEntity annotation not found, class "
                    + confObjectClass.getSimpleName());
        }
    }
    
    /**
     * Exception raised when instantiation fails.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class InstantiationFailedException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        InstantiationFailedException(Class<?> confObjectClass
                , Throwable cause) {
            super("Instantiation of configuration object failed, class "
                    + confObjectClass.getSimpleName(), cause);
        }
    }
    
    /**
     * Exception raised when setting field value via reflection fails.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class SetFieldValueFailedException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        SetFieldValueFailedException(Class<?> confObjectClass
                , Throwable cause) {
            super("Setting field value failed in class "
                    + confObjectClass.getSimpleName(), cause);
        }
    }
    
}
