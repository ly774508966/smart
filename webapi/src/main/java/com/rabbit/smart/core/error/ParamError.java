package com.rabbit.smart.core.error;

public class ParamError extends RuntimeException {

    public ParamError() {
    }

    public ParamError(String message) {
        super(message);
    }
}
