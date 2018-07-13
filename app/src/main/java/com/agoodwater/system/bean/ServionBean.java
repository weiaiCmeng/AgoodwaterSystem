package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/28.
 */

public class ServionBean {


    /**
     * message : 提交成功
     * data : [{"id":1,"url":"www.baidu.com","descr":"1","appSystem":null,"servionNum":1,"servionDes":"水站分单"}]
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
         * id : 1
         * url : www.baidu.com
         * descr : 1
         * appSystem : null
         * servionNum : 1
         * servionDes : 水站分单
         */

        private int id;
        private String url;
        private String descr;
        private Object appSystem;
        private int servionNum;
        private String servionDes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public Object getAppSystem() {
            return appSystem;
        }

        public void setAppSystem(Object appSystem) {
            this.appSystem = appSystem;
        }

        public int getServionNum() {
            return servionNum;
        }

        public void setServionNum(int servionNum) {
            this.servionNum = servionNum;
        }

        public String getServionDes() {
            return servionDes;
        }

        public void setServionDes(String servionDes) {
            this.servionDes = servionDes;
        }
    }
}
