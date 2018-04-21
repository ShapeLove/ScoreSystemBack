package com.shape.domain;

/**
 * Created by HXC on 2018/3/26.
 */
public class JsonResult<T> {
    private boolean success;
    private String message;
    private T data;

    public static JsonResult falseRuesult() {
        return new JsonResult(false, "系统繁忙请稍后重试!");
    }

    public JsonResult() {}

    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public JsonResult(T data) {
        this.success = true;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
