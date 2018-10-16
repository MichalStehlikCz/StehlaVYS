/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class DtRepository {
    private final static Map<String, String> nameBySimpleName = new HashMap<>(10);
    private final static Map<String, Integer> sqlTypeBySimpleName = new HashMap<>(10);
    private final static Map<String, Optional<Integer>> sqlDefaultSizeBySimpleName
            = new HashMap<>(10);
    private final static Map<String, Optional<Integer>> sqlSizeBySimpleName
            = new HashMap<>(10);
    private final static Map<Integer, String> simpleNameBySqlType = new HashMap<>(10);
    private static boolean initialized = false;

    /**
     * Register Dt descendant in repository.
     * 
     * @param dtClass Dt impleemntor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param defaultSize is default size for items of this type
     * @param size is size for items of this type (when size is fixed)
     * @param defForSqlType is list of sql types that can be converted to value
     * of this type
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType
            , Optional<Integer> defaultSize, Optional<Integer> size
            , List<Integer> defForSqlType) {
        nameBySimpleName.put(dtClass.getSimpleName()
                , dtClass.getCanonicalName());
        sqlTypeBySimpleName.put(dtClass.getSimpleName(), sqlType);
        sqlDefaultSizeBySimpleName.put(dtClass.getSimpleName(), defaultSize);
        sqlSizeBySimpleName.put(dtClass.getSimpleName(), size);
        defForSqlType.forEach((defaultForSqlType) -> {
            String oldMapping = simpleNameBySqlType.put(defaultForSqlType
                    , dtClass.getSimpleName());
            if (oldMapping != null) {
                if (!oldMapping.equals(dtClass.getSimpleName())) {
                    throw new DuplicateDtDefaultTypeException(dtClass
                        , defaultForSqlType);
                }
            }
        });
    }
    
    /**
     * Register Dt descendant in repository.
     * 
     * @param dtClass Dt impleemntor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param defaultSize is default size for items of this type
     * @param defForSqlType is list of sql types that can be converted to value
     * of this type
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType
            , Optional<Integer> defaultSize
            , List<Integer> defForSqlType) {
        registerDtType(dtClass, sqlType, defaultSize, Optional.empty()
                , defForSqlType);
    }
    
    /**
     * Register Dt descendant in repository.Variant with no default SQL types
     * 
     * @param dtClass Dt implementor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param defSize is default size for items of this type
     * @param size is size for items of this type (when size is fixed)
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType
            , Optional<Integer> defSize, Optional<Integer> size) {
        registerDtType(dtClass, sqlType, defSize, size
                , new ArrayList<>(1));
    }
    
    /**
     * Register Dt descendant in repository. Variant with no default SQL types
     * 
     * @param dtClass Dt impleemntor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param defSize is default size for items of this type
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType
            , Optional<Integer> defSize) {
        registerDtType(dtClass, sqlType, defSize, Optional.empty()
                , new ArrayList<>(1));
    }
    
    /**
     * Retrieve class name for specified simple name.
     * 
     * @param simpleName is simple name of class (Dt implementation)
     * @return full name of java class
     */
    public static String getName(String simpleName) {
        if (!initialized) {
            initialize();
        }
        final String name = nameBySimpleName.get(simpleName);
        if (name == null) {
            throw new UnknownDtTypeException(simpleName);
        }
        return name;
    }

    /**
     * Retrieve class for specified simple name.
     * 
     * @param simpleName is simple name of class (Dt implementation)
     * @return java class, implementing Dt
     */
    public static Class<? extends Dt> getClass(String simpleName) {
        if (!initialized) {
            initialize();
        }
        final String name = nameBySimpleName.get(simpleName);
        if (name == null) {
            throw new UnknownDtTypeException(simpleName);
        }
        try {
            return Class.forName(name).asSubclass(Dt.class);
        } catch (ClassNotFoundException ex) {
            throw new DtClassNotFoundException(name, ex);
        }
    }

    /**
     * Retrieve code of SQL Type used to supply variables of given type.
     * SQL type as defined in java.sql.Types
     * 
     * @param simpleName is simple name of class (Dt implementation)
     * @return code of sql type used to communicate values of this type to DB
     */
    public static int getSqlType(String simpleName) {
        if (!initialized) {
            initialize();
        }
        final Integer sqlType = sqlTypeBySimpleName.get(simpleName);
        if (sqlType == null) {
            throw new UnknownDtTypeException(simpleName);
        }
        return sqlType;
    }

    /**
     * Retrieve default size that should be defined for SQL column.
     * Size is number of characters
     * 
     * @param simpleName is simple name of class (Dt implementation)
     * @return number of characters
     */
    public static Optional<Integer> getDefaultSqlSize(String simpleName) {
        if (!initialized) {
            initialize();
        }
        final Optional<Integer> sqlSize = sqlSizeBySimpleName.get(simpleName);
        if (sqlSize == null) {
            throw new UnknownDtTypeException(simpleName);
        }
        return sqlSize;
    }

    /**
     * Retrieve fixed size that should be defined for SQL column.
     * Size is number of characters
     * 
     * @param simpleName is simple name of class (Dt implementation)
     * @return number of characters
     */
    public static Optional<Integer> getSqlSize(String simpleName) {
        if (!initialized) {
            initialize();
        }
        final Optional<Integer> sqlSize = sqlSizeBySimpleName.get(simpleName);
        if (sqlSize == null) {
            throw new UnknownDtTypeException(simpleName);
        }
        return sqlSize;
    }

    /**
     * Retrieve default Dt class to handle values of given SQL type.
     * 
     * @param sqlType is value corresponding to SQL type as defined in
     * java.sql.Types
     * @return simple name of DT class that should handle given type by default
     */
    public static String getDtBySqlType(int sqlType) {
        if (!initialized) {
            initialize();
        }
        final String simpleName = simpleNameBySqlType.get(sqlType);
        if (simpleName == null) {
            throw new SqlTypeNotMappedException(sqlType);
        }
        return simpleName;
    }

    static private void initialize() {
        DtBoolean.register();
        DtInteger.register();
        DtName.register();
        DtNameNm.register();
        DtNumber.register();
        DtUid.register();
        DtVarchar.register();
        initialized = true;
    }

    /**
     * Raised when multiple default mappings exist for single SQL type.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class DuplicateDtDefaultTypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        DuplicateDtDefaultTypeException(Class<? extends Dt> dtClass
                , int sqlType) {
            super("Multiple default mappings for type; class "
                    + dtClass.getName() + ", type " + sqlType);
        }
    }

    /**
     * Exception raised when description is not found for supplied simple name.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnknownDtTypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnknownDtTypeException(String simpleName) {
            super("Cannot find description for class " + simpleName);
        }
    }

    /**
     * Class was not found using name.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class DtClassNotFoundException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        DtClassNotFoundException(String name, Throwable cause) {
            super("Class " + name + "not found", cause);
        }
    }

    /**
     * No Dt class for supplied SQL type.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class SqlTypeNotMappedException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        SqlTypeNotMappedException(int sqlType) {
            super("No Dt class for supplied SQL type " + sqlType);
        }
    }

    private DtRepository() {
    }

}
