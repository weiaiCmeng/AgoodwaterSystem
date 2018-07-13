package com.agoodwater.system.bean;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/3 16:15
 */
public class LoginHttpsBean {

    /**
     * ret : 1
     * msg :
     * data : {"uid":"99","qq":false,"weixin":false,"token":"2ae0341cbd3498d15464882baa89f6e4","realname":"耿卫斌","mobile":"18511538679","headurl":"http://117.78.38.81/upload/71ef60c8ed9917bf61c21392f1819324"}
     * num :
     */

    private String ret;
    private String msg;
    private DataBean data;
    private String num;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public static class DataBean {
        /**
         * uid : 99
         * qq : false
         * weixin : false
         * token : 2ae0341cbd3498d15464882baa89f6e4
         * realname : 耿卫斌
         * mobile : 18511538679
         * headurl : http://117.78.38.81/upload/71ef60c8ed9917bf61c21392f1819324
         */

        private String uid;
        private boolean qq;
        private boolean weixin;
        private String token;
        private String realname;
        private String mobile;
        private String headurl;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public boolean isQq() {
            return qq;
        }

        public void setQq(boolean qq) {
            this.qq = qq;
        }

        public boolean isWeixin() {
            return weixin;
        }

        public void setWeixin(boolean weixin) {
            this.weixin = weixin;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }
    }
}
