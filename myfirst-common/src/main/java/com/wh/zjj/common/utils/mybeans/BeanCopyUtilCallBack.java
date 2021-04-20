package com.wh.zjj.common.utils.mybeans;

/**
 * @author zhangjingjing
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S, T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}