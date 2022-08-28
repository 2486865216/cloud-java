package com.example.common.module;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * author ye
 * createDate 2022/8/12  20:39
 */
public class UserInfo {
    private String userId;
    private String username;
    private String avatar;
    private String email;
    private String address;
    private String userCommand;
    private Timestamp createTime;

    public UserInfo() {
    }

    public UserInfo(String userId, String username, String avatar, String email, String address, String userCommand, Timestamp createTime) {
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.address = address;
        this.userCommand = userCommand;
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserCommand() {
        return userCommand;
    }

    public void setUserCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", userCommand='" + userCommand + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
