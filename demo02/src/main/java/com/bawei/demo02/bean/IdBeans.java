package com.bawei.demo02.bean;

/*
 *@Auther:陈浩
 *@Date: 2019/6/14
 *@Time:19:32
 *@Description:${DESCRIPTION}
 * */public class IdBeans {
     private String sessionId;
     private String userId;

    @Override
    public String toString() {
        return "IdBeans{" +
                "sessionId='" + sessionId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public IdBeans() {
    }

    public IdBeans(String sessionId, String userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }
}
