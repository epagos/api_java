package com.epagos.sdk;

public class EpagosException extends RuntimeException {
    private final Integer statusCode;
    private final String rawBody;

    public EpagosException(String message) {
        this(message, null, null, null);
    }

    public EpagosException(String message, Throwable cause) {
        this(message, cause, null, null);
    }

    public EpagosException(String message, Integer statusCode, String rawBody) {
        this(message, null, statusCode, rawBody);
    }

    public EpagosException(String message, Throwable cause, Integer statusCode, String rawBody) {
        super(message, cause);
        this.statusCode = statusCode;
        this.rawBody = rawBody;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getRawBody() {
        return rawBody;
    }
}
