package com.brightd.service.impl.leave;

import com.brightd.domain.bpm.leave.LeaveAppTaskDto;
import com.brightd.service.leave.ILeaveAppService;
import org.kie.api.runtime.manager.RuntimeManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pengyong on 16/12/21.
 */
public class LeaveAppServiceImpl implements ILeaveAppService {

    @Resource
    private RuntimeManager runtimeManager;

    @Override
    public List<LeaveAppTaskDto> listTasksByUserId(String userId) {
        return null;
    }
}
