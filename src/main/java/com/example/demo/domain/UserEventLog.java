package com.example.demo.domain;

import java.util.Date;

public class UserEventLog {

    private static final long serialVersionUID=-3951198127152024633L;

    private Integer id;

    private Integer userId;

    private String userName;

    private String realName;

    private String content;

    private Date createTime;

    public UserEventLog( Integer userId, String userName, String realName, Date createTime) {
        this.userId = userId;
        this.userName = userName;
        this.realName = realName;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
