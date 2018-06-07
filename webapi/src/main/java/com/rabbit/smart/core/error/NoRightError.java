package com.rabbit.smart.core.error;

/**
 * Created by xuzhang on 2017/11/22.
 */
public class NoRightError extends RuntimeException {

    public NoRightError() {
    }

    public NoRightError(String message) {
        super(message);
    }
}
