package cn.edu.whu.waytojavaservicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Jimmy
 * @version 2018-12-21
 * 路由转发和过滤器
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class WayToJavaServiceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayToJavaServiceZuulApplication.class, args);
    }

}

