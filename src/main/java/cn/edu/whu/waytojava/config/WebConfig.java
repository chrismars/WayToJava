package cn.edu.whu.waytojava.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author Jimmy
 * @version 2018/12/11
 * Web应用配置类
 */

@Configuration
@EnableWebMvc
@ComponentScan()
public class WebConfig implements WebMvcConfigurer {

}
