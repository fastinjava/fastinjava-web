package com.fastinjava.application.base;

import com.fastinjava.application.base.util.SpringContextHolder;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2//开启swagger
@EnableSwaggerBootstrapUI
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }

}
