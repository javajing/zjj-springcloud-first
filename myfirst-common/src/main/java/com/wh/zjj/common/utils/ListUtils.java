/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wh.zjj.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author duguojun
 */
public class ListUtils {


    /**
     * 过滤缓存为null数据
     *
     * @param list
     * @return
     */
    public static List<Object> filterNullObj(List<Object> list) {
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(a -> a != null).collect(Collectors.toList());
    }

    /**
     * 过滤缓存为null的数据
     *
     * @param list
     * @return
     */
    public static List<String> filterNullStr(List<String> list) {
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(a -> StrUtil.isNotBlank(a)).collect(Collectors.toList());
    }

    public static <T, P> List<T> fillterList(List<P> source, Function<P, T> c) {
        List<T> res = new ArrayList<>();
        source.forEach(o -> {
            if (c.apply(o) != null) {
                res.add(c.apply(o));
            }
        });
        return res;
    }

    public static <K, V> Map<K, V> transToMap(List<V> source, Function<V, K> c) {
        Map<K, V> res = new HashMap<>();
        source.forEach(o -> {
            res.put(c.apply(o), o);
        });
        return res;
    }

    public static List<Long> strToList(String str) {
        if (str == null || StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        return Arrays.asList(str.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    }
}
