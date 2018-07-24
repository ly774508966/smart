package com.rabbit.smart.core.protocol;

public class Response<E> {
    private String code;
    private String desc;
    private E data;

    public Response() {
        this.desc = ResponseStatus.RESPONSE_SUCCESS.getCode();
        this.code = ResponseStatus.RESPONSE_SUCCESS.getDes();
    }

    public Response(E data) {
        this.desc = ResponseStatus.RESPONSE_SUCCESS.getCode();
        this.code = ResponseStatus.RESPONSE_SUCCESS.getDes();
        this.data = data;
    }

    public Response(ResponseStatus responseStatus) {
        this.desc = responseStatus.getCode();
        this.code = responseStatus.getDes();
    }

    public Response(ResponseStatus responseStatus, E data) {
        this.desc = responseStatus.getCode();
        this.code = responseStatus.getDes();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
