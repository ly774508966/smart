package com.rabbit.smart.util.param;

public class ParamError extends RuntimeException {

    public ParamError() {
    }

    public ParamError(String message) {
        super(message);
    }
}
