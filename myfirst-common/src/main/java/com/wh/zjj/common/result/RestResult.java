/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wh.zjj.common.result;

import lombok.Data;

/**
 * @author zjj
 */
@Data
public class RestResult<T> {

    private String code;

    private String message;

    private T data;

    protected RestResult(T data) {
        this.code = "0";
        this.message = "success";
        this.data = data;
    }

    protected RestResult() {
        this.code = "0";
        this.message = "success";
    }

    public static <T> RestResult<T> success(T data) {
        return new RestResult<>(data);
    }

    public static <T> RestResult<T> data(T data) {
        return new RestResult<>(data);
    }

    public static RestResult success() {
        return new RestResult();
    }

    public static RestResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static RestResult error(String msg) {
        return error(500, msg);
    }

    public static RestResult error(int code, String msg) {
        RestResult r = new RestResult();
        r.setCode(code + "");
        r.setMessage(msg);
        return r;
    }

    public static RestResult error(String code, String msg) {
        RestResult r = new RestResult();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

}
