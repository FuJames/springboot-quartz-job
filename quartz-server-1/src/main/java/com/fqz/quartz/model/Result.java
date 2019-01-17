package com.fqz.quartz.model;

/**
 * @author fuqianzhong
 * @date 19/1/15
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(ResultCodeEnum codeEnum, T data){
        this.code = codeEnum.getCode();
        this.message = codeEnum.getTitle();
        this.data = data;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
