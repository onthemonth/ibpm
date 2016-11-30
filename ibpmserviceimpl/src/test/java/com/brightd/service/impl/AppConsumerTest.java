package com.brightd.service.impl; 

import com.brightd.service.HelloBPMService;
import com.brightd.service.HelloService;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Copyright (c)  by www.leya920.com
 * All right reserved.
 * Create Date: //TODO need edit
 * Create Author: //TODO need edit
 * Last version:  1.0
 * Last Update Date: //TODO need edit
 * Last Update Log: //TODO need edit
 * Comment: App Tester.
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring-service-consumer.xml" })
public class AppConsumerTest {

    @Resource
    private HelloService helloService;

    @Resource
    private HelloBPMService helloBPMService;



    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

	/**
	*
    * Method: helloService test
    *
    */
    @Test
    public void testRemoteHello() throws Exception {
        String str = helloService.sayHello("Jessica");
        Assert.assertEquals("Hello Jessica",str);
        String str1 = helloBPMService.sayHello("");
        Assert.assertEquals("Hello BPM ",str1);
    }
} 
