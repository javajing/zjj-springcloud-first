package com.wh.zjj.api.response;

/**
 * @author zjj
 */
public class RestException extends RuntimeException {
    private String code;

    public RestException(String code, String message) {
        super(message);
        this.code = code;
    }

    public RestException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public RestException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }
    
    public RestException(RestExceptionEnum e) {
        super(e.getMessage());
        this.code = e.getCode();
    }

    public String getCode() {
        return this.code;
    }

}
