package com.brightd.service.impl;

import com.brightd.service.HelloBPMServiceRT;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * HelloBPMServiceRTImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 7, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/rt/spring-service-rt.xml"})
public class HelloBPMServiceRTImplTest {

    @Resource
    private HelloBPMServiceRT helloBPMServiceRT;

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
        Assert.assertNotNull(helloBPMServiceRT);
        Assert.assertNotNull(helloBPMServiceRT.sayHello("RT"));
    }
} 
