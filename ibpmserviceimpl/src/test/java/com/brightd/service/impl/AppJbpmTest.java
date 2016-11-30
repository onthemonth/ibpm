package com.brightd.service.impl;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.jbpm.services.api.model.DeployedUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.internal.runtime.conf.RuntimeStrategy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * without dubbo
     * @throws Exception
     */
    @Test
    public void testJbpmService() throws Exception {
        Assert.assertNotNull(deploymentService);
        Assert.assertNotNull(runtimeDataService);
        Assert.assertNotNull(processService);
        Assert.assertNotNull(userTaskService);
        KModuleDeploymentUnit deploymentUnit = new KModuleDeploymentUnit("org.jbpm", "customer-relationships", "1.0");
        //DeploymentUnit deploymentUnit = new KModuleDeploymentUnit("com.leya", "ly-demo", "0.0.4-SNAPSHOT");
        //DeploymentUnit deploymentUnit = new DeployedUnitImpl();
        // deploy
        deploymentUnit.setStrategy(RuntimeStrategy.PER_REQUEST);
        deploymentService.deploy(deploymentUnit);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customer_phone", "4444-55555");
        //long processInstance = processService.startProcess("CustomersRelationship.customers", "C001");
        long processInstance = processService.startProcess(deploymentUnit.getIdentifier(),"CustomersRelationship.customers",params);
    }
} 
