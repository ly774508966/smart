package com.rabbit.smart.test.service;

import com.rabbit.smart.test.dto.Sick;
import com.rabbit.smart.util.event.EventHandle;

/**
 * Created by xuzhang on 2018/5/23.
 */
public class KouQiangServcie implements EventHandle {
    @Override
    public void processEvent(String msg, Object data) {
        Sick sick = (Sick) data;
        System.out.println(sick.getName() + "->口腔科");
    }
}
