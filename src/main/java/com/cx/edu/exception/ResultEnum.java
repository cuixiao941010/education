package com.cx.edu.exception;


public enum ResultEnum {
    ERROR("500", "服务器繁忙, 请稍后再试"),
    MESSAGE_NOT_READABLE("400", "无法解析请求参数"),
    MISSING_PATH_VARIABLE("400", "请求链接缺少参数"),
    MISSING_REQUEST_PARAM("400", "缺少必需的请求参数"),
    ARGUMENT_TYPE_MISMATCH("400", "参数类别转换失败"),
    METHOD_NOT_SUPPORTED("400", "不支持该请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED("400", "不支持该请求参数类型"),
    NO_HANDLER_NOT_FOUND("404", "请求资源不存在"),
    AUTHORIZATION_ERROR("401", "缺少Header,认证失败"),
    AUTHORIZATION_FAIL("401", "token验证失败"),
    AUTHORIZATION_IS_NOTE_EXIST("401", "token不存在，请重新登录"),

    USER_IS_NOT_EXIT("10001", "用户不存在"),
    USERNAME_OR_PASSWORD_ERROR("10001", "用户名或密码错误");


    private String code;
    private String message;

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String toJson() {
        return "{\"code\":\"" + this.code + "\",\"message\":\"" + this.message + "\"}";
    }

}
