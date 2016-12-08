package com.brightd.service.impl;

import com.brightd.service.HelloBPMService;
import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.kie.services.impl.ProcessServiceImpl;
import org.jbpm.kie.services.impl.RuntimeDataServiceImpl;
import org.jbpm.kie.services.impl.bpmn2.BPMN2DataServiceImpl;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.model.DeployedUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.shared.services.impl.TransactionalCommandService;
import org.kie.api.KieBase;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.conf.RuntimeStrategy;
import org.kie.internal.utils.KieHelper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengyong on 16/11/29.
 */
public class HelloBPMServiceImpl implements HelloBPMService{

    public final String GROUP_ID = "com.brightd";
    public final String ARTIFACT_ID = "km";
    public final String VERSION = "1.0-SNAPSHOT";

    @Resource
    private TaskService taskService;

    @Resource
    private TransactionalCommandService transactionCmdService;

    @Resource
    private RuntimeDataServiceImpl runtimeDataService;

    @Resource
    private BPMN2DataServiceImpl definitionService;

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

        RuntimeManager runtimeManager = deploymentService.getRuntimeManager(deploymentUnit.getIdentifier());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customer_phone", "4444-55555");
        //long processInstance = processService.startProcess("CustomersRelationship.customers", "C001");
        long processInstance = processService.startProcess(deploymentUnit.getIdentifier(),"CustomersRelationship.customers",params);

        return "Hello BPM "+str;
    }

    public String sayHelloKM(String str) {

        KModuleDeploymentUnit deploymentUnit = new KModuleDeploymentUnit("com.brightd", "km", "1.0-SNAPSHOT");
//        //deploy
        deploymentUnit.setStrategy(RuntimeStrategy.PER_REQUEST);
        deploymentService.deploy(deploymentUnit);
//
        RuntimeManager runtimeManager = deploymentService.getRuntimeManager(deploymentUnit.getIdentifier());
        RuntimeEngine runtime = runtimeManager.getRuntimeEngine(null);
//
//        // start a new process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employee", "krisv");
        params.put("reason", "Yearly performance evaluation");
        processService.startProcess(deploymentUnit.getIdentifier(),"com.sample.evaluation", params);

        taskService = runtime.getTaskService();


        TaskSummary task1 = taskService.getTasksAssignedAsPotentialOwner("krisv", "en-UK").get(0);
        System.out.println("Krisv executing task " + task1.getName() + "(" + task1.getId() + ": " + task1.getDescription() + ")");
        taskService.start(task1.getId(), "krisv");
        taskService.complete(task1.getId(), "krisv", null);

        // "john", part of the "PM" group, executes a performance evaluation
        TaskSummary task2 = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK").get(0);
        System.out.println("John executing task " + task2.getName() + "(" + task2.getId() + ": " + task2.getDescription() + ")");
        System.out.println(taskService.getTasksAssignedAsPotentialOwner("john", "en-UK").size());
        taskService.claim(task2.getId(), "john");
        taskService.start(task2.getId(), "john");
        taskService.complete(task2.getId(), "john", null);

        // "mary", part of the "HR" group, delegates a performance evaluation
        TaskSummary task3 = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK").get(0);
        System.out.println("Mary delegating task " + task3.getName() + "(" + task3.getId() + ": " + task3.getDescription() + ") to krisv");
        taskService.claim(task3.getId(), "mary");
        taskService.delegate(task3.getId(), "mary", "krisv");

        // "administrator" delegates the task back to mary
        System.out.println("Administrator delegating task back to mary");
        taskService.delegate(task3.getId(), "Administrator", "mary");

        // mary executing the task
        TaskSummary task3b = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK").get(0);
        System.out.println("Mary executing task " + task3b.getName() + "(" + task3b.getId() + ": " + task3b.getDescription() + ")");
        taskService.start(task3b.getId(), "mary");
        taskService.complete(task3b.getId(), "mary", null);

        System.out.println("Process instance completed");

        return "Hello BPM KM "+str;
    }


}
