package com.brightd.service.impl;

import com.brightd.service.HelloBPMServiceRT;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengyong on 16/12/7.
 */
public class HelloBPMServiceRTImpl implements HelloBPMServiceRT{

    @Resource
    private RuntimeManager runtimeManager;

    @Override
    public String sayHello(String str) {

        RuntimeEngine runtimeEngine = runtimeManager.getRuntimeEngine(null);

        TaskService taskService = runtimeEngine.getTaskService();

        KieSession ksession = runtimeEngine.getKieSession();

        // start a new process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employee", "krisv");
        params.put("reason", "Yearly performance evaluation");
        ksession.startProcess("com.sample.evaluation", params);

        // "krisv" executes his own performance evaluation
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

        runtimeManager.disposeRuntimeEngine(runtimeEngine);

        return "Hello BPM "+str;
    }
}
