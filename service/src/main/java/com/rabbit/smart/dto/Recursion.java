package com.rabbit.smart.dto;

import java.util.List;

public class Recursion<T> {

    public Recursion() {
    }

    public Recursion(T node) {
        this.node = node;
    }

    private T node;
    private List<Recursion<T>> subs;

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public List<Recursion<T>> getSubs() {
        return subs;
    }

    public void setSubs(List<Recursion<T>> subs) {
        this.subs = subs;
    }
}
