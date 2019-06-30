package com.bawei.demo02.bean;

/*
 *@Auther:陈浩
 *@Date: 2019/6/19
 *@Time:11:40
 *@Description:${DESCRIPTION}
 * */public class UserEntity {


    private String createTime;
    private String headPic;
    private String nickName;
    private String password;
    private String phone;
    private String sex;
    private String userId;

    @Override
    public String toString() {
        return "UserEntity{" +
                "createTime='" + createTime + '\'' +
                ", headPic='" + headPic + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserEntity(String createTime, String headPic, String nickName, String password, String phone, String sex, String userId) {
        this.createTime = createTime;
        this.headPic = headPic;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.userId = userId;
    }
}
