package com.cgc.dto;

/**
 * 所有ajax的返回信息
 * @param <T>
 */
public class SeckillResult<T> {
    private boolean success;
    private String error;
    private T data;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillResult(boolean success, String error, T data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }
}
