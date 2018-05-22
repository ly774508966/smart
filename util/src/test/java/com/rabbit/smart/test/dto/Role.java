package com.rabbit.smart.test.dto;

/**
 * Created by xuzhang on 2018/5/22.
 */
public class Role {
    public Role() {
    }

    private String id;
    private String name;


    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
