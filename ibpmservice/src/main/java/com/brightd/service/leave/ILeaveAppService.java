package com.brightd.service.leave;

import com.brightd.domain.bpm.leave.LeaveAppTaskDto;

import java.util.List;

/**
 * Created by pengyong on 16/12/21.
 */
public interface ILeaveAppService {

    public boolean addTask(String userId,LeaveAppTaskDto leaveTask);
    public boolean apply(String userId,Long taskId);
    public boolean approveTask(String userId,Long taskId);
    public List<LeaveAppTaskDto> listTasksByUserId(String userId);

}
