package com.wh.zjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangjingjing
 * @date 2021/4/13 20:14
 * @project springcloudfirst
 * @desc (服务提供者db2)
 */
@SpringBootApplication
@EnableConfigurationProperties
@MapperScan({"com.wh.zjj.common.*.mapper"})
@EnableTransactionManagement
@EnableAsync

@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient //服务发现
public class ProviderAppDb2 extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ProviderAppDb2.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    /**
     * 支持外部容器启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProviderAppDb2.class);
    }

}
