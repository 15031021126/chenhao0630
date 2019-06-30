package com.bawei.demo02.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/*
 *@Auther:陈浩
 *@Date: 2019/6/27
 *@Time:10:50
 *@Description:${DESCRIPTION}
 * */public class Pic extends SimpleBannerInfo {
     private String pic;

    @Override
    public String toString() {
        return "Pic{" +
                "pic='" + pic + '\'' +
                '}';
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Pic() {
    }

    public Pic(String pic) {
        this.pic = pic;
    }

    @Override
    public String getXBannerUrl() {
        return getPic();
    }
}
