package cn.edu.whu.waytojavadiscussion.controller;

import cn.edu.whu.waytojavadiscussion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 用户控制器
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    public Map<String,Object> findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        Map<String, Object> map = new HashMap<>();
        map.put("user",userService.getUsers(pageNum,pageSize).getList());
        return map;
    }
}
