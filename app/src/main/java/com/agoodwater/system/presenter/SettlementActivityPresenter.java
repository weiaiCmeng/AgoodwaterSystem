package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.CompanyBean;
import com.agoodwater.system.bean.SettlementBean;
import com.agoodwater.system.contract.SettlementActivityContract;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/8/2 17:48
 */
public class SettlementActivityPresenter implements SettlementActivityContract.Presenter {

    private SettlementActivityContract.View mView ;
    private Map<String, String> map;

    @Inject
    SettlementActivityPresenter(SettlementActivityContract.View mView){
        this.mView = mView ;
    }


    @Override
    public void loginNet() {

        map = new ArrayMap<>();
        map.put("waterNum" , mView.getwaterName());
        map.put("userName" , mView.getUseName());

        UpdateFractory.getBuild().name("settlementCall").map(map).build()
                .execute(new ResponseSubscriber<SettlementBean>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.LoginError("服务器繁忙,请您稍后重试!");
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(SettlementBean settlementBean) {

                        mView.loginSuccess(settlementBean);


                    }
                });






    }
}
