package com.example.common.module;

/**
 * author ye
 * createDate 2022/8/20  19:23
 */
public class Love {
    private Integer id;
    private String loveUserId;
    private String taskId;

    public Love() {
    }

    public Love(Integer id, String loveUserId, String taskId) {
        this.id = id;
        this.loveUserId = loveUserId;
        this.taskId = taskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoveUserId() {
        return loveUserId;
    }

    public void setLoveUserId(String loveUserId) {
        this.loveUserId = loveUserId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Love{" +
                "id=" + id +
                ", loveUserId='" + loveUserId + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
