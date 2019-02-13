package com.provys.common.exception;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface ProvysException {

    /**
     * @return internal name of exception, used to matching against errors in PROVYS database
     */
    @Nonnull
    String getNameNm();
    @Nullable
    String getMessage();
    @Nullable
    Throwable getCause();
    @Nonnull
    Map<String, String> getParams();
}
