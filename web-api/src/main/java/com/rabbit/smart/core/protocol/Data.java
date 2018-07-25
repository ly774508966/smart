package com.rabbit.smart.core.protocol;

/**
 * Created by xuzhang on 2017/10/28.
 */
public class Data {
    private String tip;

    public Data() {
        this.tip = "成功";
    }

    public Data(boolean success, String description) {
        this.tip = description;
    }
    

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
