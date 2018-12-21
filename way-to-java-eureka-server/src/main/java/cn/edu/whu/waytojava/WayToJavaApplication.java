package cn.edu.whu.waytojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Jimmy
 * @version 2018-12-21
 * 服务注册者
 */
@SpringBootApplication
@EnableEurekaServer
public class WayToJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WayToJavaApplication.class, args);
    }

}

