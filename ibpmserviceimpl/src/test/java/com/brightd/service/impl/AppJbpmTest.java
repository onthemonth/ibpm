package com.brightd.service.impl;

import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring-jbpm-application.xml" })
public class AppJbpmTest {

    @Resource(name="deploymentService")
    private DeploymentService deploymentService;

    @Resource(name="runtimeDataService")
    private RuntimeDataService runtimeDataService;

    @Resource(name="processService")
    private ProcessService processService;

    @Resource(name="userTaskService")
    private UserTaskService userTaskService;

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 


    @Test
    public void testJbpmService() throws Exception {
        Assert.assertNotNull(deploymentService);
        Assert.assertNotNull(runtimeDataService);
        Assert.assertNotNull(processService);
        Assert.assertNotNull(userTaskService);
    }
} 
