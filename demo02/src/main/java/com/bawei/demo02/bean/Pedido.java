package com.bawei.demo02.bean;

import java.util.List;

/*
 *@Auther:陈浩
 *@Date: 2019/6/21
 *@Time:21:12
 *@Description:${DESCRIPTION}
 * */public class Pedido {

    /**
     * message : 查询成功
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":2,"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/4.jpg","commodityPrice":19,"orderDetailId":10691},{"commentStatus":1,"commodityCount":2,"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/5.jpg,","commodityPrice":39,"orderDetailId":10692}],"expressCompName":"京东快递","expressSn":"1001","orderId":"201906211953513873377","orderStatus":1,"payAmount":116,"payMethod":1,"userId":3377},{"detailList":[{"commentStatus":1,"commodityCount":2,"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/4.jpg","commodityPrice":19,"orderDetailId":10690}],"expressCompName":"京东快递","expressSn":"1001","orderId":"201906211952148763377","orderStatus":1,"payAmount":38,"payMethod":1,"userId":3377}]
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", orderList=" + orderList +
                '}';
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":2,"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/4.jpg","commodityPrice":19,"orderDetailId":10691},{"commentStatus":1,"commodityCount":2,"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/5.jpg,","commodityPrice":39,"orderDetailId":10692}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 201906211953513873377
         * orderStatus : 1
         * payAmount : 116
         * payMethod : 1
         * userId : 3377
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            public DetailListBean(int commentStatus, int commodityCount, int commodityId, String commodityName, String commodityPic, int commodityPrice, int orderDetailId) {
                this.commentStatus = commentStatus;
                this.commodityCount = commodityCount;
                this.commodityId = commodityId;
                this.commodityName = commodityName;
                this.commodityPic = commodityPic;
                this.commodityPrice = commodityPrice;
                this.orderDetailId = orderDetailId;
            }

            @Override
            public String toString() {
                return "DetailListBean{" +
                        "commentStatus=" + commentStatus +
                        ", commodityCount=" + commodityCount +
                        ", commodityId=" + commodityId +
                        ", commodityName='" + commodityName + '\'' +
                        ", commodityPic='" + commodityPic + '\'' +
                        ", commodityPrice=" + commodityPrice +
                        ", orderDetailId=" + orderDetailId +
                        '}';
            }

            /**
             * commentStatus : 1
             * commodityCount : 2
             * commodityId : 8
             * commodityName : Lara style蜜颊润泽腮红
             * commodityPic : http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/6/4.jpg
             * commodityPrice : 19
             * orderDetailId : 10691
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }

        @Override
        public String toString() {
            return "OrderListBean{" +
                    "expressCompName='" + expressCompName + '\'' +
                    ", expressSn='" + expressSn + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", orderStatus=" + orderStatus +
                    ", payAmount=" + payAmount +
                    ", payMethod=" + payMethod +
                    ", userId=" + userId +
                    ", detailList=" + detailList +
                    '}';
        }

    }
}
