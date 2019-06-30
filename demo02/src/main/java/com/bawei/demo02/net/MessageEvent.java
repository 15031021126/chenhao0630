package com.bawei.demo02.net;

/*
 *@Auther:陈浩
 *@Date: 2019/6/10
 *@Time:21:23
 *@Description:${DESCRIPTION}
 * */public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
