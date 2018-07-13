package com.agoodwater.system.bean;

import java.util.List;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/8/2 15:04
 */
public class CompanyBean {

    /**
     * companyAccountList : [{"companyAddress":"百度创客","companyName":"百度","companyPhone":"4102145313","createTime":1500348354000,"delFlag":1,"endDate":1504195200000,"goodsId":9,"goodsName":"景田，娃哈哈","id":4,"monthlydemand":"500以上","name":"闫宇","onedemand":"20-50","page":{"pageNo":1,"pageSize":10,"result":[],"startOfPage":0,"totalPageCount":0,"totalRowCount":0},"piaoName":"雀巢","piaoNum":40,"pwd":"123456","receiptType":"一般发票","songNum":"0","tempNum":40,"tranType":2,"useNum":0,"userName":"18813014231","userPrice":22,"userTempNum":40,"userTong":40,"waterName":"水多多","waterNum":"15801502283","waterPrice":20,"waterTong":0},{"companyAddress":"测试地址","companyName":"测试","companyPhone":"101231030","createTime":1501558959000,"delFlag":1,"endDate":1504195200000,"goodsId":6,"goodsName":"景田","id":7,"monthlydemand":"100","name":"测试人","onedemand":"5","page":{"pageNo":1,"pageSize":10,"result":[],"startOfPage":0,"totalPageCount":0,"totalRowCount":0},"piaoName":"景田","piaoNum":100,"pwd":"ceshi","receiptType":"专用发票","sex":1,"songNum":"0","tempNum":100,"tranType":1,"useNum":0,"userName":"18612595913","userPrice":20,"userTempNum":110,"userTong":21,"waterName":"水多多","waterNum":"15801502283","waterPrice":18,"waterTong":10}]
     * success : true
     */

    private boolean success;
    private List<CompanyAccountListBean> companyAccountList;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<CompanyAccountListBean> getCompanyAccountList() {
        return companyAccountList;
    }

    public void setCompanyAccountList(List<CompanyAccountListBean> companyAccountList) {
        this.companyAccountList = companyAccountList;
    }

    public static class CompanyAccountListBean {
        /**
         * companyAddress : 百度创客
         * companyName : 百度
         * companyPhone : 4102145313
         * createTime : 1500348354000
         * delFlag : 1
         * endDate : 1504195200000
         * goodsId : 9
         * goodsName : 景田，娃哈哈
         * id : 4
         * monthlydemand : 500以上
         * name : 闫宇
         * onedemand : 20-50
         * page : {"pageNo":1,"pageSize":10,"result":[],"startOfPage":0,"totalPageCount":0,"totalRowCount":0}
         * piaoName : 雀巢
         * piaoNum : 40
         * pwd : 123456
         * receiptType : 一般发票
         * songNum : 0
         * tempNum : 40
         * tranType : 2
         * useNum : 0
         * userName : 18813014231
         * userPrice : 22
         * userTempNum : 40
         * userTong : 40
         * waterName : 水多多
         * waterNum : 15801502283
         * waterPrice : 20
         * waterTong : 0
         * sex : 1
         */

        private String companyAddress;
        private String companyName;
        private String companyPhone;
        private long createTime;
        private int delFlag;
        private long endDate;
        private int goodsId;
        private String goodsName;
        private int id;
        private String monthlydemand;
        private String name;
        private String onedemand;
        private PageBean page;
        private String piaoName;
        private int piaoNum;
        private String pwd;
        private String receiptType;
        private String songNum;
        private int tempNum;
        private int tranType;
        private int useNum;
        private String userName;
        private int userPrice;
        private int userTempNum;
        private int userTong;
        private String waterName;
        private String waterNum;
        private int waterPrice;
        private int waterTong;
        private int sex;

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

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

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMonthlydemand() {
            return monthlydemand;
        }

        public void setMonthlydemand(String monthlydemand) {
            this.monthlydemand = monthlydemand;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOnedemand() {
            return onedemand;
        }

        public void setOnedemand(String onedemand) {
            this.onedemand = onedemand;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public String getPiaoName() {
            return piaoName;
        }

        public void setPiaoName(String piaoName) {
            this.piaoName = piaoName;
        }

        public int getPiaoNum() {
            return piaoNum;
        }

        public void setPiaoNum(int piaoNum) {
            this.piaoNum = piaoNum;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getReceiptType() {
            return receiptType;
        }

        public void setReceiptType(String receiptType) {
            this.receiptType = receiptType;
        }

        public String getSongNum() {
            return songNum;
        }

        public void setSongNum(String songNum) {
            this.songNum = songNum;
        }

        public int getTempNum() {
            return tempNum;
        }

        public void setTempNum(int tempNum) {
            this.tempNum = tempNum;
        }

        public int getTranType() {
            return tranType;
        }

        public void setTranType(int tranType) {
            this.tranType = tranType;
        }

        public int getUseNum() {
            return useNum;
        }

        public void setUseNum(int useNum) {
            this.useNum = useNum;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserPrice() {
            return userPrice;
        }

        public void setUserPrice(int userPrice) {
            this.userPrice = userPrice;
        }

        public int getUserTempNum() {
            return userTempNum;
        }

        public void setUserTempNum(int userTempNum) {
            this.userTempNum = userTempNum;
        }

        public int getUserTong() {
            return userTong;
        }

        public void setUserTong(int userTong) {
            this.userTong = userTong;
        }

        public String getWaterName() {
            return waterName;
        }

        public void setWaterName(String waterName) {
            this.waterName = waterName;
        }

        public String getWaterNum() {
            return waterNum;
        }

        public void setWaterNum(String waterNum) {
            this.waterNum = waterNum;
        }

        public int getWaterPrice() {
            return waterPrice;
        }

        public void setWaterPrice(int waterPrice) {
            this.waterPrice = waterPrice;
        }

        public int getWaterTong() {
            return waterTong;
        }

        public void setWaterTong(int waterTong) {
            this.waterTong = waterTong;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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
