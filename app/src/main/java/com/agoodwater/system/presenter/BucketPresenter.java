package com.agoodwater.system.presenter;

import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.BucketCompletedBean;
import com.agoodwater.system.contract.BucketContract;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;

import java.util.Map;

/**
 * Created by shiqiang on 2017/5/5.
 */

public class BucketPresenter implements BucketContract.Presenter {

    private BucketContract.View mView ;
    private String userName;
    private String pageNum;

    public BucketPresenter(BucketContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loginNet() {

        userName = mView.getUserName();

        pageNum = mView.getPageNum();


       Map<String ,String> outstandingMap = new ArrayMap<>();
        outstandingMap.put("userName", userName);
        outstandingMap.put("pageNum", pageNum );



        UpdateFractory.getBuild()
                .name("bucketCompletedCall")
                .map(outstandingMap)
                .build()
                .execute(new ResponseSubscriber<BucketCompletedBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                       mView.error("");
                    }

                    @Override
                    public void onSuccess(BucketCompletedBean completedOrderBean) {

                        mView.updateNet(completedOrderBean);


                    }
                });




    }
}
