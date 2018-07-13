package com.agoodwater.system.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.BucketOrderAdapter;
import com.agoodwater.system.bean.BucketCompletedBean;
import com.agoodwater.system.contract.BucketContract;
import com.agoodwater.system.presenter.BucketPresenter;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiqiang on 2016/10/21.
 */
public class BucketFragment extends BasePagerFragment implements BucketContract.View , BucketOrderAdapter.OnRecyclerViewItemClickListener{

    private List<BucketCompletedBean.DataBean.EndTorderListBean> newWaterOrder;
    //    private CheckOrderBean.DataBean checkOrderDate;
    private StringBuffer sb;

    private int pager = 1;

    //记录是否联网成功的标示
    boolean isSuccess = true;

    private BucketPresenter bucketPresenter;
    private List<BucketCompletedBean.DataBean.EndTorderListBean> endTorderList;
    private BucketOrderAdapter mAdapter;

    public BucketFragment(Activity activity) {
        super(activity);
    }




    @Override
    public void initData() {

//        EventBus.getDefault().register(this);

        newWaterOrder = new ArrayList();

        System.out.println("我是已完成initData");

        tvNoOrder.setText("暂无已完成退桶!");



        //设置RecycleView 的布局格式
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);

//        bucketPresenter = new BucketPresenter(this);

        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerview.refreshComplete();


                        //如果请求数据成功以后再次发起请求
                        //上拉更新1条

//                        recyclerview.setLoadingMoreEnabled(true);
//                        isSuccess = true;
//                        newWaterOrder.clear();
//                        pager = 1;
//                        bucketPresenter.loginNet();
//                        mAdapter.notifyDataSetChanged();


                    }
                }, 2 * 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerview.loadMoreComplete();

                        if (isSuccess) {
                            pager++;
//                            bucketPresenter.loginNet();


                        } else {

                            recyclerview.setLoadingMoreEnabled(false);
                            MyToast.show(mActivity , "没有更多数据了");

                        }

                    }
                }, 2 * 1000);
            }
        });



//        bucketPresenter.loginNet();


    }


    @Override
    public void onClick(View v) {

        int tag = (int) v.getTag();

        switch (v.getId()) {
            case R.id.btn_confirm:


                break;


            case R.id.iv_phone_call:

                //用intent启动拨打电话
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + newWaterOrder.get(tag).getUserTp()));
                phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(phoneIntent);


                break;


        }


    }


    //得到弹出框的样式
    public void getBrandNumber(String reTgoods, String reTnum) {

        System.out.println(reTgoods + "nidaye" + reTnum);
        String[] splitGoods = reTgoods.split(",");
        String[] splitNums = reTnum.split(",");

        sb = new StringBuffer();
        if (!TextUtils.isEmpty(reTgoods)) {

            for (int i = 0; i < splitGoods.length; i++) {

                System.out.println(splitGoods[i] + "----" + splitNums[i] + "\n");
                sb.append(splitGoods[i] + "X" + splitNums[i] + "\n");


            }

            System.out.println(sb.toString() + "我的品牌家数量");

        } else {
            sb.append("此单无回桶...");
        }


        System.out.println(sb + "woshi");


    }


    @Override
    public String getUserName() {
        return SpUtils.getString(mActivity, "userName", "");
    }

    @Override
    public String getPageNum() {
        return pager + "";
    }

    @Override
    public void updateNet(BucketCompletedBean completedOrderBean) {

        //联网成功的标示

        isSuccess = true;

        endTorderList = completedOrderBean.getData().getEndTorderList();

        newWaterOrder.addAll(endTorderList);

        System.out.println("This is pager" + pager + "--" + endTorderList.size() + "---" + newWaterOrder.size());

        if (newWaterOrder.size() == 0) {

            rlNoWater.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);

        }else{
            rlNoWater.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);




        }

        if (mAdapter == null) {

            mAdapter = new BucketOrderAdapter(mActivity, newWaterOrder);
            recyclerview.setAdapter(mAdapter);

        } else {
            mAdapter.setAdapter(newWaterOrder);
            mAdapter.notifyDataSetChanged();

        }
        mAdapter.setOnItemClickListener(this);


        if (endTorderList.size() < 10) {

            //不让在加载数据了
            isSuccess = false;


        }






    }

    @Override
    public void error(String error) {

        MyToast.show(mActivity,error);
    }
}
