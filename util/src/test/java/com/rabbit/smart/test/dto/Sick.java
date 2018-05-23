package com.rabbit.smart.test.dto;

/**
 * Created by xuzhang on 2018/5/23.
 */
public class Sick {
    public Sick() {
    }

    public Sick(String name, String condition) {
        this.name = name;
        this.condition = condition;
    }

    private String name;
    private String condition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
