package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.entity.User;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: yzy
 * @Date: 2019/1/20 13:53
 * @Description:
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Object json() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "zhangSan");
        return map;
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object querty() {
        List<User> users = userService.queryAll();
        return users;
    }
}
