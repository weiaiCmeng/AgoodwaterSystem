package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.StoreBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.view.StoreView;

import java.util.Map;


/**
 * Created by shiqiang on 2017/2/28.
 */

public class StoreManagerPresenter implements StoreMangInfo {

    private StoreView storeView ;
    private Map<String, String> map;

    public StoreManagerPresenter(StoreView storeView) {

        this.storeView = storeView ;




    }

    @Override
    public void loginNet() {


        map = new ArrayMap<>();
        map.put("userName" , storeView.getUserId());

        UpdateFractory.getBuild()
                .name("storeCall")
                .map(map)
                .build()

        .execute(new ResponseSubscriber<StoreBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(StoreBean storeBean) {

                if (storeBean.getInfoCode() == 200){

                    storeView.loginSuccess(storeBean);
                }else{

                    storeView.loginError("服务器繁忙,请您稍后重试!");
                }


            }
        });


    }
}
