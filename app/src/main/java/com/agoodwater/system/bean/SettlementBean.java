package com.agoodwater.system.bean;

import java.util.List;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/8/3 09:07
 */
public class SettlementBean {


    private List<CompanyendListBean> companyendList;

    public List<CompanyendListBean> getCompanyendList() {
        return companyendList;
    }

    public void setCompanyendList(List<CompanyendListBean> companyendList) {
        this.companyendList = companyendList;
    }

    public static class CompanyendListBean {
        /**
         * companyName : 百度
         * createDate : 1501644121000
         * createTime : 1501647910883
         * delFlag : 1
         * goodsName : 雀巢
         * id : 5
         * page : {"pageNo":1,"pageSize":10,"result":[],"startOfPage":0,"totalPageCount":0,"totalRowCount":0}
         * price : 220
         * tongNum : 10
         * type : 2
         * userName : 18813014231
         * waterNum : 15801502283
         */

        private String companyName;
        private long createDate;
        private long createTime;
        private int delFlag;
        private String goodsName;
        private int id;
        private PageBean page;
        private int price;
        private int tongNum;
        private int type;
        private String userName;
        private String waterNum;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
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

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getTongNum() {
            return tongNum;
        }

        public void setTongNum(int tongNum) {
            this.tongNum = tongNum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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
