package com.provys.common.exception;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Represents internal exception. This exception can be used in internal checks, where other code should ensure
 * exception is not thrown. It should never be used for validation of external data
 */
public class InternalException extends RegularException {

    private static final String NAME_NM = "JAVA_INTERNAL_EXCEPTION";

    public InternalException(String message, Map<String, String> params, @Nullable Throwable cause) {
        super(NAME_NM, message, params, cause);
    }

    public InternalException(String nameNm, String message, Map<String, String> params) {
        super(NAME_NM, message, params);
    }

    public InternalException(String nameNm, String message, @Nullable Throwable cause) {
        super(NAME_NM, message, cause);
    }

    public InternalException(String nameNm, String message) {
        super(NAME_NM, message);
    }
}
