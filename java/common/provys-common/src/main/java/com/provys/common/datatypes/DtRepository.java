/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Registers all Dt classes and allows lookups by simple name, Provys domain or
 * SQL Type.
 * All classes implementing Dt interface should be registered here. ClassGraph
 * or similar library can be used in future, but at the time, it is not deemed
 * necessary as static mapping is just fine.
 * 
 * @author stehlik
 */
public class DtRepository {
    static Map<String, String> nameBySimpleName;
    static Map<String, Integer> sqlTypeBySimpleName;
    static Map<String, Integer> sqlSizeBySimpleName;
    static Map<Integer, String> simpleNameBySqlType;

    private static void addSqlType(Class<? extends Dt> dtClass) {
        final Method getSqlType;
        try {
            getSqlType = dtClass.getMethod("getSqlType");
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new GetSqlTypeNotFoundException(dtClass, ex);
        }
        try {
            sqlTypeBySimpleName.put(dtClass.getSimpleName()
                    , (Integer) getSqlType.invoke(null));
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {
            throw new GetSqlTypeInvocationFailedException(dtClass, ex);
        }
    }

    private static void addSqlSize(Class<? extends Dt> dtClass) {
        Method getSqlSize;
        try {
            getSqlSize = dtClass.getMethod("getSqlSize");
        } catch (NoSuchMethodException ex) {
            getSqlSize = null;
        }
        if (getSqlSize != null) {
            try {
                sqlTypeBySimpleName.put(dtClass.getSimpleName(),
                         (Integer) getSqlSize.invoke(null));
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
                throw new GetSqlSizeInvocationFailedException(dtClass, ex);
            }
        } else {
            sqlSizeBySimpleName.put(dtClass.getSimpleName(), -1);
        }
    }

    private static void addForSqlType(Class<? extends Dt> dtClass) {
        Method getDefaultForSqlTypes;
        try {
            getDefaultForSqlTypes = dtClass.getMethod("getDefaultForSqlTypes");
        } catch (NoSuchMethodException ex) {
            getDefaultForSqlTypes = null;
        }
        if (getDefaultForSqlTypes != null) {
            try {
                Optional<List<Integer>> defaultFor = (Optional<List<Integer>>)
                        getDefaultForSqlTypes.invoke(null);
                if (defaultFor.isPresent()) {
                    defaultFor.get().forEach((sqlType) -> {
                        if (simpleNameBySqlType.containsKey(sqlType)) {
                            throw new DuplicateDtDefaultTypeException(dtClass
                                    , sqlType);
                        }
                        simpleNameBySqlType.put(sqlType, dtClass.getSimpleName());
                    });
                }
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
                throw new GetDefaultForSqlTypesInvocationFailedException(dtClass, ex);
            }
        }
    }

    private static void addClass(Class<? extends Dt> dtClass) {
        nameBySimpleName.put(dtClass.getSimpleName(), dtClass.getName());
        addSqlType(dtClass);
        addSqlSize(dtClass);
        addForSqlType(dtClass);
    }
    
    /**
     * Retrieve class name for specified simple name.
     * 
     * @param simpleName is simple name of class (Dt implementation)
     * @return full name of java class
     */
    public static String getName(String simpleName) {
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
        final Integer sqlType = sqlTypeBySimpleName.get(simpleName);
        if (sqlType == null) {
            throw new UnknownDtTypeException(simpleName);
        }
        return sqlType;
    }

    /**
     * Retrieve size that should be defined for SQL column.
     * Size is number of characters
     * 
     * @param simpleName is simple name of class (Dt implementation)
     * @return number of characters, -1 if not defined
     */
    public static int getSqlSize(String simpleName) {
        final Integer sqlSize = sqlSizeBySimpleName.get(simpleName);
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
        final String simpleName = simpleNameBySqlType.get(sqlType);
        if (simpleName == null) {
            throw new SqlTypeNotMappedException(sqlType);
        }
        return simpleName;
    }

    static {
        nameBySimpleName = new HashMap<>(10);
        sqlTypeBySimpleName = new HashMap<>(10);
        sqlSizeBySimpleName = new HashMap<>(10);
        simpleNameBySqlType = new HashMap<>(10);
        addClass(DtBoolean.class);
    }

    /**
     * Exception raised when method getSqlType is not found in one of Dt
     * implementation classes.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class GetSqlTypeNotFoundException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        GetSqlTypeNotFoundException(Class<? extends Dt> dtClass
                , Throwable cause) {
            super("Method getSqlType not found in class "+dtClass.getName()
                    , cause);
        }
    }

    /**
     * Exception raised when invocation of method getSqlType or retype of result
     * to Integer fails.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class GetSqlTypeInvocationFailedException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        GetSqlTypeInvocationFailedException(Class<? extends Dt> dtClass
                , Throwable cause) {
            super("Method getSqlType invocation failed for class "
                    +dtClass.getName(), cause);
        }
    }

    /**
     * Exception raised when invocation of method getSqlSize or retype of result
     * to Integer fails.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class GetSqlSizeInvocationFailedException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        GetSqlSizeInvocationFailedException(Class<? extends Dt> dtClass
                , Throwable cause) {
            super("Method getSqlSize invocation failed for class "
                    +dtClass.getName(), cause);
        }
    }

    /**
     * Exception raised when invocation of method getDefaultForSqlTypes or
     * retype of result to Optional(List(Integer)) fails.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class GetDefaultForSqlTypesInvocationFailedException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        GetDefaultForSqlTypesInvocationFailedException(Class<? extends Dt> dtClass
                , Throwable cause) {
            super("Method getDefaultForSqlTypes invocation failed for class "
                    +dtClass.getName(), cause);
        }
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

}
