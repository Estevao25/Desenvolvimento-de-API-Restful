package com.residencia.dell.exceptions;

public class CustHistException extends Exception {
    private static final long serialVersionUID = 1L;

    public CustHistException() {
    }

    public CustHistException(String message) {
        super(message);
    }

    public CustHistException(Throwable cause) {
        super(cause);
    }

    public CustHistException(String message, Throwable cause) {
        super(message, cause);
    }
}
