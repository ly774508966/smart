package com.rabbit.smart.core.error;

/**
 * Created by xuzhang on 2018/5/21.
 */
public class NoLoginError extends RuntimeException  {
    public NoLoginError() {
    }

    public NoLoginError(String message) {
        super(message);
    }
}