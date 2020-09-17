package com.example.demo.init;

import com.example.demo.ZhuJie.MyAnnotation;
import com.example.demo.netty.NettyServer;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by 24345 on 2020/8/5
 */
@Component
public class InitInfo implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Info init start!");
        // 要扫描的包
        String packageName = "com.example.demo.controller";
        //反射框架
        Reflections reflections = new Reflections(packageName);
        // 获取扫描到的标记注解的集合
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(MyAnnotation.class);
        for (Class<?> c : set) {
            // 循环获取标记的注解
            MyAnnotation annotation = c.getAnnotation(MyAnnotation.class);
            // 打印注解中的内容
            System.out.println(annotation.name());

        }
        System.out.println("Info init end!");
        OpenNetty();
    }

    public void OpenNetty(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                NettyServer.start();
            }
        });
        thread.start();
        new String("dada");
    }

}
