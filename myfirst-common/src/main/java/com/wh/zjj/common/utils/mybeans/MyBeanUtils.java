package com.wh.zjj.common.utils.mybeans;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author zhangjingjing
 * @time 2020/8/17 11:37
 * @project help-center
 * @desc (复杂数据拷贝器)
 */
public class MyBeanUtils extends BeanUtils {

    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        if (!CollectionUtils.isEmpty(sources)) {
            for (S source : sources) {
                T t = target.get();
                copyProperties(source, t);
                list.add(t);
                if (callBack != null) {
                    // 回调
                    callBack.callBack(source, t);
                }
            }
        }
        return list;
    }

    public static <S, D> D copySourceToTarget(S source, Class<D> target) {
        /*Type superClass = target.getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];*/
        return JSON.parseObject(JSON.toJSONString(source), target);
    }

}
