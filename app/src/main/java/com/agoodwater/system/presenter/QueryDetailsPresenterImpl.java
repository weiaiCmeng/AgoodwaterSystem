package com.agoodwater.system.presenter;


import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.QueryDetailsBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;

import java.util.Map;

import contract.QueryDetailsContract;

/**
* Created by shiqiang on 2017/04/21
*/

public class QueryDetailsPresenterImpl implements QueryDetailsContract.Presenter{
    private QueryDetailsContract.View mIview ;
    private Map<String, String> map;
    private String company;

    public QueryDetailsPresenterImpl(QueryDetailsContract.View iview) {
        this.mIview = iview ;
    }

    @Override
    public void loginNet() {

        company = mIview.getCompany();

        map = new ArrayMap<>();
        //这个是公司订单 为空就是原来的
//        map.put("companyUserName" , company);
        map.put("userName" , mIview.getUserName());
        map.put("time1" , mIview.getStartTime());
        map.put("time2" , mIview.getEndTime());
        map.put("songNum" , mIview.getWork());
        map.put("pageNum" , mIview.getPager() + "");
        //个人用户
        if (company == null || company.equals("-1")){

        }else{
            //公司用户
            map.put("companyUserName" , company) ;
        }

        System.out.println("userName:" + mIview.getUserName() + "songNum:" + mIview.getWork() + "---"
                + mIview.getStartTime() + "companyUserName:" + company );
        UpdateFractory.getBuild()
                .name("queryDetailsCall")
                .map(map)
                .build()
        .execute(new ResponseSubscriber<QueryDetailsBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(QueryDetailsBean queryDetailsBean) {

                if (queryDetailsBean.getInfoCode() == 200){

                    mIview.loginSuceess(queryDetailsBean);
                }else{

                    mIview.loginError("服务器繁忙,请您稍后重试!");
                }



            }
        });



    }
}