package com.brightd.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pengyong on 16/11/25.
 */
public class App {
    public static void main(String[] args)throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring-service.xml"});
        context.start();
        System.out.println("Started...");
        System.in.read();//按任意键退出 }
    }
}
