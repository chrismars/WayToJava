package cn.edu.whu.waytojavaserviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jimmy
 * @version 2018-12-21
 * 服务消费者
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class WayToJavaServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayToJavaServiceRibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

