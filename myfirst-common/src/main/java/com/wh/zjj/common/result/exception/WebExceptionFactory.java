package com.wh.zjj.common.result.exception;

/**
 * @author zjj
 */
public interface WebExceptionFactory {
    String prefix();

    default WebException apply(String code, String message) {
        return new WebException(prefix() + code, message);
    }
}
