package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.CheckEmployeeBean;
import com.agoodwater.system.bean.CompanyBean;
import com.agoodwater.system.contract.CompanyActivityContract;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;

import java.util.Map;

import javax.inject.Inject;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/8/2 15:01
 */
public class CompanyActivityPresenter implements CompanyActivityContract.Presenter {

    private CompanyActivityContract.View mView ;
    private Map<String, String> map;

    @Inject
    CompanyActivityPresenter(CompanyActivityContract.View mView){
        this.mView = mView ;
    }


    @Override
    public void loginNet() {


        map = new ArrayMap<>();
        map.put("waterNum" , mView.getWaterNum());

        UpdateFractory.getBuild().name("companyCall").map(map).build()
                .execute(new ResponseSubscriber<CompanyBean>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.loginError("服务器繁忙,请您稍后重试!");
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(CompanyBean companyBean) {

                       mView.loginSuccess(companyBean);


                    }
                });

    }
}
