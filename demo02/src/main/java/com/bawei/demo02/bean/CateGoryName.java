package com.bawei.demo02.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/*
 *@Auther:陈浩
 *@Date: 2019/6/18
 *@Time:14:04
 *@Description:${DESCRIPTION}
 * */
@Entity
public class CateGoryName  {

    private String categoryName;

    @Generated(hash = 1874808149)
    public CateGoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Generated(hash = 11093249)
    public CateGoryName() {
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CateGoryName{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
