package com.wh.zjj;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author zhangjingjing
 * @date 2021/4/13 20:14
 * @project springcloudfirst
 * @desc (dashboard)
 */
@SpringBootApplication
@RefreshScope
public class ConfigBusClientApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConfigBusClientApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    /**
     * 支持外部容器启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ConfigBusClientApp.class);
    }

}
