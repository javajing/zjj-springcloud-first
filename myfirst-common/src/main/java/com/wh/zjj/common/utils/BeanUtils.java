package com.wh.zjj.common.utils;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author weiqu
 * @Description
 * @Date 2019/11/6
 *
 */
@Slf4j
public class BeanUtils {

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
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * list 对象 转 list对象
     *
     * @param t
     * @param l
     * @param <T>
     * @return
     */
    public static <T> List<T> trans(Class<T> t, List l) {
        List<T> res = new ArrayList<>();
        l.forEach(o -> {
            T dist = null;
            try {
                dist = t.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(o, dist);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            res.add(dist);
        });
        return res;
    }
}
