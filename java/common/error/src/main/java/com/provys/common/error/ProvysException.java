/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.error;

/**
 *
 * @author stehlik
 */
public class ProvysException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new PROVYS runtime exception with the specified detail
     * message and cause.
     * <p>
     * Note that the detail message associated with {@code cause} is <i>not</i>
     * automatically incorporated in this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by
     * the {@link #getMessage()} method).
     */
    public ProvysException(String message) {
        super(message);
    }

    /**
     * Constructs a new PROVYS runtime exception with the specified detail
     * message and cause.
     * <p>
     * Note that the detail message associated with {@code cause} is <i>not</i>
     * automatically incorporated in this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by
     * the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method). (A <tt>null</tt> value is permitted, and
     * indicates that the cause is nonexistent or unknown.)
     */
    public ProvysException(String message, Throwable cause) {
        super(message, cause);
    }
}
