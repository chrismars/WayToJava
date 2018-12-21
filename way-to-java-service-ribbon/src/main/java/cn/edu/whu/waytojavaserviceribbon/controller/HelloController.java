package cn.edu.whu.waytojavaserviceribbon.controller;

import cn.edu.whu.waytojavaserviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 测试控制器
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hi")
    public String sayHello(@RequestParam String name){
        return helloService.sayHello(name);
    }
}
