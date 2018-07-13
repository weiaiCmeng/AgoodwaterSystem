package com.agoodwater.system.view;

/**
 * Created by shiqiang on 2017/2/22.
 */

public interface LoginView {

    String getPhone();
    String getPwd();

    //登录成功保存这个用户值
    void loginSuccess(String success);
    void loginError(String error);
}
