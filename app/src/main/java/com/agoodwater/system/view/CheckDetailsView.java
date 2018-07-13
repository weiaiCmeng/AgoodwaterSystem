package com.agoodwater.system.view;

import com.agoodwater.system.bean.CheckDetailsBean;

/**
 * Created by shiqiang on 2017/2/22.
 */

public interface CheckDetailsView {

    String getUserId();

    String getStartTime();
    String getEndTime();

    //登录成功保存这个用户值
    void loginSuccess(CheckDetailsBean checkDetailsBean);
    void loginError(String error);
}
