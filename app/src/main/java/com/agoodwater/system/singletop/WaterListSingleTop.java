package com.agoodwater.system.singletop;

import com.agoodwater.system.bean.OutstandingBean;

import java.util.List;

/**
 * Created by shiqiang on 2017/4/17.
 */

public class WaterListSingleTop {

    private static volatile WaterListSingleTop instance = null ;


    private List<OutstandingBean.DataBean.DisListBean> orderList ;
    private WaterListSingleTop(){}



    public static WaterListSingleTop getOrderMapSingleTop(){


        if (instance == null){

            synchronized (WaterListSingleTop.class){

                if (instance == null){

                    instance = new WaterListSingleTop();

                }


            }

        }

        return instance ;


    }



    public void setMap(List<OutstandingBean.DataBean.DisListBean> orderList ){

        this.orderList = orderList ;


    }

    public List<OutstandingBean.DataBean.DisListBean> getMap(){
        return orderList;


    }




}
