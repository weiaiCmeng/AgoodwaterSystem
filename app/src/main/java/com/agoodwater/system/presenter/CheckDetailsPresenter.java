package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.CheckDetailsBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.view.CheckDetailsView;

import java.util.Map;


/**
 * Created by shiqiang on 2017/2/28.
 */

public class CheckDetailsPresenter implements StoreMangInfo {

    private CheckDetailsView storeView ;
    private Map<String, String> map;

    public CheckDetailsPresenter(CheckDetailsView storeView) {

        this.storeView = storeView ;




    }

    @Override
    public void loginNet() {

        map = new ArrayMap<>();
        map.put("userName" , storeView.getUserId());
        map.put("time1" , storeView.getStartTime());
        map.put("time2" , storeView.getEndTime());

        System.out.println("我的日期是联网: CheckDetailsCall" + storeView.getStartTime());

        UpdateFractory.getBuild()
                .name("CheckDetailsCall")
                .map(map)
                .build()
                .execute(new ResponseSubscriber<CheckDetailsBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(CheckDetailsBean checkDetailsBean) {

                        if (checkDetailsBean.getInfoCode() == 200){

                            storeView.loginSuccess(checkDetailsBean);
                        }else{

                            storeView.loginError("服务器繁忙,请您稍后重试!");
                        }


                    }
                });


    }
}
