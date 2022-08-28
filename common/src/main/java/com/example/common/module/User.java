package com.example.common.module;

/**
 * author ye
 * createDate 2022/8/10  20:43
 */
public class User {
    private String userId;
    private String username;
    private String password;
    private Integer status;

    public User() {
    }

    public User(String userId, String username, String password, Integer status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.status = status;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
