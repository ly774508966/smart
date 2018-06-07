package com.rabbit.smart.core.protocol;

public enum ResponseStatus {
    RESPONSE_SUCCESS("0000", "成功"),
    RESPONSE_INNER_ERROR("0001", "内部异常"),
    RESPONSE_ERROR_PARAMS("0002", "参数错误"),
    RESPONSE_NO_LOGIN("0003", "尚未登录"),
    RESPONSE_NO_RIGHT("0004", "没有权限");

    ResponseStatus(String code, String des) {
        this.code = code;
        this.des = des;
    }

    private String code;
    private String des;

    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
