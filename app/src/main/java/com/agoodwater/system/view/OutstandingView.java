package com.agoodwater.system.view;

import com.agoodwater.system.bean.OutstandingBean;

/**
 * Created by shiqiang on 2017/2/22.
 */

public interface OutstandingView {

    //得到用户名
    String getUserId();
    //得到送水订单编号
    String getOrderNo();
    //得到送水订单的标示
    String getStatus();

    //得到送水工的num
    String getSongNum();

    void successNet(OutstandingBean outstandingBean);

    void confirmOrderSuccess(String confirmSuccess);

    void error(String error);
}
