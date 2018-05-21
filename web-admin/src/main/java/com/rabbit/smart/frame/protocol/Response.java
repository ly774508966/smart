package com.rabbit.smart.frame.protocol;

public class Response<E> {
    private String des;
    private String msg;
    private E data;

    public Response() {
        this.msg = ResponseStatus.RESPONSE_SUCCESS.getCode();
        this.des = ResponseStatus.RESPONSE_SUCCESS.getDes();
    }

    public Response(E data) {
        this.msg = ResponseStatus.RESPONSE_SUCCESS.getCode();
        this.des = ResponseStatus.RESPONSE_SUCCESS.getDes();
        this.data = data;
    }

    public Response(ResponseStatus responseStatus) {
        this.msg = responseStatus.getCode();
        this.des = responseStatus.getDes();
    }

    public Response(ResponseStatus responseStatus, E data) {
        this.msg = responseStatus.getCode();
        this.des = responseStatus.getDes();
        this.data = data;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
