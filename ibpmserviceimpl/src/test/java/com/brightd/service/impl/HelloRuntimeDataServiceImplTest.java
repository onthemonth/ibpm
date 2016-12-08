package com.brightd.service.impl;

import com.brightd.service.HelloRuntimeDataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * HelloRuntimeDataServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 8, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring-service.xml"})
public class HelloRuntimeDataServiceImplTest {

    @Resource
    private HelloRuntimeDataService helloRuntimeDataService;

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
        Assert.assertNotNull(helloRuntimeDataService);
        Assert.assertNotNull(helloRuntimeDataService.sayHello(""));
    }
} 
