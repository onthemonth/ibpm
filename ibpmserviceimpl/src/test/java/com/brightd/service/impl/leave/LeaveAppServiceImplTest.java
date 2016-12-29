package com.brightd.service.impl.leave;

import com.brightd.domain.bpm.leave.LeaveAppTaskDto;
import com.brightd.service.leave.ILeaveAppService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * LeaveAppServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 28, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/rt/spring-service-rt.xml"})
public class LeaveAppServiceImplTest {

    @Resource
    private ILeaveAppService leaveAppService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addTask(String userId, LeaveAppTaskDto leaveTask)
     */
    @Test
    public void testAddTask() throws Exception {
        LeaveAppTaskDto leaveAppTaskDto = new LeaveAppTaskDto();
        leaveAppTaskDto.setEmployee("krisv");
        leaveAppTaskDto.setHours(50);
        leaveAppTaskDto.setReason("take 50 hours off");
        leaveAppService.addTask("krisv",leaveAppTaskDto);
    }

    /**
     * Method: apply(String userId, Long taskId)
     */
    @Test
    public void testApply() throws Exception {
        leaveAppService.apply("krisv",14l);
    }

    /**
     * Method: approveTask(String userId, Long taskId)
     */
    @Test
    public void testApproveTask() throws Exception {

    }

    /**
     * Method: listTasksByUserId(String userId)
     */
    @Test
    public void testListTasksByUserId() throws Exception {
        List<LeaveAppTaskDto> leaveAppTaskList = leaveAppService.listTasksByUserId("krisv");
        System.out.println("");
    }
} 
