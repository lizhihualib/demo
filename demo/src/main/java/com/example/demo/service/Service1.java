package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * @author lizhihua
 * @date 2020/9/28 - 21:39
 */
@Component
public class Service1 implements TestService{

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sayName() {
        System.out.println("service1 " + this.name);
    }
}
