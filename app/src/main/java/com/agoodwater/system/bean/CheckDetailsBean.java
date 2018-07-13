package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class CheckDetailsBean {


    /**
     * message : 所有订单
     * data : {"time1":"2017-11-07","time2":"2017-12-12","orderSize":5,"salesDetileList":[{"id":null,"total":22,"userName":null,"songNum":null,"songName":null,"createDate":null,"goodsName":"景田","goodsId":null,"goodsNum":2,"city":null,"waterName":null,"waterNum":null,"unitPrice":12,"cityName":null},{"id":null,"total":40,"userName":null,"songNum":null,"songName":null,"createDate":null,"goodsName":"吉星","goodsId":null,"goodsNum":4,"city":null,"waterName":null,"waterNum":null,"unitPrice":10,"cityName":null},{"id":null,"total":15,"userName":null,"songNum":null,"songName":null,"createDate":null,"goodsName":"农夫山泉水","goodsId":null,"goodsNum":1,"city":null,"waterName":null,"waterNum":null,"unitPrice":15,"cityName":null}],"tongNum":7,"money":77}
     * infoCode : 200
     * success : 1
     */

    private String message;
    private DataBean data;
    private int infoCode;
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * time1 : 2017-11-07
         * time2 : 2017-12-12
         * orderSize : 5
         * salesDetileList : [{"id":null,"total":22,"userName":null,"songNum":null,"songName":null,"createDate":null,"goodsName":"景田","goodsId":null,"goodsNum":2,"city":null,"waterName":null,"waterNum":null,"unitPrice":12,"cityName":null},{"id":null,"total":40,"userName":null,"songNum":null,"songName":null,"createDate":null,"goodsName":"吉星","goodsId":null,"goodsNum":4,"city":null,"waterName":null,"waterNum":null,"unitPrice":10,"cityName":null},{"id":null,"total":15,"userName":null,"songNum":null,"songName":null,"createDate":null,"goodsName":"农夫山泉水","goodsId":null,"goodsNum":1,"city":null,"waterName":null,"waterNum":null,"unitPrice":15,"cityName":null}]
         * tongNum : 7
         * money : 77
         */

        private String time1;
        private String time2;
        private int orderSize;
        private int tongNum;
        private int money;
        private List<SalesDetileListBean> salesDetileList;

        public String getTime1() {
            return time1;
        }

        public void setTime1(String time1) {
            this.time1 = time1;
        }

        public String getTime2() {
            return time2;
        }

        public void setTime2(String time2) {
            this.time2 = time2;
        }

        public int getOrderSize() {
            return orderSize;
        }

        public void setOrderSize(int orderSize) {
            this.orderSize = orderSize;
        }

        public int getTongNum() {
            return tongNum;
        }

        public void setTongNum(int tongNum) {
            this.tongNum = tongNum;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public List<SalesDetileListBean> getSalesDetileList() {
            return salesDetileList;
        }

        public void setSalesDetileList(List<SalesDetileListBean> salesDetileList) {
            this.salesDetileList = salesDetileList;
        }

        public static class SalesDetileListBean {
            /**
             * id : null
             * total : 22
             * userName : null
             * songNum : null
             * songName : null
             * createDate : null
             * goodsName : 景田
             * goodsId : null
             * goodsNum : 2
             * city : null
             * waterName : null
             * waterNum : null
             * unitPrice : 12
             * cityName : null
             */

            private Object id;
            private int total;
            private Object userName;
            private Object songNum;
            private Object songName;
            private Object createDate;
            private String goodsName;
            private Object goodsId;
            private int goodsNum;
            private Object city;
            private Object waterName;
            private Object waterNum;
            private int unitPrice;
            private Object cityName;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public Object getSongNum() {
                return songNum;
            }

            public void setSongNum(Object songNum) {
                this.songNum = songNum;
            }

            public Object getSongName() {
                return songName;
            }

            public void setSongName(Object songName) {
                this.songName = songName;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public Object getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(Object goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getWaterName() {
                return waterName;
            }

            public void setWaterName(Object waterName) {
                this.waterName = waterName;
            }

            public Object getWaterNum() {
                return waterNum;
            }

            public void setWaterNum(Object waterNum) {
                this.waterNum = waterNum;
            }

            public int getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(int unitPrice) {
                this.unitPrice = unitPrice;
            }

            public Object getCityName() {
                return cityName;
            }

            public void setCityName(Object cityName) {
                this.cityName = cityName;
            }
        }
    }
}
