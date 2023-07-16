package com.ardadev.infrastructure.driven_adapter.api.connection.error;

public class StatusException extends RuntimeException{

    private int statusCode;

    public StatusException(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return "ERROR: Status code had a problem [ " + this.statusCode + " ]";
    }
}
