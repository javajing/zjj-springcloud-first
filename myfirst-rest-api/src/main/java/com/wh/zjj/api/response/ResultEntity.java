package com.wh.zjj.api.response;


import java.io.Serializable;

/**
 * @author zjj
 */
public class ResultEntity<T> implements Serializable {

    private String code;
    private String message;
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(T data) {
        this.data = data;
        this.code = RestExceptionEnum.SUCCESS.getCode();
        this.message = RestExceptionEnum.SUCCESS.getMessage();
    }

    private ResultEntity(RestException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ResultEntity(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultEntity(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultEntity<T> badRequestError(String message) {
        return new ResultEntity<T>(RestExceptionEnum.CHECK_ERROR.getCode(), message, null);
    }

    public static <T> ResultEntity<T> badRequestError(RestExceptionEnum exceptionEnum) {
        return new ResultEntity<T>(exceptionEnum.getCode(), exceptionEnum.getMessage(), null);
    }

    /**
     * 成功信息处理
     *
     * @param data 处理结果数据
     * @param <T> 泛型
     * @return 成功信息
     */
    public static <T> ResultEntity<T> successful(T data) {
        return new ResultEntity<T>(data);
    }

    /**
     * 返回异常
     *
     * @param e Exception
     */
    public static <T> ResultEntity<T> fail(Throwable e) {
        if (e instanceof RestException) {
            return new ResultEntity<T>((RestException) e);
        }

        return new ResultEntity<T>(RestExceptionEnum.SERVER_ERROR.getCode(), e.getMessage(), null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public boolean isSuccess() {
        return RestExceptionEnum.SUCCESS.getCode().equals(code);
    }
}
