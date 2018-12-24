package cn.edu.whu.waytojavaeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 服务注册器
 */
@SpringBootApplication
@EnableEurekaServer
public class WayToJavaEurekaServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(WayToJavaEurekaServerApplication.class, args);
    }

}

