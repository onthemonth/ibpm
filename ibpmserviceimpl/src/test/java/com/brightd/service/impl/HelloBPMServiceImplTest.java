package com.brightd.service.impl;

import com.brightd.service.HelloBPMService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * HelloBPMServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Nov 29, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-service.xml"})
public class HelloBPMServiceImplTest {

    @Resource
    private HelloBPMService helloBPMService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: sayHello(String str)
     */
    @Test
    public void testSayHello() throws Exception {
        Assert.assertNotNull(helloBPMService);
        Assert.assertNotNull(helloBPMService.sayHello(""));
    }
} 
