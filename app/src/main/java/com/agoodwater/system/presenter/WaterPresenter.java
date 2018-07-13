package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.ConfirmOrderBean;
import com.agoodwater.system.bean.WaterCompletedBean;
import com.agoodwater.system.contract.WaterContract;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shiqiang on 2017/5/5.
 */

public class WaterPresenter implements WaterContract.Presenter {

    private WaterContract.View mView ;
    private Map<String, String> loginMap;
    private Map<String, String> confirmMap;
    private String userName;
    private String orderNo;
    private String songNum;
    private String status;

    public WaterPresenter(WaterContract.View mView) {
        this.mView = mView;
    }



    @Override
    public void loginNet() {

        userName = mView.getUserName();
        String pageNum = mView.getPageNum();


        loginMap = new ArrayMap<>();
        loginMap.put("userName", userName);
        loginMap.put("pageNum", pageNum);


        UpdateFractory.getBuild()
                .name("waterCompletedCall")
                .map(loginMap)
                .build()
                .execute(new ResponseSubscriber<WaterCompletedBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        mView.error("服务器繁忙,请稍后再试!");
                    }

                    @Override
                    public void onSuccess(WaterCompletedBean completedOrderBean) {

                        mView.updateNet(completedOrderBean);


                    }
                });





    }

    @Override
    public void confirmWater() {


        userName = mView.getUserName();

        orderNo = mView.getOrderNo();
        songNum = mView.getSongNum();
        status = mView.getStatus();

        confirmMap = new HashMap<>();
        confirmMap.put("userName" , userName);
        confirmMap.put("orderNo" , orderNo);
        confirmMap.put("status" , status);
        confirmMap.put("songNum" , songNum );

        UpdateFractory.getBuild()
                .name("confirmOrderCall")
                .map(confirmMap)
                .build()

                .execute(new ResponseSubscriber<ConfirmOrderBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        mView.error("服务器繁忙,请稍后重试!");
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(ConfirmOrderBean confirmOrderBean) {

                        int infoCode = confirmOrderBean.getInfoCode();

                        if (infoCode == 200){

                           mView.confirmOrder("订单已重新分配送水工");

                        }else{

                            mView.error("服务器繁忙,请稍后重试!");
                        }

                    }
                });



    }
}
