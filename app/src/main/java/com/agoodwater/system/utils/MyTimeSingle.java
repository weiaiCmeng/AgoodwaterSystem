package com.agoodwater.system.utils;

import static com.umeng.analytics.pro.x.P;

/**
 * 类描述：用于收益查询下时间的单例
 * 创建人： 史强
 * 创建时间:2017/6/27 13:59
 */
public class MyTimeSingle {

    private String time;
    private volatile static MyTimeSingle myTimeSingle;

    private MyTimeSingle() {
    }

    public static synchronized MyTimeSingle getTimeSingle(){

        if (myTimeSingle == null){
            synchronized (MyTimeSingle.class){

                if (myTimeSingle == null){
                    myTimeSingle = new MyTimeSingle();
                }
            }
        }
        return myTimeSingle ;

    }

    public void setTime(String time){

        this.time = time ;

    }

    public String getTime(){

        return time ;

    }





}
