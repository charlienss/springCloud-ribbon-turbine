package com.stuzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//启动类加上注解@EnableZuulProxy
//它默认加上了@EnableCircuitBreaker和@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class StuZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuZuulApplication.class, args);
    }
}
