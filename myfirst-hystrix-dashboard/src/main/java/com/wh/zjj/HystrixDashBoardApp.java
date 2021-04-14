package com.wh.zjj;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author zhangjingjing
 * @date 2021/4/13 20:14
 * @project springcloudfirst
 * @desc (dashboard)
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashBoardApp extends SpringBootServletInitializer {

    /**
     * 访问主页面地址: http://localhost:9003/hystrix
     * 监控其他地址：http://localhost:8001/hystrix.stream
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HystrixDashBoardApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    /**
     * 支持外部容器启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HystrixDashBoardApp.class);
    }

}
