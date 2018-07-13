package com.agoodwater.system.presenter;

/**
 * Created by shiqiang on 2017/2/22.
 */

public interface OutstandingInfo {

    //进入的时候进入联网
    void loginNet();

    //提交订单的时候:分配送水工
    void confirmNet();

}
