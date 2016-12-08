package com.brightd.service.impl;

import com.brightd.service.HelloRuntimeDataService;
import org.jbpm.kie.services.impl.ProcessServiceImpl;
import org.jbpm.kie.services.impl.RuntimeDataServiceImpl;
import org.jbpm.kie.services.impl.bpmn2.BPMN2DataServiceImpl;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.model.ProcessInstanceDesc;
import org.jbpm.shared.services.impl.TransactionalCommandService;
import org.kie.api.runtime.query.QueryContext;
import org.kie.api.task.TaskService;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by pengyong on 16/12/8.
 */
public class HelloRuntimeDataServiceImpl implements HelloRuntimeDataService{

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
        System.out.println(runtimeDataService);
        //Collection<ProcessInstanceDesc> instances = runtimeDataService.getProcessInstances(new QueryContext());
        Collection definitions = runtimeDataService.getProcesses(new QueryContext());
        return "Hello BPM data "+str;
    }
}
