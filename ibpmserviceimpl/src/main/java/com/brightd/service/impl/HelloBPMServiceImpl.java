package com.brightd.service.impl;

import com.brightd.service.HelloBPMService;
import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.kie.services.impl.ProcessServiceImpl;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.model.DeployedUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.kie.api.KieBase;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.conf.RuntimeStrategy;
import org.kie.internal.utils.KieHelper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengyong on 16/11/29.
 */
public class HelloBPMServiceImpl implements HelloBPMService{

    public final String GROUP_ID = "com.brightd";
    public final String ARTIFACT_ID = "mykjar";
    public final String VERSION = "1.0-SNAPSHOT";

    @Resource
    private DeploymentService deploymentService;

    @Resource
    private ProcessServiceImpl processService;

    @Override
    public String sayHello(String str) {

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


        return "Hello BPM "+str;
    }
}