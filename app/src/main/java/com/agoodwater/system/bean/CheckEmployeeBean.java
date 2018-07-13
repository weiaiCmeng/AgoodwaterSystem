package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/27.
 */

public class CheckEmployeeBean {
    /**
     * message : 员工统计
     * data : {"time1":"2017-12-15","time2":"2017-12-15","disList":[{"id":409,"status":1,"session":1,"deposit":0,"user_num":600356,"storeName":null,"tongNum":1,"pis":null,"user_name":"刘建平","waterNum":"13811667658","danNum":1969,"loginName":null,"passWord":"123456","create_date":1503449618000,"submitStore":null,"user_mobile_phone":"18210190532","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":365,"status":1,"session":1,"deposit":0,"user_num":600321,"storeName":null,"tongNum":1,"pis":null,"user_name":"李亮","waterNum":"13811667658","danNum":1930,"loginName":null,"passWord":"123456","create_date":1500043147000,"submitStore":null,"user_mobile_phone":"15210306605","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":191,"status":1,"session":1,"deposit":0,"user_num":600166,"storeName":null,"tongNum":1,"pis":null,"user_name":"乔红亮","waterNum":"13811667658","danNum":3800,"loginName":null,"passWord":"654321","create_date":1502187628000,"submitStore":null,"user_mobile_phone":"13513921129","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":177,"status":1,"session":1,"deposit":0,"user_num":600152,"storeName":null,"tongNum":2,"pis":null,"user_name":"李志勇","waterNum":"13811667658","danNum":1340,"loginName":null,"passWord":"123456","create_date":1492412444000,"submitStore":null,"user_mobile_phone":"15210957621","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":176,"status":1,"session":1,"deposit":0,"user_num":600151,"storeName":null,"tongNum":5,"pis":null,"user_name":"李占领","waterNum":"13811667658","danNum":3702,"loginName":null,"passWord":"123456","create_date":1492412447000,"submitStore":null,"user_mobile_phone":"17703205621","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1}]}
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
         * time1 : 2017-12-15
         * time2 : 2017-12-15
         * disList : [{"id":409,"status":1,"session":1,"deposit":0,"user_num":600356,"storeName":null,"tongNum":1,"pis":null,"user_name":"刘建平","waterNum":"13811667658","danNum":1969,"loginName":null,"passWord":"123456","create_date":1503449618000,"submitStore":null,"user_mobile_phone":"18210190532","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":365,"status":1,"session":1,"deposit":0,"user_num":600321,"storeName":null,"tongNum":1,"pis":null,"user_name":"李亮","waterNum":"13811667658","danNum":1930,"loginName":null,"passWord":"123456","create_date":1500043147000,"submitStore":null,"user_mobile_phone":"15210306605","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":191,"status":1,"session":1,"deposit":0,"user_num":600166,"storeName":null,"tongNum":1,"pis":null,"user_name":"乔红亮","waterNum":"13811667658","danNum":3800,"loginName":null,"passWord":"654321","create_date":1502187628000,"submitStore":null,"user_mobile_phone":"13513921129","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":177,"status":1,"session":1,"deposit":0,"user_num":600152,"storeName":null,"tongNum":2,"pis":null,"user_name":"李志勇","waterNum":"13811667658","danNum":1340,"loginName":null,"passWord":"123456","create_date":1492412444000,"submitStore":null,"user_mobile_phone":"15210957621","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1},{"id":176,"status":1,"session":1,"deposit":0,"user_num":600151,"storeName":null,"tongNum":5,"pis":null,"user_name":"李占领","waterNum":"13811667658","danNum":3702,"loginName":null,"passWord":"123456","create_date":1492412447000,"submitStore":null,"user_mobile_phone":"17703205621","updateTime":null,"createTime":1513325112030,"page":{"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0},"delFlag":1}]
         */

        private String time1;
        private String time2;
        private List<DisListBean> disList;

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

        public List<DisListBean> getDisList() {
            return disList;
        }

        public void setDisList(List<DisListBean> disList) {
            this.disList = disList;
        }

        public static class DisListBean {
            /**
             * id : 409
             * status : 1
             * session : 1
             * deposit : 0
             * user_num : 600356
             * storeName : null
             * tongNum : 1
             * pis : null
             * user_name : 刘建平
             * waterNum : 13811667658
             * danNum : 1969
             * loginName : null
             * passWord : 123456
             * create_date : 1503449618000
             * submitStore : null
             * user_mobile_phone : 18210190532
             * updateTime : null
             * createTime : 1513325112030
             * page : {"result":[],"pageNo":1,"pageSize":10,"totalPageCount":0,"totalRowCount":0,"startOfPage":0}
             * delFlag : 1
             */

            private int id;
            private int status;
            private int session;
            private int deposit;
            private int user_num;
            private Object storeName;
            private int tongNum;
            private Object pis;
            private String user_name;
            private String waterNum;
            private int danNum;
            private Object loginName;
            private String passWord;
            private long create_date;
            private Object submitStore;
            private String user_mobile_phone;
            private Object updateTime;
            private long createTime;
            private PageBean page;
            private int delFlag;

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

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public int getUser_num() {
                return user_num;
            }

            public void setUser_num(int user_num) {
                this.user_num = user_num;
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

            public Object getPis() {
                return pis;
            }

            public void setPis(Object pis) {
                this.pis = pis;
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

            public int getDanNum() {
                return danNum;
            }

            public void setDanNum(int danNum) {
                this.danNum = danNum;
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

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public Object getSubmitStore() {
                return submitStore;
            }

            public void setSubmitStore(Object submitStore) {
                this.submitStore = submitStore;
            }

            public String getUser_mobile_phone() {
                return user_mobile_phone;
            }

            public void setUser_mobile_phone(String user_mobile_phone) {
                this.user_mobile_phone = user_mobile_phone;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public static class PageBean {
                /**
                 * result : []
                 * pageNo : 1
                 * pageSize : 10
                 * totalPageCount : 0
                 * totalRowCount : 0
                 * startOfPage : 0
                 */

                private int pageNo;
                private int pageSize;
                private int totalPageCount;
                private int totalRowCount;
                private int startOfPage;
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

                public int getStartOfPage() {
                    return startOfPage;
                }

                public void setStartOfPage(int startOfPage) {
                    this.startOfPage = startOfPage;
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
