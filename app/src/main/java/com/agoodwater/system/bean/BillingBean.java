package com.agoodwater.system.bean;

import java.util.List;

/**
 * Created by shiqiang on 2017/3/27.
 */

public class BillingBean {

    /**
     * message : 账户统计
     * data : {"waterAccountList":[{"name":null,"id":67,"startTime":null,"status":2,"endTime":null,"createTime":1491978397000,"temp":null,"weekDay":"周三","waterNum":"admin","id2":0,"pr2":0,"price":80,"id3":1,"pr3":20,"id4":0,"pr4":0,"id5":1,"pr5":20,"id6":2,"pr6":40,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":4},{"name":null,"id":41,"startTime":null,"status":2,"endTime":null,"createTime":1491791733000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":40,"startTime":null,"status":2,"endTime":null,"createTime":1491791734000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":39,"startTime":null,"status":2,"endTime":null,"createTime":1491791728000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":38,"startTime":null,"status":2,"endTime":null,"createTime":1491791728000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":37,"startTime":null,"status":2,"endTime":null,"createTime":1491791748000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":36,"startTime":null,"status":2,"endTime":null,"createTime":1491791748000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":35,"startTime":null,"status":2,"endTime":null,"createTime":1491791749000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":34,"startTime":null,"status":2,"endTime":null,"createTime":1491791749000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":33,"startTime":null,"status":2,"endTime":null,"createTime":1491791750000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0}],"weekNowMoney":80,"weekBeforeMoney":0}
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
         * waterAccountList : [{"name":null,"id":67,"startTime":null,"status":2,"endTime":null,"createTime":1491978397000,"temp":null,"weekDay":"周三","waterNum":"admin","id2":0,"pr2":0,"price":80,"id3":1,"pr3":20,"id4":0,"pr4":0,"id5":1,"pr5":20,"id6":2,"pr6":40,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":4},{"name":null,"id":41,"startTime":null,"status":2,"endTime":null,"createTime":1491791733000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":40,"startTime":null,"status":2,"endTime":null,"createTime":1491791734000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":39,"startTime":null,"status":2,"endTime":null,"createTime":1491791728000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":38,"startTime":null,"status":2,"endTime":null,"createTime":1491791728000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":37,"startTime":null,"status":2,"endTime":null,"createTime":1491791748000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":36,"startTime":null,"status":2,"endTime":null,"createTime":1491791748000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":35,"startTime":null,"status":2,"endTime":null,"createTime":1491791749000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":34,"startTime":null,"status":2,"endTime":null,"createTime":1491791749000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0},{"name":null,"id":33,"startTime":null,"status":2,"endTime":null,"createTime":1491791750000,"temp":null,"weekDay":"周一","waterNum":"admin","id2":0,"pr2":0,"price":0,"id3":0,"pr3":0,"id4":0,"pr4":0,"id5":0,"pr5":0,"id6":0,"pr6":0,"id7":0,"pr7":0,"id8":0,"pr8":0,"idNum":0}]
         * weekNowMoney : 80
         * weekBeforeMoney : 0
         */

        private int weekNowMoney;
        private int weekBeforeMoney;
        private List<WaterAccountListBean> waterAccountList;

        public int getWeekNowMoney() {
            return weekNowMoney;
        }

        public void setWeekNowMoney(int weekNowMoney) {
            this.weekNowMoney = weekNowMoney;
        }

        public int getWeekBeforeMoney() {
            return weekBeforeMoney;
        }

        public void setWeekBeforeMoney(int weekBeforeMoney) {
            this.weekBeforeMoney = weekBeforeMoney;
        }

        public List<WaterAccountListBean> getWaterAccountList() {
            return waterAccountList;
        }

        public void setWaterAccountList(List<WaterAccountListBean> waterAccountList) {
            this.waterAccountList = waterAccountList;
        }

        public static class WaterAccountListBean {
            /**
             * name : null
             * id : 67
             * startTime : null
             * status : 2
             * endTime : null
             * createTime : 1491978397000
             * temp : null
             * weekDay : 周三
             * waterNum : admin
             * id2 : 0
             * pr2 : 0
             * price : 80
             * id3 : 1
             * pr3 : 20
             * id4 : 0
             * pr4 : 0
             * id5 : 1
             * pr5 : 20
             * id6 : 2
             * pr6 : 40
             * id7 : 0
             * pr7 : 0
             * id8 : 0
             * pr8 : 0
             * idNum : 4
             */

            private Object name;
            private int id;
            private Object startTime;
            private int status;
            private Object endTime;
            private long createTime;
            private Object temp;
            private String weekDay;
            private String waterNum;
            private int id2;
            private int pr2;
            private int price;
            private int id3;
            private int pr3;
            private int id4;
            private int pr4;
            private int id5;
            private int pr5;
            private int id6;
            private int pr6;
            private int id7;
            private int pr7;
            private int id8;
            private int pr8;
            private int idNum;

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getTemp() {
                return temp;
            }

            public void setTemp(Object temp) {
                this.temp = temp;
            }

            public String getWeekDay() {
                return weekDay;
            }

            public void setWeekDay(String weekDay) {
                this.weekDay = weekDay;
            }

            public String getWaterNum() {
                return waterNum;
            }

            public void setWaterNum(String waterNum) {
                this.waterNum = waterNum;
            }

            public int getId2() {
                return id2;
            }

            public void setId2(int id2) {
                this.id2 = id2;
            }

            public int getPr2() {
                return pr2;
            }

            public void setPr2(int pr2) {
                this.pr2 = pr2;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getId3() {
                return id3;
            }

            public void setId3(int id3) {
                this.id3 = id3;
            }

            public int getPr3() {
                return pr3;
            }

            public void setPr3(int pr3) {
                this.pr3 = pr3;
            }

            public int getId4() {
                return id4;
            }

            public void setId4(int id4) {
                this.id4 = id4;
            }

            public int getPr4() {
                return pr4;
            }

            public void setPr4(int pr4) {
                this.pr4 = pr4;
            }

            public int getId5() {
                return id5;
            }

            public void setId5(int id5) {
                this.id5 = id5;
            }

            public int getPr5() {
                return pr5;
            }

            public void setPr5(int pr5) {
                this.pr5 = pr5;
            }

            public int getId6() {
                return id6;
            }

            public void setId6(int id6) {
                this.id6 = id6;
            }

            public int getPr6() {
                return pr6;
            }

            public void setPr6(int pr6) {
                this.pr6 = pr6;
            }

            public int getId7() {
                return id7;
            }

            public void setId7(int id7) {
                this.id7 = id7;
            }

            public int getPr7() {
                return pr7;
            }

            public void setPr7(int pr7) {
                this.pr7 = pr7;
            }

            public int getId8() {
                return id8;
            }

            public void setId8(int id8) {
                this.id8 = id8;
            }

            public int getPr8() {
                return pr8;
            }

            public void setPr8(int pr8) {
                this.pr8 = pr8;
            }

            public int getIdNum() {
                return idNum;
            }

            public void setIdNum(int idNum) {
                this.idNum = idNum;
            }
        }
    }
}
