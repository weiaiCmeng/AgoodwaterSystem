package com.agoodwater.system.contract;

import com.agoodwater.system.bean.BucketCompletedBean;

/**
 * Created by shiqiang on 2017/5/5.
 */

public interface BucketContract {


    interface View {

        String getUserName();
        String getPageNum();

        void updateNet(BucketCompletedBean completedOrderBean);
        void error(String error);
    }

    interface Presenter {

        void loginNet();
    }
}
