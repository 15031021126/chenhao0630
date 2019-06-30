package com.bawei.demo02.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/*
 *@Auther:陈浩
 *@Date: 2019/6/18
 *@Time:11:59
 *@Description:${DESCRIPTION}
 *
 * */
@Entity
public class CardItemEntity {
    private String commodityId;
    private String commodityName;
    private String count;
    private String pic;
    private String price;
    private Boolean checkboxstate;
    @Generated(hash = 745849386)
    public CardItemEntity(String commodityId, String commodityName, String count,
            String pic, String price, Boolean checkboxstate) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.count = count;
        this.pic = pic;
        this.price = price;
        this.checkboxstate = checkboxstate;
    }
    @Generated(hash = 1081008228)
    public CardItemEntity() {
    }
    public String getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getCount() {
        return this.count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public Boolean getCheckboxstate() {
        return this.checkboxstate;
    }
    public void setCheckboxstate(Boolean checkboxstate) {
        this.checkboxstate = checkboxstate;
    }

    @Override
    public String toString() {
        return "CardItemEntity{" +
                "commodityId='" + commodityId + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", count='" + count + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                ", checkboxstate=" + checkboxstate +
                '}';
    }
}
