package com.agoodwater.system.view;

import com.agoodwater.system.bean.BillingBean;

/**
 * Created by shiqiang on 2017/3/27.
 */

public interface BillingView {

    String getUserId();
    String getPager();

    void loginSuccess(BillingBean billingBean);
    void loginError(String error);
}
