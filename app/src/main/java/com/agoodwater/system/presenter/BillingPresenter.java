package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.BillingBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.view.BillingView;

import java.util.Map;


/**
 * Created by shiqiang on 2017/2/28.
 */

public class BillingPresenter implements StoreMangInfo {

    private BillingView mIview ;
    private Map<String, String> map;

    public BillingPresenter(BillingView mIview) {

        this.mIview = mIview ;




    }

    @Override
    public void loginNet() {

        map = new ArrayMap<>();
        map.put("userName" , mIview.getUserId());
        map.put("pageNum" , mIview.getPager());

         UpdateFractory.getBuild()
                .name("billingcall")
                .map(map)
                .build()
                .execute(new ResponseSubscriber<BillingBean>() {
                    @Override
                    public void onFailure(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(BillingBean billingBean) {

                        if (billingBean.getInfoCode() == 200){
                            mIview.loginSuccess(billingBean);
                        }else{
                            mIview.loginError("服务器繁忙,请您稍后重试!");
                        }



                    }
                });


    }
}
