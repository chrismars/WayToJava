package cn.edu.whu.waytojavadiscussion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Jimmy
 * @version 2018/12/22
 * 讨论交流模块
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class WayToJavaDiscussionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayToJavaDiscussionApplication.class, args);
    }

}

