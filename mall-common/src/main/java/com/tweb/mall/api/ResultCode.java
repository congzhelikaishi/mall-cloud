package com.tweb.mall.api;

public enum ResultCode implements IErrorCode {
    /*
    枚举常量类型中的数据格式来自本类的形参构造器
     */
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404,"参数检验失败"),
    UNAUTHORIZED(401,"暂未授权登录或token已经过期"),
    FORBIDDEN(403,"没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
