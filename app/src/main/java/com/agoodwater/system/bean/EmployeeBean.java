package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/24.
 */
public class EmployeeBean {


    /**
     * message : 送水工查询成功
     * data : [{"id":75,"status":1,"user_mobile_phone":"135","waterNum":"admin","user_name":"王嘉浩","pis":null,"user_num":600111,"storeName":null,"submitStore":"xingfushuizhan","loginName":null,"passWord":"123456","page":{"totalPageCount":0,"totalRowCount":0,"result":[],"pageSize":10,"startOfPage":0,"pageNo":1},"delFlag":1,"createTime":1490335400641,"updateTime":null}]
     * infoCode : 200
     * success : 1
     */

    private String message;
    private int infoCode;
    private String success;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 75
         * status : 1
         * user_mobile_phone : 135
         * waterNum : admin
         * user_name : 王嘉浩
         * pis : null
         * user_num : 600111
         * storeName : null
         * submitStore : xingfushuizhan
         * loginName : null
         * passWord : 123456
         * page : {"totalPageCount":0,"totalRowCount":0,"result":[],"pageSize":10,"startOfPage":0,"pageNo":1}
         * delFlag : 1
         * createTime : 1490335400641
         * updateTime : null
         */

        private int id;
        private int status;
        private String user_mobile_phone;
        private String waterNum;
        private String user_name;
        private Object pis;
        private int user_num;
        private Object storeName;
        private String submitStore;
        private Object loginName;
        private String passWord;
        private PageBean page;
        private int delFlag;
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

        public String getUser_mobile_phone() {
            return user_mobile_phone;
        }

        public void setUser_mobile_phone(String user_mobile_phone) {
            this.user_mobile_phone = user_mobile_phone;
        }

        public String getWaterNum() {
            return waterNum;
        }

        public void setWaterNum(String waterNum) {
            this.waterNum = waterNum;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Object getPis() {
            return pis;
        }

        public void setPis(Object pis) {
            this.pis = pis;
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

        public String getSubmitStore() {
            return submitStore;
        }

        public void setSubmitStore(String submitStore) {
            this.submitStore = submitStore;
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
             * totalPageCount : 0
             * totalRowCount : 0
             * result : []
             * pageSize : 10
             * startOfPage : 0
             * pageNo : 1
             */

            private int totalPageCount;
            private int totalRowCount;
            private int pageSize;
            private int startOfPage;
            private int pageNo;
            private List<?> result;

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
