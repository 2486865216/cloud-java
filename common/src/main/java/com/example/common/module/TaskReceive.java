package com.example.common.module;

import java.sql.Timestamp;

/**
 * author ye
 * createDate 2022/8/17  21:38
 */
public class TaskReceive {
    private String taskId;
    private String taskName;
    private Integer isShow;
    private String taskCommand;
    private String createUserId;
    private Integer loveCount;
    private String endTime;

    public TaskReceive() {
    }

    public TaskReceive(String taskId, String taskName, Integer isShow, String taskCommand, String createUserId, Integer loveCount, String endTime) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.isShow = isShow;
        this.taskCommand = taskCommand;
        this.createUserId = createUserId;
        this.loveCount = loveCount;
        this.endTime = endTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getTaskCommand() {
        return taskCommand;
    }

    public void setTaskCommand(String taskCommand) {
        this.taskCommand = taskCommand;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TaskReceive{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", isShow=" + isShow +
                ", taskCommand='" + taskCommand + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", loveCount=" + loveCount +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
