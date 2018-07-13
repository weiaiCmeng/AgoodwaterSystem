package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/23.
 */
public class WaterCompletedBean {
    /**
     * message : 所有订单
     * data : {"orderList":[{"id":175847,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600321,"send_time":"10月10日 周二 今天","order_no":"20171010153930838807","deposit":0,"reTgoods":"","songName":"李亮","goods_name":"吉星","money":null,"city":"北京","user_name":"13261678264","goods_id":8,"isFinish":2,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":6982,"time2":null,"time1":null,"reTnum":"","songTP":"15210306605","remarks":"","create_date":1512631731000,"update_date":1507621181000,"songSendTime":1513061817000,"ship_mobile_phone":"13261678264","ship_address":"北京市昌平区和谐家园-一区6号楼3单元101","send_time_range":"15:39-17:39","storeremarks":null,"ship_name":"王雪川"},{"id":175846,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600356,"send_time":"10月10日 周二今天","order_no":"3013669111994807376740938","deposit":0,"reTgoods":"","songName":"刘建平","goods_name":"景田","money":null,"city":"北京","user_name":"18613807802","goods_id":6,"isFinish":2,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":36046,"time2":null,"time1":null,"reTnum":"","songTP":"18210190532","remarks":"","create_date":1512631731000,"update_date":1507621186000,"songSendTime":1513061817000,"ship_mobile_phone":"18613807802","ship_address":"风雅园-三区12号楼5单元501","send_time_range":"15:38-17:38","storeremarks":null,"ship_name":"胡杰伊(女士)"},{"id":175844,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600388,"send_time":"10月10日 周二 今天","order_no":"20171010152919573863","deposit":0,"reTgoods":null,"songName":"乔国轩","goods_name":"娃哈哈","money":null,"city":"北京","user_name":"18610710818","goods_id":7,"isFinish":1,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":11612,"time2":null,"time1":null,"reTnum":null,"songTP":"18539322043","remarks":"","create_date":1512631731000,"update_date":1507620577000,"songSendTime":null,"ship_mobile_phone":"18610710818","ship_address":"龙跃苑东四区-20号楼4单元201","send_time_range":"15:28-17:28","storeremarks":null,"ship_name":"吕国成"},{"id":175839,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600321,"send_time":"10月10日 周二 今天","order_no":"20171010151406591859","deposit":0,"reTgoods":null,"songName":"李亮","goods_name":"吉星","money":null,"city":"北京","user_name":"15611436317","goods_id":8,"isFinish":1,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":12642,"time2":null,"time1":null,"reTnum":null,"songTP":"15210306605","remarks":"","create_date":1512631731000,"update_date":1507619703000,"songSendTime":null,"ship_mobile_phone":"15611436317","ship_address":"北京市昌平区天龙苑26号楼1单元南面阳光房","send_time_range":"15:13-17:13","storeremarks":null,"ship_name":"周云贞"},{"id":175833,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600356,"send_time":"10月10日 周二 今天","order_no":"20171010145632793661","deposit":0,"reTgoods":null,"songName":"刘建平","goods_name":"汇源","money":null,"city":"北京","user_name":"13810770952","goods_id":5,"isFinish":1,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":9510,"time2":null,"time1":null,"reTnum":null,"songTP":"18210190532","remarks":"","create_date":1512631731000,"update_date":1507618607000,"songSendTime":null,"ship_mobile_phone":"13810770952","ship_address":"北京市昌平区龙华园小区30-5-201","send_time_range":"14:56-16:56","storeremarks":null,"ship_name":"李波"},{"id":175829,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600321,"send_time":"10月10日 周二今天","order_no":"20171010145153957058","deposit":0,"reTgoods":"景田,","songName":"李亮","goods_name":"景田","money":null,"city":"北京","user_name":"17310018089","goods_id":6,"isFinish":2,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":30805,"time2":null,"time1":null,"reTnum":"1,","songTP":"15210306605","remarks":"","create_date":1507618313000,"update_date":1507618344000,"songSendTime":1513061817000,"ship_mobile_phone":"17310018089","ship_address":"禧乐汇-东北门禧乐汇一层中国联通","send_time_range":"14:51-16:51","storeremarks":null,"ship_name":"张女士"},{"id":175828,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600321,"send_time":"10月10日 周二 今天","order_no":"20171010144645737510","deposit":0,"reTgoods":null,"songName":"李亮","goods_name":"雀巢","money":null,"city":"北京","user_name":"17301242340","goods_id":9,"isFinish":1,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":39818,"time2":null,"time1":null,"reTnum":null,"songTP":"15210306605","remarks":"","create_date":1507618005000,"update_date":1507621025000,"songSendTime":null,"ship_mobile_phone":"17301242340","ship_address":"田园风光雅苑-10号楼1单元102","send_time_range":"17:00-19:00","storeremarks":null,"ship_name":"张炜彤"},{"id":175824,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600388,"send_time":"10月10日 周二今天","order_no":"20171010143732762692","deposit":0,"reTgoods":"雀巢,","songName":"乔国轩","goods_name":"雀巢","money":null,"city":"北京","user_name":"13311368635","goods_id":9,"isFinish":2,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":36996,"time2":null,"time1":null,"reTnum":"1,","songTP":"18539322043","remarks":"","create_date":1507617452000,"update_date":1507617471000,"songSendTime":1513061817000,"ship_mobile_phone":"13311368635","ship_address":"龙腾苑五区-1号楼三单元501","send_time_range":"14:37-16:37","storeremarks":null,"ship_name":"靳超"},{"id":175822,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600321,"send_time":"10月10日 周二今天","order_no":"20171010143510899117","deposit":0,"reTgoods":"吉星,","songName":"李亮","goods_name":"吉星","money":null,"city":"北京","user_name":"18500140083","goods_id":8,"isFinish":2,"isCompany":0,"waterNum":"13811667658","goods_num":2,"user_id":7328,"time2":null,"time1":null,"reTnum":"2,","songTP":"15210306605","remarks":"","create_date":1507617310000,"update_date":1507617321000,"songSendTime":1513061817000,"ship_mobile_phone":"18500140083","ship_address":"北京市昌平区田园风光雅苑 中国农业大学田园风光综合商业楼2号3层翡翠集团","send_time_range":"14:35-16:35","storeremarks":null,"ship_name":"李先生"},{"id":175820,"ysjremark":null,"detailed":null,"distribution":2,"status":1,"songNum":600388,"send_time":"10月10日 周二 今天","order_no":"20171010143321397215","deposit":0,"reTgoods":"景田,","songName":"乔国轩","goods_name":"景田","money":null,"city":"北京","user_name":"13810165417","goods_id":6,"isFinish":2,"isCompany":0,"waterNum":"13811667658","goods_num":1,"user_id":11540,"time2":null,"time1":null,"reTnum":"1,","songTP":"18539322043","remarks":"","create_date":1507617201000,"update_date":1507617214000,"songSendTime":1513061817000,"ship_mobile_phone":"13810165417","ship_address":"龙冠商务中心银座603 巴塔科技","send_time_range":"14:32-16:32","storeremarks":null,"ship_name":"张思"}]}
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
             * id : 175847
             * ysjremark : null
             * detailed : null
             * distribution : 2
             * status : 1
             * songNum : 600321
             * send_time : 10月10日 周二 今天
             * order_no : 20171010153930838807
             * deposit : 0
             * reTgoods :
             * songName : 李亮
             * goods_name : 吉星
             * money : null
             * city : 北京
             * user_name : 13261678264
             * goods_id : 8
             * isFinish : 2
             * isCompany : 0
             * waterNum : 13811667658
             * goods_num : 1
             * user_id : 6982
             * time2 : null
             * time1 : null
             * reTnum :
             * songTP : 15210306605
             * remarks :
             * create_date : 1512631731000
             * update_date : 1507621181000
             * songSendTime : 1513061817000
             * ship_mobile_phone : 13261678264
             * ship_address : 北京市昌平区和谐家园-一区6号楼3单元101
             * send_time_range : 15:39-17:39
             * storeremarks : null
             * ship_name : 王雪川
             */

            private int id;
            private String ysjremark;
            private Object detailed;
            private int distribution;
            private int status;
            private int songNum;
            private String send_time;
            private String order_no;
            private int deposit;
            private String reTgoods;
            private String songName;
            private String goods_name;
            private Object money;
            private String city;
            private String user_name;
            private int goods_id;
            private int isFinish;
            private int isCompany;
            private String waterNum;
            private int goods_num;
            private int user_id;
            private Object time2;
            private Object time1;
            private String reTnum;
            private String songTP;
            private String remarks;
            private long create_date;
            private long update_date;
            private long songSendTime;
            private String ship_mobile_phone;
            private String ship_address;
            private String send_time_range;
            private String storeremarks;
            private String ship_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getYsjremark() {
                return ysjremark;
            }

            public void setYsjremark(String ysjremark) {
                this.ysjremark = ysjremark;
            }

            public Object getDetailed() {
                return detailed;
            }

            public void setDetailed(Object detailed) {
                this.detailed = detailed;
            }

            public int getDistribution() {
                return distribution;
            }

            public void setDistribution(int distribution) {
                this.distribution = distribution;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSongNum() {
                return songNum;
            }

            public void setSongNum(int songNum) {
                this.songNum = songNum;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
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

            public String getSongName() {
                return songName;
            }

            public void setSongName(String songName) {
                this.songName = songName;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public Object getMoney() {
                return money;
            }

            public void setMoney(Object money) {
                this.money = money;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
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

            public int getIsCompany() {
                return isCompany;
            }

            public void setIsCompany(int isCompany) {
                this.isCompany = isCompany;
            }

            public String getWaterNum() {
                return waterNum;
            }

            public void setWaterNum(String waterNum) {
                this.waterNum = waterNum;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public Object getTime2() {
                return time2;
            }

            public void setTime2(Object time2) {
                this.time2 = time2;
            }

            public Object getTime1() {
                return time1;
            }

            public void setTime1(Object time1) {
                this.time1 = time1;
            }

            public String getReTnum() {
                return reTnum;
            }

            public void setReTnum(String reTnum) {
                this.reTnum = reTnum;
            }

            public String getSongTP() {
                return songTP;
            }

            public void setSongTP(String songTP) {
                this.songTP = songTP;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
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

            public String getStoreremarks() {
                return storeremarks;
            }

            public void setStoreremarks(String storeremarks) {
                this.storeremarks = storeremarks;
            }

            public String getShip_name() {
                return ship_name;
            }

            public void setShip_name(String ship_name) {
                this.ship_name = ship_name;
            }
        }
    }
}
