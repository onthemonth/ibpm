package com.brightd.service.impl.leave;

import com.brightd.domain.bpm.leave.LeaveAppTaskDto;
import com.brightd.service.leave.ILeaveAppService;
import org.jbpm.process.audit.AuditLogService;
import org.jbpm.process.audit.VariableInstanceLog;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskData;
import org.kie.api.task.model.TaskSummary;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengyong on 16/12/21.
 */
public class LeaveAppServiceImpl implements ILeaveAppService {

    @Resource
    private AuditLogService auditLogService;

    @Resource
    private KieSession ksession;

    @Resource
    private TaskService taskService;

    @Override
    public boolean addTask(String userId,LeaveAppTaskDto leaveTask) {
        // start a new process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LeaveVariableConstants.EMPLOYEE, userId);
        params.put(LeaveVariableConstants.REASON, leaveTask.getReason());
        params.put(LeaveVariableConstants.HOURS,leaveTask.getHours());

        ksession.startProcess(LeaveVariableConstants.LEAVE_PROCESS_ID,params);

        return true;
    }

    @Override
    public boolean apply(String userId,Long taskId) {
        return completeTask(userId,taskId,null);
    }

    @Override
    public boolean approveTask(String userId,Long taskId) {
        if((!"marry".equals(userId)) || (!"john".equals(userId))){
            return false;
        }
        taskService.claim(taskId, userId);
        Map<String, Object> params = new HashMap<String, Object>();
        if("marry".equals(userId)){
            params.put(LeaveVariableConstants.R_MANAGER,"approve");
        }else if("john".equals(userId)){
            params.put(LeaveVariableConstants.R_BOSS,"approve");
        }
        return completeTask(userId,taskId,params);
    }

    private boolean completeTask(String userId,Long taskId, Map map){
        Task task = taskService.getTaskById(taskId);
        System.out.println(userId +" executing task " + task.getName() + "(" + task.getId() + ": " + task.getDescription() + ")");
        taskService.start(task.getId(), userId);
        taskService.complete(task.getId(), userId, map);
        return true;
    }

    @Override
    public List<LeaveAppTaskDto> listTasksByUserId(String userId) {

        List<LeaveAppTaskDto> list = new ArrayList();

        List<TaskSummary> tasSummarykList = taskService.getTasksAssignedAsPotentialOwner(userId, "en-UK");

        for(TaskSummary taskSummary : tasSummarykList){

            LeaveAppTaskDto leaveAppTaskDto = new LeaveAppTaskDto();

            long taskId = taskSummary.getId();
            long piId = taskSummary.getProcessInstanceId();
            List<VariableInstanceLog>  variableInstanceLogs = (List<VariableInstanceLog>) auditLogService.findVariableInstances(piId);
            Task task = taskService.getTaskById(taskId);
            for(VariableInstanceLog vil : variableInstanceLogs){
                if(LeaveVariableConstants.EMPLOYEE.equals(vil.getVariableId())){
                    leaveAppTaskDto.setEmployee(vil.getValue());
                }else if(LeaveVariableConstants.HOURS.equals(vil.getVariableId())){
                    leaveAppTaskDto.setHours(Integer.parseInt(vil.getValue()));
                }else if(LeaveVariableConstants.REASON.equals(vil.getVariableId())){
                    leaveAppTaskDto.setReason(vil.getValue());
                }
                leaveAppTaskDto.setCreateTime(vil.getDate().toString());
                leaveAppTaskDto.setTaskId(String.valueOf(taskId));
            }
            list.add(leaveAppTaskDto);
        }
        return list;
    }
}
