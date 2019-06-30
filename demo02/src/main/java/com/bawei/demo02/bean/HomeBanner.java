package com.bawei.demo02.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:17:18
 *@Description:${DESCRIPTION}
 * */public class HomeBanner extends SimpleBannerInfo {
    private String imageUrl;
    private String jumpUrl;
    private String rank;
    private String title;

    @Override
    public String toString() {
        return "HomeBanner{" +
                "imageUrl='" + imageUrl + '\'' +
                ", jumpUrl='" + jumpUrl + '\'' +
                ", rank='" + rank + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HomeBanner() {
    }

    public HomeBanner(String imageUrl, String jumpUrl, String rank, String title) {
        this.imageUrl = imageUrl;
        this.jumpUrl = jumpUrl;
        this.rank = rank;
        this.title = title;
    }

    @Override
    public String getXBannerUrl() {
        return imageUrl;
    }
}
