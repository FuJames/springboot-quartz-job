package com.fqz.quartz.model;

/**
 * @author fuqianzhong
 * @date 19/1/15
 */
public enum ResultCodeEnum {
    SUCCESS(200,"请求成功"),
    SERVER_ERROR(500,"服务异常"),
    BAD_REQUEST(400,"参数错误")
    ;
    private int code;
    private String title;

    ResultCodeEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
