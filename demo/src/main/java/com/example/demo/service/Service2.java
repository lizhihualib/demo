package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * @author lizhihua
 * @date 2020/9/28 - 21:41
 */
@Component
public class Service2 implements TestService{

    String name;

    @Override
    public void sayName() {
        System.out.println("service2 " + this.name);
    }
}
