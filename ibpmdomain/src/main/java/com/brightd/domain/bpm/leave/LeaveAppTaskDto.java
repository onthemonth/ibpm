package com.brightd.domain.bpm.leave;

import java.io.Serializable;

/**
 * Created by pengyong on 16/12/21.
 */
public class LeaveAppTaskDto implements Serializable{

    private String taskId;
    private String employee;
    private String reason;
    private String comment;
    private Integer hours;
    private String mgComment;
    private String bossComment;
    private String approvedByManager;
    private String getApprovedByBoss;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    private String createTime;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getMgComment() {
        return mgComment;
    }

    public void setMgComment(String mgComment) {
        this.mgComment = mgComment;
    }

    public String getBossComment() {
        return bossComment;
    }

    public void setBossComment(String bossComment) {
        this.bossComment = bossComment;
    }

    public String getApprovedByManager() {
        return approvedByManager;
    }

    public void setApprovedByManager(String approvedByManager) {
        this.approvedByManager = approvedByManager;
    }

    public String getGetApprovedByBoss() {
        return getApprovedByBoss;
    }

    public void setGetApprovedByBoss(String getApprovedByBoss) {
        this.getApprovedByBoss = getApprovedByBoss;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
