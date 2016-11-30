package com.brightd.service.impl;

import com.brightd.service.HelloService;

/**
 * Created by pengyong on 16/11/25.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello "+ name;
    }
}
