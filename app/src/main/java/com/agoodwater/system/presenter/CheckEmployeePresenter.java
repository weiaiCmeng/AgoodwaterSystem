package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.CheckEmployeeBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.view.CheckEmployView;

import java.util.Map;


/**
 * Created by shiqiang on 2017/2/28.
 */

public class CheckEmployeePresenter implements StoreMangInfo {

    private CheckEmployView storeView ;
    private Map<String, String> map;

    public CheckEmployeePresenter(CheckEmployView storeView) {

        this.storeView = storeView ;




    }

    @Override
    public void loginNet() {

        map = new ArrayMap<>();
        map.put("userName" , storeView.getUserId());
        map.put("time1" , storeView.getStartTime());
        map.put("time2" , storeView.getEndTime());


        UpdateFractory.getBuild().name("CheckEmployeeCall").map(map).build()
        .execute(new ResponseSubscriber<CheckEmployeeBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(CheckEmployeeBean checkDetailsBean) {

                if (checkDetailsBean.getInfoCode() == 200){

                    storeView.loginSuccess(checkDetailsBean);
                }else{

                    storeView.loginError("服务器繁忙,请您稍后重试!");
                }


            }
        });


    }
}
