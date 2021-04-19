package com.wh.zjj;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author zhangjingjing
 * @date 2021/4/13 20:14
 * @project springcloudfirst
 * @desc (dashboard)
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableTurbine
public class HystrixTurbineApp extends SpringBootServletInitializer {

    /**
     * 访问主页面地址: http://localhost:9005/turbine.stream?cluster=default
     * Hystrix-dashboard上监控聚合视图：http://localhost:9005/hystrix.stream然后输入上面url进行集群监控
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HystrixTurbineApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    /**
     * 支持外部容器启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HystrixTurbineApp.class);
    }

}
