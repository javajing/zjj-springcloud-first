package com.wh.zjj;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangjingjing
 * @date 2021/4/13 20:14
 * @project springcloudfirst
 * @desc (admin)
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@EnableDiscoveryClient
public class AdminActuatorApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AdminActuatorApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    /**
     * 支持外部容器启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AdminActuatorApp.class);
    }

}
