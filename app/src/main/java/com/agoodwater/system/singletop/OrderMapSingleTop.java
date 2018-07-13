package com.agoodwater.system.singletop;

import java.util.Map;

/**
 * Created by shiqiang on 2017/4/17.
 */

public class OrderMapSingleTop {

    private static volatile OrderMapSingleTop instance = null ;


    private Map<String,String> orderMap ;
    private OrderMapSingleTop(){}



    public static OrderMapSingleTop getOrderMapSingleTop(){


        if (instance == null){

            synchronized (OrderMapSingleTop.class){

                if (instance == null){

                    instance = new OrderMapSingleTop();

                }


            }

        }

        return instance ;


    }



    public void setMap(Map<String,String> orderMap){

        this.orderMap = orderMap ;


    }

    public Map<String,String> getMap(){

        return orderMap;


    }




}
