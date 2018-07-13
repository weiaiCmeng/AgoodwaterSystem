package com.agoodwater.system.contract;

import com.agoodwater.system.bean.WaterCompletedBean;

/**
 * Created by shiqiang on 2017/5/5.
 */

public interface WaterContract {

    interface View {

        String getUserName();
        String getPageNum();
        String getOrderNo();
        String getStatus();
        String getSongNum();

        void updateNet(WaterCompletedBean completedOrderBean);
        void confirmOrder(String confirmSuccess);

        void error(String error);


    }

    interface Presenter {

        void loginNet();
        void confirmWater();


    }
}
