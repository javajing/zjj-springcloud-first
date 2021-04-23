package com.wh.zjj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigBusClient {
    /**
     * 拿到从4002微服务的bus--->GitHub-->返回来的配置信息
     */
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String port;

    /**
     * 访问地址：http://localhost:5001/config
     *  dev的端口是5001，test的端口是5002(配置指定的profile不同，端口就不一样！)
     *
     *
     * 修改git配置applicationName后,调用http://config-4002.com:4002/bus/refresh的configServer进行统一的bus下发,实现配置的动态更新
     * @return
     */
    @RequestMapping("/config")
    public String getConfig() {
        String str = "applicationName: " + applicationName + "\t eurekaServers:" + 1 + "\t port: " + port;
        System.out.println("******str: " + str);
        return "applicationName: " + applicationName + "\t eurekaServers:" + 1 + "\t port: " + port;
    }
}
