package com.rabbit.smart.util.event;

/**
 * Created by xuzhang on 2018/4/18.
 */
public interface EventHandle {

    void processEvent(String msg, Object data);
}
