package com.bawei.chenhao0603.bean;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:14:14
 *@Description:多条目}
 * */public class Bean {
     private String intro;
     private String u;
     private String title;

    @Override
    public String toString() {
        return "Bean{" +
                "intro='" + intro + '\'' +
                ", u='" + u + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bean() {
    }

    public Bean(String intro, String u, String title) {
        this.intro = intro;
        this.u = u;
        this.title = title;
    }
}
