package com.wh.zjj.common.result.exception;

public class WebException extends RuntimeException {
    private String code;

    public WebException(String code, String message) {
        super(message);
        this.code = code;
    }

    public WebException(String message) {
        super(message);
        this.code = "-1";
    }

    public WebException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public WebException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
