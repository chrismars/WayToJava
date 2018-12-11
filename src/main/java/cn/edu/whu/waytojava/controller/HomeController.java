package cn.edu.whu.waytojava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Jimmy
 * @version 2018/12/11
 * 主页控制器
 */

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(method = GET)
    public String home(){
        return "index";
    }
}
