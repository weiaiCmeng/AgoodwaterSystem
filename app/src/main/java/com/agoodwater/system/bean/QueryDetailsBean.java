package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/4/21.
 */

public class QueryDetailsBean {


    /**
     * message : 查询订单成功
     * data : {"orderList":[{"id":48969,"status":1,"distribution":2,"storeremarks":null,"ship_mobile_phone":"18813014231","ship_address":"测试 ","send_time_range":"16:06-17:06","create_date":1491380022000,"goods_name":"景田","update_date":1491978009000,"songSendTime":1491978033000,"remarks":"","ship_name":"测试","send_time":"04月05日 周三 今天","songTP":"13501383557","detailed":null,"user_id":368,"songNum":600201,"songName":"ç\u0094°é\u0087\u008e","deposit":0,"reTgoods":"水立方,","order_no":"20170405161342769092","goods_num":1,"user_name":"18813014231","reTnum":"1,","waterNum":"admin","money":null,"goods_id":6,"isFinish":2},{"id":48968,"status":1,"distribution":2,"storeremarks":null,"ship_mobile_phone":"18813014231","ship_address":"测试 ","send_time_range":"16:06-17:06","create_date":1491379606000,"goods_name":"景田","update_date":1491978103000,"songSendTime":1491978117000,"remarks":"","ship_name":"测试","send_time":"04月05日 周三 今天","songTP":"13501383557","detailed":null,"user_id":368,"songNum":600201,"songName":"ç\u0094°é\u0087\u008e","deposit":0,"reTgoods":"水立方,水立方,","order_no":"20170405160646525554","goods_num":1,"user_name":"18813014231","reTnum":"1,1,","waterNum":"admin","money":null,"goods_id":6,"isFinish":2},{"id":48965,"status":1,"distribution":2,"storeremarks":null,"ship_mobile_phone":"18814014444","ship_address":"北京市昌平区琥珀天地539","send_time_range":"15:31-16:31","create_date":1491377517000,"goods_name":"汇源","update_date":1491978234000,"songSendTime":1491978258000,"remarks":"","ship_name":"焦翔","send_time":"04月05日 周三今天","songTP":"13501383557","detailed":null,"user_id":4823,"songNum":600201,"songName":"ç\u0094°é\u0087\u008e","deposit":0,"reTgoods":"水立方,","order_no":"20170405153157460485","goods_num":1,"user_name":"18814014444","reTnum":"1,","waterNum":"admin","money":null,"goods_id":5,"isFinish":2},{"id":48964,"status":1,"distribution":2,"storeremarks":null,"ship_mobile_phone":"18814014444","ship_address":"北京市昌平区琥珀天地539","send_time_range":"15:30-16:30","create_date":1491377410000,"goods_name":"乐百氏","update_date":1491978383000,"songSendTime":1491978397000,"remarks":"","ship_name":"焦翔","send_time":"04月05日 周三今天","songTP":"13501383557","detailed":null,"user_id":4823,"songNum":600201,"songName":"ç\u0094°é\u0087\u008e","deposit":0,"reTgoods":"景田,","order_no":"20170405153010147033","goods_num":1,"user_name":"18814014444","reTnum":"1,","waterNum":"admin","money":null,"goods_id":3,"isFinish":2}]}
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
        private List<OrderListBean> orderList;

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * id : 48969
             * status : 1
             * distribution : 2
             * storeremarks : null
             * ship_mobile_phone : 18813014231
             * ship_address : 测试
             * send_time_range : 16:06-17:06
             * create_date : 1491380022000
             * goods_name : 景田
             * update_date : 1491978009000
             * songSendTime : 1491978033000
             * remarks :
             * ship_name : 测试
             * send_time : 04月05日 周三 今天
             * songTP : 13501383557
             * detailed : null
             * user_id : 368
             * songNum : 600201
             * songName : ç°é
             * deposit : 0
             * reTgoods : 水立方,
             * order_no : 20170405161342769092
             * goods_num : 1
             * user_name : 18813014231
             * reTnum : 1,
             * waterNum : admin
             * money : null
             * goods_id : 6
             * isFinish : 2
             */

            private int id;
            private int status;
            private int distribution;
            private String storeremarks;
            private String ship_mobile_phone;
            private String ship_address;
            private String send_time_range;
            private long create_date;
            private String goods_name;
            private long update_date;
            private long songSendTime;
            private String remarks;
            private String ship_name;
            private String send_time;
            private String songTP;
            private Object detailed;
            private int user_id;
            private int songNum;
            private String songName;
            private int deposit;
            private String reTgoods;
            private String order_no;
            private int goods_num;
            private String user_name;
            private String reTnum;
            private String waterNum;
            private Object money;
            private int goods_id;
            private int isFinish;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getDistribution() {
                return distribution;
            }

            public void setDistribution(int distribution) {
                this.distribution = distribution;
            }

            public String getStoreremarks() {
                return storeremarks;
            }

            public void setStoreremarks(String storeremarks) {
                this.storeremarks = storeremarks;
            }

            public String getShip_mobile_phone() {
                return ship_mobile_phone;
            }

            public void setShip_mobile_phone(String ship_mobile_phone) {
                this.ship_mobile_phone = ship_mobile_phone;
            }

            public String getShip_address() {
                return ship_address;
            }

            public void setShip_address(String ship_address) {
                this.ship_address = ship_address;
            }

            public String getSend_time_range() {
                return send_time_range;
            }

            public void setSend_time_range(String send_time_range) {
                this.send_time_range = send_time_range;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public long getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(long update_date) {
                this.update_date = update_date;
            }

            public long getSongSendTime() {
                return songSendTime;
            }

            public void setSongSendTime(long songSendTime) {
                this.songSendTime = songSendTime;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getShip_name() {
                return ship_name;
            }

            public void setShip_name(String ship_name) {
                this.ship_name = ship_name;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getSongTP() {
                return songTP;
            }

            public void setSongTP(String songTP) {
                this.songTP = songTP;
            }

            public Object getDetailed() {
                return detailed;
            }

            public void setDetailed(Object detailed) {
                this.detailed = detailed;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getSongNum() {
                return songNum;
            }

            public void setSongNum(int songNum) {
                this.songNum = songNum;
            }

            public String getSongName() {
                return songName;
            }

            public void setSongName(String songName) {
                this.songName = songName;
            }

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public String getReTgoods() {
                return reTgoods;
            }

            public void setReTgoods(String reTgoods) {
                this.reTgoods = reTgoods;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getReTnum() {
                return reTnum;
            }

            public void setReTnum(String reTnum) {
                this.reTnum = reTnum;
            }

            public String getWaterNum() {
                return waterNum;
            }

            public void setWaterNum(String waterNum) {
                this.waterNum = waterNum;
            }

            public Object getMoney() {
                return money;
            }

            public void setMoney(Object money) {
                this.money = money;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getIsFinish() {
                return isFinish;
            }

            public void setIsFinish(int isFinish) {
                this.isFinish = isFinish;
            }
        }
    }
}
