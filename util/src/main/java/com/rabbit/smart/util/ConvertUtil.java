package com.rabbit.smart.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhang on 2018/5/21.
 */
public class ConvertUtil {
    public static String[] listToArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static List<String> arrayToList(String[] array) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }
}
