package com.agoodwater.system.bean;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class StoreBean {
    /**
     * message : 门店管理当日统计获取成功
     * data : {"waterName":"回龙观店","orderSize":0,"tongNum":6,"money":67}
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
         * waterName : 回龙观店
         * orderSize : 0
         * tongNum : 6
         * money : 67
         */

        private String waterName;
        private int orderSize;
        private int tongNum;
        private int money;

        public String getWaterName() {
            return waterName;
        }

        public void setWaterName(String waterName) {
            this.waterName = waterName;
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
    }
}
