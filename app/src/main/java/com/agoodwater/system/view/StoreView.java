package com.agoodwater.system.view;

import com.agoodwater.system.bean.StoreBean;

/**
 * Created by shiqiang on 2017/2/22.
 */

public interface StoreView {

    String getUserId();

    //登录成功保存这个用户值
    void loginSuccess(StoreBean storeBean);
    void loginError(String error);
}
