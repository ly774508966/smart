package com.rabbit.smart.dto;

/**
 * Created by xuzhang on 2017/11/28.
 */
public class KeyValue {
    private String k;
    private String v;

    public KeyValue() {
    }

    public KeyValue(String k, String v) {
        this.k = k;
        this.v = v;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
