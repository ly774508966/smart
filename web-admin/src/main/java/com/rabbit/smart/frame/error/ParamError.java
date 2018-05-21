package com.rabbit.smart.frame.error;

public class ParamError extends RuntimeException {

    public ParamError() {
    }

    public ParamError(String message) {
        super(message);
    }
}
