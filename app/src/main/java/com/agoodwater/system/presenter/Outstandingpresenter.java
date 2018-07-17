package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.ConfirmOrderBean;
import com.agoodwater.system.bean.OutstandingBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.singletop.OrderMapSingleTop;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.view.OutstandingView;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class Outstandingpresenter implements OutstandingInfo{

    private OutstandingView outstandingView ;
    private String userId;
    private String orderNo;
    private String status;
    private String songNum;
    private Map<String, String> loginMap;
    private Map<String, String> confirmMap;

    @Inject
    public Outstandingpresenter(OutstandingView outstandingView) {
        this.outstandingView = outstandingView ;
    }


    @Override
    public void loginNet() {


        //联网请求数据

        userId = outstandingView.getUserId();


        loginMap = new ArrayMap<>();
        loginMap.put("userName" , userId);

        System.out.println(userId + "------------");


        UpdateFractory.getBuild()
                .name("outstandingCall")
                .map(loginMap)
                .build()

        .execute(new ResponseSubscriber<OutstandingBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(OutstandingBean outstandingBean) {

                int infoCode = outstandingBean.getInfoCode();

                if (infoCode == 200){

                    outstandingView.successNet(outstandingBean);
                }else{

                    outstandingView.error("服务器繁忙,请稍后重试!");
                }

            }
        });


    }

    @Override
    public void confirmNet() {


        //联网请求数据

         userId = outstandingView.getUserId();
        orderNo = outstandingView.getOrderNo();
        status = outstandingView.getStatus();
        songNum = outstandingView.getSongNum();


        System.out.println("userId" + userId + "====" + orderNo + "songNum" + songNum + "---" + status);
        confirmMap = new ArrayMap<>();
        confirmMap.put("userName" , userId);
        confirmMap.put("orderNo" , orderNo);
        confirmMap.put("status" , status);
        confirmMap.put("songNum" , songNum);


        OrderMapSingleTop.getOrderMapSingleTop().setMap(confirmMap);

        UpdateFractory.getBuild()
                .name("confirmOrderCall")
                .map(confirmMap)
                .build()

        .execute(new ResponseSubscriber<ConfirmOrderBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(ConfirmOrderBean confirmOrderBean) {

                int infoCode = confirmOrderBean.getInfoCode();

                if (infoCode == 200){

                    outstandingView.confirmOrderSuccess("订单已分配送水工");
                }else{

                    outstandingView.error("-1");
//                    outstandingView.error("服务器繁忙,请稍后重试!");
                }

            }
        });




    }
}
