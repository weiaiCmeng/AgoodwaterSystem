package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class OutstandingBean {
    /**
     * message : 未分配订单
     * data : {"orderList":[{"id":175675,"distribution":1,"status":1,"reTnum":null,"songName":null,"songSendTime":null,"ship_address":"龙华园龙华园东区29号楼204 （晚上七点左右送）","ship_mobile_phone":"13520610312","send_time_range":"10:01-11:01","storeremarks":null,"create_date":1507600918000,"update_date":null,"isFinish":1,"city":"北京","user_name":"13520610312","waterNum":"13811667658","isCompany":0,"goods_id":6,"goods_num":1,"money":null,"songNum":null,"goods_name":"景田","remarks":"","ship_name":"杨凯","order_no":"20171010100158815279","reTgoods":null,"deposit":0,"send_time":"10月10日 周二 今天","songTP":null,"ysjremark":null,"detailed":null,"time1":null,"user_id":9263,"time2":null},{"id":175611,"distribution":1,"status":1,"reTnum":null,"songName":null,"songSendTime":null,"ship_address":"京投发展公园悦府8号楼2单元202","ship_mobile_phone":"18301553496","send_time_range":"19:00-21:00","storeremarks":null,"create_date":1507595963000,"update_date":null,"isFinish":1,"city":"北京","user_name":"18301553496","waterNum":"13811667658","isCompany":0,"goods_id":6,"goods_num":1,"money":null,"songNum":null,"goods_name":"景田","remarks":"","ship_name":"白力","order_no":"20171010083923372854","reTgoods":null,"deposit":0,"send_time":"10月10日 周二 今天","songTP":null,"ysjremark":null,"detailed":null,"time1":null,"user_id":37311,"time2":null}],"disList":[{"id":409,"status":1,"session":1,"user_mobile_phone":"18210190532","user_num":600356,"pis":null,"storeName":null,"tongNum":0,"submitStore":null,"create_date":1503449618000,"user_name":"刘建平","waterNum":"13811667658","deposit":0,"loginName":null,"passWord":"123456","danNum":0,"delFlag":1,"page":{"result":[],"startOfPage":0,"pageSize":10,"totalRowCount":0,"totalPageCount":0,"pageNo":1},"createTime":1512701528722,"updateTime":null},{"id":365,"status":1,"session":1,"user_mobile_phone":"15210306605","user_num":600321,"pis":null,"storeName":null,"tongNum":0,"submitStore":null,"create_date":1500043147000,"user_name":"李亮","waterNum":"13811667658","deposit":0,"loginName":null,"passWord":"123456","danNum":0,"delFlag":1,"page":{"result":[],"startOfPage":0,"pageSize":10,"totalRowCount":0,"totalPageCount":0,"pageNo":1},"createTime":1512701528723,"updateTime":null},{"id":191,"status":1,"session":1,"user_mobile_phone":"13513921129","user_num":600166,"pis":null,"storeName":null,"tongNum":0,"submitStore":null,"create_date":1502187628000,"user_name":"乔红亮","waterNum":"13811667658","deposit":0,"loginName":null,"passWord":"654321","danNum":0,"delFlag":1,"page":{"result":[],"startOfPage":0,"pageSize":10,"totalRowCount":0,"totalPageCount":0,"pageNo":1},"createTime":1512701528723,"updateTime":null},{"id":177,"status":1,"session":1,"user_mobile_phone":"15210957621","user_num":600152,"pis":null,"storeName":null,"tongNum":0,"submitStore":null,"create_date":1492412444000,"user_name":"李志勇","waterNum":"13811667658","deposit":0,"loginName":null,"passWord":"123456","danNum":0,"delFlag":1,"page":{"result":[],"startOfPage":0,"pageSize":10,"totalRowCount":0,"totalPageCount":0,"pageNo":1},"createTime":1512701528723,"updateTime":null},{"id":176,"status":1,"session":1,"user_mobile_phone":"17703205621","user_num":600151,"pis":null,"storeName":null,"tongNum":0,"submitStore":null,"create_date":1492412447000,"user_name":"李占领","waterNum":"13811667658","deposit":0,"loginName":null,"passWord":"123456","danNum":0,"delFlag":1,"page":{"result":[],"startOfPage":0,"pageSize":10,"totalRowCount":0,"totalPageCount":0,"pageNo":1},"createTime":1512701528724,"updateTime":null}]}
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
        private List<DisListBean> disList;

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public List<DisListBean> getDisList() {
            return disList;
        }

        public void setDisList(List<DisListBean> disList) {
            this.disList = disList;
        }

        public static class OrderListBean {
            /**
             * id : 175675
             * distribution : 1
             * status : 1
             * reTnum : null
             * songName : null
             * songSendTime : null
             * ship_address : 龙华园龙华园东区29号楼204 （晚上七点左右送）
             * ship_mobile_phone : 13520610312
             * send_time_range : 10:01-11:01
             * storeremarks : null
             * create_date : 1507600918000
             * update_date : null
             * isFinish : 1
             * city : 北京
             * user_name : 13520610312
             * waterNum : 13811667658
             * isCompany : 0
             * goods_id : 6
             * goods_num : 1
             * money : null
             * songNum : null
             * goods_name : 景田
             * remarks :
             * ship_name : 杨凯
             * order_no : 20171010100158815279
             * reTgoods : null
             * deposit : 0
             * send_time : 10月10日 周二 今天
             * songTP : null
             * ysjremark : null
             * detailed : null
             * time1 : null
             * user_id : 9263
             * time2 : null
             */

            private int id;
            private int distribution;
            private int status;
            private Object reTnum;
            private Object songName;
            private Object songSendTime;
            private String ship_address;
            private String ship_mobile_phone;
            private String send_time_range;
            private Object storeremarks;
            private long create_date;
            private Object update_date;
            private int isFinish;
            private String city;
            private String user_name;
            private String waterNum;
            private int isCompany;
            private int goods_id;
            private int goods_num;
            private Object money;
            private Object songNum;
            private String goods_name;
            private String remarks;
            private String ship_name;
            private String order_no;
            private Object reTgoods;
            private int deposit;
            private String send_time;
            private Object songTP;
            private String ysjremark;
            private Object detailed;
            private Object time1;
            private int user_id;
            private Object time2;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public Object getReTnum() {
                return reTnum;
            }

            public void setReTnum(Object reTnum) {
                this.reTnum = reTnum;
            }

            public Object getSongName() {
                return songName;
            }

            public void setSongName(Object songName) {
                this.songName = songName;
            }

            public Object getSongSendTime() {
                return songSendTime;
            }

            public void setSongSendTime(Object songSendTime) {
                this.songSendTime = songSendTime;
            }

            public String getShip_address() {
                return ship_address;
            }

            public void setShip_address(String ship_address) {
                this.ship_address = ship_address;
            }

            public String getShip_mobile_phone() {
                return ship_mobile_phone;
            }

            public void setShip_mobile_phone(String ship_mobile_phone) {
                this.ship_mobile_phone = ship_mobile_phone;
            }

            public String getSend_time_range() {
                return send_time_range;
            }

            public void setSend_time_range(String send_time_range) {
                this.send_time_range = send_time_range;
            }

            public Object getStoreremarks() {
                return storeremarks;
            }

            public void setStoreremarks(Object storeremarks) {
                this.storeremarks = storeremarks;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public Object getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(Object update_date) {
                this.update_date = update_date;
            }

            public int getIsFinish() {
                return isFinish;
            }

            public void setIsFinish(int isFinish) {
                this.isFinish = isFinish;
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

            public String getWaterNum() {
                return waterNum;
            }

            public void setWaterNum(String waterNum) {
                this.waterNum = waterNum;
            }

            public int getIsCompany() {
                return isCompany;
            }

            public void setIsCompany(int isCompany) {
                this.isCompany = isCompany;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public Object getMoney() {
                return money;
            }

            public void setMoney(Object money) {
                this.money = money;
            }

            public Object getSongNum() {
                return songNum;
            }

            public void setSongNum(Object songNum) {
                this.songNum = songNum;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
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

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public Object getReTgoods() {
                return reTgoods;
            }

            public void setReTgoods(Object reTgoods) {
                this.reTgoods = reTgoods;
            }

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public Object getSongTP() {
                return songTP;
            }

            public void setSongTP(Object songTP) {
                this.songTP = songTP;
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

            public Object getTime1() {
                return time1;
            }

            public void setTime1(Object time1) {
                this.time1 = time1;
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
        }

        public static class DisListBean {
            /**
             * id : 409
             * status : 1
             * session : 1
             * user_mobile_phone : 18210190532
             * user_num : 600356
             * pis : null
             * storeName : null
             * tongNum : 0
             * submitStore : null
             * create_date : 1503449618000
             * user_name : 刘建平
             * waterNum : 13811667658
             * deposit : 0
             * loginName : null
             * passWord : 123456
             * danNum : 0
             * delFlag : 1
             * page : {"result":[],"startOfPage":0,"pageSize":10,"totalRowCount":0,"totalPageCount":0,"pageNo":1}
             * createTime : 1512701528722
             * updateTime : null
             */

            private int id;
            private int status;
            private int session;
            private String user_mobile_phone;
            private int user_num;
            private Object pis;
            private Object storeName;
            private int tongNum;
            private Object submitStore;
            private long create_date;
            private String user_name;
            private String waterNum;
            private int deposit;
            private Object loginName;
            private String passWord;
            private int danNum;
            private int delFlag;
            private PageBean page;
            private long createTime;
            private Object updateTime;

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

            public int getSession() {
                return session;
            }

            public void setSession(int session) {
                this.session = session;
            }

            public String getUser_mobile_phone() {
                return user_mobile_phone;
            }

            public void setUser_mobile_phone(String user_mobile_phone) {
                this.user_mobile_phone = user_mobile_phone;
            }

            public int getUser_num() {
                return user_num;
            }

            public void setUser_num(int user_num) {
                this.user_num = user_num;
            }

            public Object getPis() {
                return pis;
            }

            public void setPis(Object pis) {
                this.pis = pis;
            }

            public Object getStoreName() {
                return storeName;
            }

            public void setStoreName(Object storeName) {
                this.storeName = storeName;
            }

            public int getTongNum() {
                return tongNum;
            }

            public void setTongNum(int tongNum) {
                this.tongNum = tongNum;
            }

            public Object getSubmitStore() {
                return submitStore;
            }

            public void setSubmitStore(Object submitStore) {
                this.submitStore = submitStore;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getWaterNum() {
                return waterNum;
            }

            public void setWaterNum(String waterNum) {
                this.waterNum = waterNum;
            }

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public Object getLoginName() {
                return loginName;
            }

            public void setLoginName(Object loginName) {
                this.loginName = loginName;
            }

            public String getPassWord() {
                return passWord;
            }

            public void setPassWord(String passWord) {
                this.passWord = passWord;
            }

            public int getDanNum() {
                return danNum;
            }

            public void setDanNum(int danNum) {
                this.danNum = danNum;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public static class PageBean {
                /**
                 * result : []
                 * startOfPage : 0
                 * pageSize : 10
                 * totalRowCount : 0
                 * totalPageCount : 0
                 * pageNo : 1
                 */

                private int startOfPage;
                private int pageSize;
                private int totalRowCount;
                private int totalPageCount;
                private int pageNo;
                private List<?> result;

                public int getStartOfPage() {
                    return startOfPage;
                }

                public void setStartOfPage(int startOfPage) {
                    this.startOfPage = startOfPage;
                }

                public int getPageSize() {
                    return pageSize;
                }

                public void setPageSize(int pageSize) {
                    this.pageSize = pageSize;
                }

                public int getTotalRowCount() {
                    return totalRowCount;
                }

                public void setTotalRowCount(int totalRowCount) {
                    this.totalRowCount = totalRowCount;
                }

                public int getTotalPageCount() {
                    return totalPageCount;
                }

                public void setTotalPageCount(int totalPageCount) {
                    this.totalPageCount = totalPageCount;
                }

                public int getPageNo() {
                    return pageNo;
                }

                public void setPageNo(int pageNo) {
                    this.pageNo = pageNo;
                }

                public List<?> getResult() {
                    return result;
                }

                public void setResult(List<?> result) {
                    this.result = result;
                }
            }
        }
    }
}
