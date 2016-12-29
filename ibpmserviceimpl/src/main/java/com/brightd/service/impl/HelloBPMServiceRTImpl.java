package com.brightd.service.impl;

import com.brightd.service.HelloBPMServiceRT;
import org.jbpm.process.audit.AuditLogService;
import org.jbpm.process.audit.VariableInstanceLog;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskData;
import org.kie.api.task.model.TaskSummary;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengyong on 16/12/7.
 */
public class HelloBPMServiceRTImpl implements HelloBPMServiceRT{

    @Resource
    private RuntimeManager runtimeManager;

    @Resource
    private RuntimeEngine runtimeEngine;

    @Resource
    private AuditLogService auditLogService;

    @Resource
    private KieSession ksession;

    @Resource
    private TaskService taskService;

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

    public String sayHelloKml(String str) {

        RuntimeEngine runtimeEngine = runtimeManager.getRuntimeEngine(null);

        TaskService taskService = runtimeEngine.getTaskService();

        KieSession ksession = runtimeEngine.getKieSession();

        // start a new process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employee", "krisv");
        params.put("reason", "take 5 days off");
        params.put("hours",18);

        ksession.startProcess("com.brightd.leave",params);

        return "Hello BPM KML "+str;
    }

    public String sayHelloKmlPhase2(String str) {

        RuntimeEngine runtimeEngine = runtimeManager.getRuntimeEngine(null);

        TaskService taskService = runtimeEngine.getTaskService();

        TaskSummary task1 = taskService.getTasksAssignedAsPotentialOwner("krisv", "en-UK").get(0);
        System.out.println("Krisv executing task " + task1.getName() + "(" + task1.getId() + ": " + task1.getDescription() + ")");


        Map<String, Object> params1 = new HashMap<String, Object>();
        params1.put("comment","self comment 2");
        taskService.start(task1.getId(), "krisv");
        taskService.complete(task1.getId(), "krisv", params1);

        return "Hello BPM KML Phase2"+str;
    }

    @Override
    public String sayHelloKmlLeaveV3(String str) {

        RuntimeEngine runtimeEngine = runtimeManager.getRuntimeEngine(null);

        TaskService taskService = runtimeEngine.getTaskService();

        KieSession ksession = runtimeEngine.getKieSession();

        // start a new process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employee", "krisv");
        params.put("reason", "take 9 days off");
        params.put("hours", 19);

        ksession.startProcess("com.birghtd.bpm.leave.v3.leave",params);

        return "Hello BPM KML V3"+str;
    }

    @Override
    public String sayHelloKmlLeaveV3Phase1(String str) {

        taskService.start(Long.parseLong(str), "krisv");
        taskService.complete(Long.parseLong(str), "krisv", null);

        return "";
    }

    @Override
    public String sayHelloKmlLeaveV3Phase2(String str) {

        taskService.claim(24l, "mary");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("r_manager","approve");
        taskService.start(24l, "mary");
        taskService.complete(24l, "mary", params);

        return "";
    }

    @Override
    public String sayHelloKmlLeaveV3Phase3(String str) {
        return null;
    }

    @Override
    public String sayHelloKmlQuery(String str) {

        //RuntimeEngine runtimeEngine = runtimeManager.getRuntimeEngine(null);

        //TaskService taskService = runtimeEngine.getTaskService();

        List<TaskSummary> taskList = taskService.getTasksAssignedAsPotentialOwner("krisv", "en-UK");


        //KieSession ksession = runtimeEngine.getKieSession();
        for(TaskSummary taskSummary : taskList){
            long taskId = taskSummary.getId();
            long piId = taskSummary.getProcessInstanceId();
            ProcessInstance pi = ksession.getProcessInstance(piId);

            List<VariableInstanceLog>  variableInstanceLogs = (List<VariableInstanceLog>) auditLogService.findVariableInstances(piId);

            Task task = taskService.getTaskById(taskId);
            TaskData taskData = task.getTaskData();
            System.out.println("");
        }

        return "Hello BPM KML Query"+str;
    }
}
