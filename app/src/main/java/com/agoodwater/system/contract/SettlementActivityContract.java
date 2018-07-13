package com.agoodwater.system.contract;

import com.agoodwater.system.bean.SettlementBean;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/8/2 17:48
 */
public interface SettlementActivityContract {
    interface Model {
    }

    interface View {
        String getwaterName();
        String  getUseName();

        void loginSuccess(SettlementBean settlementBean);
        void LoginError(String error);


    }

    interface Presenter {

        void loginNet();
    }
}
