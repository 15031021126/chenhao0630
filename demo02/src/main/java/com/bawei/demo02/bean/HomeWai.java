package com.bawei.demo02.bean;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:20:17
 *@Description:${DESCRIPTION}
 * */public class HomeWai {
     private String mlss;
     private String pzsh;
     private String rxxp;

    public HomeWai() {
    }

    @Override
    public String toString() {
        return "HomeWai{" +
                "mlss='" + mlss + '\'' +
                ", pzsh='" + pzsh + '\'' +
                ", rxxp='" + rxxp + '\'' +
                '}';
    }

    public String getMlss() {
        return mlss;
    }

    public void setMlss(String mlss) {
        this.mlss = mlss;
    }

    public String getPzsh() {
        return pzsh;
    }

    public void setPzsh(String pzsh) {
        this.pzsh = pzsh;
    }

    public String getRxxp() {
        return rxxp;
    }

    public void setRxxp(String rxxp) {
        this.rxxp = rxxp;
    }

    public HomeWai(String mlss, String pzsh, String rxxp) {
        this.mlss = mlss;
        this.pzsh = pzsh;
        this.rxxp = rxxp;
    }
}
