package com.rabbit.smart.dto;

public class AccountLoginDto {
    public AccountLoginDto() {
    }

    public AccountLoginDto(String msg) {
        this.msg = msg;
    }

    public AccountLoginDto(String msg, String token) {
        this.msg = msg;
        this.token = token;
    }

    private String msg;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
