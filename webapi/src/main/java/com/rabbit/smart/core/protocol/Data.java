package com.rabbit.smart.core.protocol;

/**
 * Created by xuzhang on 2017/10/28.
 */
public class Data {
    private boolean success;
    private String tip;

    public Data() {
        this.success = true;
        this.tip = "成功";
    }

    public Data(boolean success, String description) {
        this.success = success;
        this.tip = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
