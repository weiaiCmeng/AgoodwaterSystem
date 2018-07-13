package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/23.
 */
public class BucketCompletedBean {


    /**
     * data : {"endTorderList":[{"createTime":1490256909360,"delFlag":1,"distribution":1,"distribution_Name":"q","getT_Time":1477380993000,"getT_num":"1,1,","getTtime":1463195918000,"goods":"","id":2,"orderTime":1477446015000,"order_No":"1","page":{"pageNo":1,"pageSize":10,"result":[],"startOfPage":0,"totalPageCount":0,"totalRowCount":0},"reT_Money":1,"remark":"态度要短暂","status":2,"storeName":"二号水电","supstatus":3,"t_Number":1,"userAddress":"龙泽","userId":0,"userName":"田野","userRemark":"不喝了","userTp":"111","user_num":1,"waterNum":"admin"}]}
     * infoCode : 200
     * message : 所有订单
     * success : 1
     */

    private DataBean data;
    private int infoCode;
    private String message;
    private String success;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class DataBean {
        private List<EndTorderListBean> endTorderList;

        public List<EndTorderListBean> getEndTorderList() {
            return endTorderList;
        }

        public void setEndTorderList(List<EndTorderListBean> endTorderList) {
            this.endTorderList = endTorderList;
        }

        public static class EndTorderListBean {
            /**
             * createTime : 1490256909360
             * delFlag : 1
             * distribution : 1
             * distribution_Name : q
             * getT_Time : 1477380993000
             * getT_num : 1,1,
             * getTtime : 1463195918000
             * goods :
             * id : 2
             * orderTime : 1477446015000
             * order_No : 1
             * page : {"pageNo":1,"pageSize":10,"result":[],"startOfPage":0,"totalPageCount":0,"totalRowCount":0}
             * reT_Money : 1
             * remark : 态度要短暂
             * status : 2
             * storeName : 二号水电
             * supstatus : 3
             * t_Number : 1
             * userAddress : 龙泽
             * userId : 0
             * userName : 田野
             * userRemark : 不喝了
             * userTp : 111
             * user_num : 1
             * waterNum : admin
             */

            private long createTime;
            private int delFlag;
            private int distribution;
            private String distribution_Name;
            private long getT_Time;
            private String getT_num;
            private long getTtime;
            private String goods;
            private int id;
            private long orderTime;
            private String order_No;
            private PageBean page;
            private int reT_Money;
            private String remark;
            private int status;
            private String storeName;
            private int supstatus;
            private int t_Number;
            private String userAddress;
            private int userId;
            private String userName;
            private String userRemark;
            private String userTp;
            private int user_num;
            private String waterNum;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public int getDistribution() {
                return distribution;
            }

            public void setDistribution(int distribution) {
                this.distribution = distribution;
            }

            public String getDistribution_Name() {
                return distribution_Name;
            }

            public void setDistribution_Name(String distribution_Name) {
                this.distribution_Name = distribution_Name;
            }

            public long getGetT_Time() {
                return getT_Time;
            }

            public void setGetT_Time(long getT_Time) {
                this.getT_Time = getT_Time;
            }

            public String getGetT_num() {
                return getT_num;
            }

            public void setGetT_num(String getT_num) {
                this.getT_num = getT_num;
            }

            public long getGetTtime() {
                return getTtime;
            }

            public void setGetTtime(long getTtime) {
                this.getTtime = getTtime;
            }

            public String getGoods() {
                return goods;
            }

            public void setGoods(String goods) {
                this.goods = goods;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getOrderTime() {
                return orderTime;
            }

            public void setOrderTime(long orderTime) {
                this.orderTime = orderTime;
            }

            public String getOrder_No() {
                return order_No;
            }

            public void setOrder_No(String order_No) {
                this.order_No = order_No;
            }

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public int getReT_Money() {
                return reT_Money;
            }

            public void setReT_Money(int reT_Money) {
                this.reT_Money = reT_Money;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public int getSupstatus() {
                return supstatus;
            }

            public void setSupstatus(int supstatus) {
                this.supstatus = supstatus;
            }

            public int getT_Number() {
                return t_Number;
            }

            public void setT_Number(int t_Number) {
                this.t_Number = t_Number;
            }

            public String getUserAddress() {
                return userAddress;
            }

            public void setUserAddress(String userAddress) {
                this.userAddress = userAddress;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserRemark() {
                return userRemark;
            }

            public void setUserRemark(String userRemark) {
                this.userRemark = userRemark;
            }

            public String getUserTp() {
                return userTp;
            }

            public void setUserTp(String userTp) {
                this.userTp = userTp;
            }

            public int getUser_num() {
                return user_num;
            }

            public void setUser_num(int user_num) {
                this.user_num = user_num;
            }

            public String getWaterNum() {
                return waterNum;
            }

            public void setWaterNum(String waterNum) {
                this.waterNum = waterNum;
            }

            public static class PageBean {
                /**
                 * pageNo : 1
                 * pageSize : 10
                 * result : []
                 * startOfPage : 0
                 * totalPageCount : 0
                 * totalRowCount : 0
                 */

                private int pageNo;
                private int pageSize;
                private int startOfPage;
                private int totalPageCount;
                private int totalRowCount;
                private List<?> result;

                public int getPageNo() {
                    return pageNo;
                }

                public void setPageNo(int pageNo) {
                    this.pageNo = pageNo;
                }

                public int getPageSize() {
                    return pageSize;
                }

                public void setPageSize(int pageSize) {
                    this.pageSize = pageSize;
                }

                public int getStartOfPage() {
                    return startOfPage;
                }

                public void setStartOfPage(int startOfPage) {
                    this.startOfPage = startOfPage;
                }

                public int getTotalPageCount() {
                    return totalPageCount;
                }

                public void setTotalPageCount(int totalPageCount) {
                    this.totalPageCount = totalPageCount;
                }

                public int getTotalRowCount() {
                    return totalRowCount;
                }

                public void setTotalRowCount(int totalRowCount) {
                    this.totalRowCount = totalRowCount;
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
