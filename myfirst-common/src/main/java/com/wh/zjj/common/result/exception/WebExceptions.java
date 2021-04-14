package com.wh.zjj.common.result.exception;

public enum WebExceptions implements WebExceptionFactory {

    PARAM_ERROR("-1000", "参数异常"),
    ;

    private String code;
    private String message;

    WebExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String prefix() {
        return "ZJJ:";
    }

    public WebException build() {
        return apply(code, message);
    }
}
