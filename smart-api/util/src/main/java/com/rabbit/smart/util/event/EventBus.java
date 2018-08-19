package com.rabbit.smart.util.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuzhang on 2018/4/18.
 */
public class EventBus {
    private static Map<String, List<EventHandle>> maps = new HashMap<String, List<EventHandle>>();

    public static void addEventListener(EventHandle obj, String msg) {
        synchronized (EventBus.class) {
            if (maps.get(msg) == null) {
                List<EventHandle> callBacks = new ArrayList<EventHandle>();
                maps.put(msg, callBacks);
            }
            maps.get(msg).add(obj);
        }
    }

    public static void removeEventListener(EventHandle obj, String msg) {
        synchronized (EventBus.class) {
            if (maps.get(msg) == null)
                return;
            maps.get(msg).remove(obj);
        }
    }

    public static void dispatchEvent(String msg, Object data) {
        List<EventHandle> callBacks = new ArrayList<EventHandle>();
        synchronized (EventBus.class) {
            if (maps.get(msg) == null)
                return;
            for (EventHandle callback : maps.get(msg)) {
                callBacks.add(callback);
            }
        }
        for (EventHandle callBack : callBacks) {
            callBack.processEvent(msg,data);
        }
    }
}
