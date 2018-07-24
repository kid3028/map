package com.map.entity;

import java.util.List;

public class ResultVO<T> {
    private boolean state;

    private T data;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultVO() {
    }

    public ResultVO(boolean state, T data) {
        this.state = state;
        this.data = data;
    }
}
