package com.wh.zjj.api.response;

/**
 * @author zjj
 */
public enum RestExceptionEnum {

    /**
     * 异常枚举申明
     */
    SUCCESS("0000", "成功"),
    SERVER_ERROR("-1000", "查询失败"),
    CHECK_ERROR("-1001", "参数校验失败");

    private String code;
    private String message;

    RestExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
