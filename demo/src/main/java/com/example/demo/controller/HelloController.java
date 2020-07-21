package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by 24345 on 2020/6/17.
 */
@Controller
@RequestMapping("/Hello")
@ResponseBody
public class HelloController {
    @Autowired
    UserMapper userInterface;
    @RequestMapping("/SayHello")
    public Object sayHello(){

        HashMap<String, String> map = new HashMap<>();
        map.put("1","lizhihua");
        map.put("2","lizhihua2");
        User user = userInterface.getOne(1);
        System.out.println(user);
        return map;
    }
}
