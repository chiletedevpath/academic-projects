package com.demo.sunat.exception;

public class ProviderException extends RuntimeException {

    private final int status;

    public ProviderException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}