/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;

public class DtRepository {

    private final static Map<String, String> NAMEBYSIMPLENAME = new HashMap<>(10);
    private final static Map<String, Integer> SQLTYPEBYSIMPLENAME = new HashMap<>(10);
    private final static Map<String, PrecisionValidator> PRECISIONVALIDATORS
            = new HashMap<>(10);
    private final static Map<String, ScaleValidator> SCALEVALIDATORS
            = new HashMap<>(10);
    private final static Map<String, EligibleForSqlType> SQLTYPEMAPPING
            = new HashMap<>(10);

    private static final Lock INITLOCK = new ReentrantLock();
    private static boolean initialized = false;

    /**
     * Register Dt descendant in repository.
     *
     * @param dtClass Dt implementor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param precisionValidator - function used for validation of precision for
     * given {@code Dt} class
     * @param scaleValidator - function used for validation of scale for given
     * {@code Dt} class
     * @param eligibleForSqlType is function that evaluates mapping quality of
     * given {@code Dt} class to SQL type
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType,
            PrecisionValidator precisionValidator,
            ScaleValidator scaleValidator,
            EligibleForSqlType eligibleForSqlType) {
        NAMEBYSIMPLENAME.put(dtClass.getSimpleName(),
                dtClass.getCanonicalName());
        SQLTYPEBYSIMPLENAME.put(dtClass.getSimpleName(), sqlType);
        if (precisionValidator != null) {
            PRECISIONVALIDATORS.put(dtClass.getSimpleName(), precisionValidator);
        }
        if (scaleValidator != null) {
            SCALEVALIDATORS.put(dtClass.getSimpleName(), scaleValidator);
        }
        if (eligibleForSqlType != null) {
            SQLTYPEMAPPING.put(dtClass.getSimpleName(), eligibleForSqlType);
        }
    }

    /**
     * Register Dt descendant in repository.
     *
     * @param dtClass Dt implementor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param precisionValidator - function used for validation of precision for
     * given {@code Dt} class
     * @param scaleValidator - function used for validation of scale for given
     * {@code Dt} class
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType,
            PrecisionValidator precisionValidator,
            ScaleValidator scaleValidator) {
        registerDtType(dtClass, sqlType, precisionValidator, scaleValidator,
                null);
    }

    /**
     * Register Dt descendant in repository.
     *
     * @param dtClass Dt implementor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param precisionValidator - function used for validation of precision for
     * given {@code Dt} class
     * @param eligibleForSqlType is function that evaluates mapping quality of
     * given {@code Dt} class to SQL type
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType,
            PrecisionValidator precisionValidator,
            EligibleForSqlType eligibleForSqlType) {
        registerDtType(dtClass, sqlType, precisionValidator, null,
                eligibleForSqlType);
    }

    /**
     * Register Dt descendant in repository.
     *
     * @param dtClass Dt implementor class to be registered
     * @param sqlType is java.sql.Types type values of this type are converted
     * to
     * @param precisionValidator - function used for validation of precision for
     * given {@code Dt} class
     */
    public static void registerDtType(Class<? extends Dt> dtClass, int sqlType,
            PrecisionValidator precisionValidator) {
        registerDtType(dtClass, sqlType, precisionValidator, null, null);
    }

    /**
     * Retrieve class name for specified simple name.
     *
     * @param simpleName is simple name of class (Dt implementation)
     * @return full name of java class
     */
    public static String getName(String simpleName) {
        initialize();
        final String name = NAMEBYSIMPLENAME.get(simpleName);
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
        initialize();
        final String name = NAMEBYSIMPLENAME.get(simpleName);
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
     * Retrieve code of SQL Type used to supply variables of given type. SQL
     * type as defined in java.sql.Types
     *
     * @param simpleName is simple name of class (Dt implementation)
     * @return code of sql type used to communicate values of this type to DB
     */
    public static int getSqlType(String simpleName) {
        initialize();
        final Integer sqlType = SQLTYPEBYSIMPLENAME.get(simpleName);
        if (sqlType == null) {
            throw new UnknownDtTypeException(simpleName);
        }
        return sqlType;
    }

    /**
     * Retrieve default / fixed precision that should be defined for SQL column.
     * Precision is number of characters / digits
     *
     * @param simpleName is simple name of class (Dt implementation)
     * @param precision is precision supplied on column creation
     * @return precision for column of given type
     */
    public static Optional<Integer> validatePrecision(String simpleName,
            Optional<Integer> precision) {
        initialize();
        PrecisionValidator precisionValidator
                = PRECISIONVALIDATORS.get(simpleName);
        if (precisionValidator == null) {
            // if type doesn't have validator, precision has no meaning for it
            return Optional.empty();
        }
        return precisionValidator.validatePrecision(precision);
    }

    /**
     * Retrieve default / fixed scale that should be defined for SQL column.
     * Scale is number of digits to the right of decimal point
     *
     * @param simpleName is simple name of class (Dt implementation)
     * @param scale is scale supplied on column creation
     * @return scale for column of given type
     */
    public static Optional<Short> validateScale(String simpleName,
            Optional<Short> scale) {
        initialize();
        ScaleValidator scaleValidator
                = SCALEVALIDATORS.get(simpleName);
        if (scaleValidator == null) {
            // if type doesn't have validator, scale has no meaning for it
            return Optional.empty();
        }
        return scaleValidator.validateScale(scale);
    }

    /**
     * Retrieve default {@code Dt} class to handle values of given SQL type.
     *
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return simple name of {@code Dt} class that should handle given type by
     * default
     * @throws SqlTypeNotMappedException when no sql type eligible to handle
     * supplied sql type / properties is found
     */
    public static String getDtBySqlType(int sqlType, Optional<Integer> precision,
            Optional<Short> scale, boolean isNullable, String name) {
        initialize();
        EligibilityEvaluator evaluator = new EligibilityEvaluator(sqlType,
                precision, scale, isNullable, name);
        SQLTYPEMAPPING.forEach(evaluator);
        return evaluator.getSimpleName();
    }

    static private void initialize() {
        if (initialized) {
            return;
        }
        try {
            INITLOCK.lock();
            if (!initialized) {
                DtBoolean.register();
                DtOptBoolean.register();
                DtInteger.register();
                DtOptInteger.register();
                DtName.register();
                DtOptName.register();
                DtNameNm.register();
                DtOptNameNm.register();
                DtNumber.register();
                DtOptNumber.register();
                DtUid.register();
                DtOptUid.register();
                DtVarchar.register();
                DtOptVarchar.register();
                initialized = true;
            }
        } finally {
            INITLOCK.unlock();
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
     * No Dt class eligible for supplied SQL type found.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class SqlTypeNotMappedException
            extends ProvysException {

        SqlTypeNotMappedException(int sqlType, Optional<Integer> precision,
                Optional<Short> scale, boolean isNullable, String name) {
            super("No Dt class for supplied SQL type " + sqlType + " precision "
                    + precision.toString() + " scale " + scale.toString() + " nullable "
                    + (isNullable ? "TRUE" : "FALSE") + " name " + name);
        }
    }

    /**
     * Functional interface, describing signature of function {@code Dt} type
     * should supply to allow validation of precision.
     */
    @FunctionalInterface
    @SuppressWarnings("PublicInnerClass")
    public interface PrecisionValidator {

        public Optional<Integer> validatePrecision(Optional<Integer> precision);
    }

    /**
     * Functional interface, describing signature of function {@code Dt} type
     * should supply to allow validation of scale.
     */
    @FunctionalInterface
    @SuppressWarnings("PublicInnerClass")
    public interface ScaleValidator {

        public Optional<Short> validateScale(Optional<Short> scale);
    }

    /**
     * Functional interface, describing signature of function {@code Dt} type
     * should supply to allow its ability to support given SQL type.
     */
    @FunctionalInterface
    @SuppressWarnings("PublicInnerClass")
    public interface EligibleForSqlType {

        public int isEligible(int sqlType, Optional<Integer> precision,
                Optional<Short> scale, boolean isNullable, String name);
    }

    private DtRepository() {
    }

    private static class EligibilityEvaluator
            implements BiConsumer<String, EligibleForSqlType> {

        final private int sqlType;
        final private Optional<Integer> precision;
        final private Optional<Short> scale;
        final private boolean isNullable;
        final private String name;

        private int weight = -1;
        private String simpleName;

        EligibilityEvaluator(int sqlType, Optional<Integer> precision,
                Optional<Short> scale, boolean isNullable, String name) {
            this.sqlType = sqlType;
            this.precision = precision;
            this.scale = scale;
            this.isNullable = isNullable;
            this.name = name;
        }

        @Override
        public void accept(String simpleName, EligibleForSqlType u) {
            int currentWeight = u.isEligible(sqlType, precision, scale,
                    isNullable, name);
            if (currentWeight > weight) {
                this.weight = currentWeight;
                this.simpleName = simpleName;
            }
        }

        String getSimpleName() {
            if (simpleName == null) {
                throw new SqlTypeNotMappedException(sqlType, precision, scale,
                        isNullable, name);
            }
            return simpleName;
        }

    }
}
