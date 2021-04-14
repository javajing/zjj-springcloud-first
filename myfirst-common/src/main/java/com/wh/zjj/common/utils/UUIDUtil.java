package com.wh.zjj.common.utils;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author zhangjingjing
 * @time 2020/8/31 15:57
 * @project hrs-serve
 * @desc (uuid)
 */
public class UUIDUtil {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        // 替换-字符
        String uniqueId = uuid.replace("-", "");
        return uniqueId;
    }

    public static void insertTid(){
        MDC.put("tid", getUUID());
    }

    public static void removeTid(){
        MDC.remove("tid");
    }

}
