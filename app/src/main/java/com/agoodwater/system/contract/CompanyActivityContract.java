package com.agoodwater.system.contract;

import com.agoodwater.system.bean.CompanyBean;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/8/2 15:01
 */
public interface CompanyActivityContract {
    interface Model {
    }

    interface View {
        String getWaterNum();

        void loginSuccess(CompanyBean companyBean);

        void loginError(String error);

    }

    interface Presenter {

        void loginNet();
    }
}
