package com.rabbit.smart.test.service;

import com.rabbit.smart.test.dto.Sick;
import com.rabbit.smart.util.event.EventHandle;

/**
 * Created by xuzhang on 2018/5/23.
 */

public class GuKeService implements EventHandle {
    @Override
    public void processEvent(String msg, Object data) {
        Sick sick = (Sick) data;
        if (sick.getCondition().equals("产检")) {
            System.out.println(sick.getName() + "->妇产科");
        }
    }
}
