package com.agoodwater.system.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.QueryDetailsAdapter;
import com.agoodwater.system.bean.QueryDetailsBean;
import com.agoodwater.system.presenter.QueryDetailsPresenterImpl;
import com.agoodwater.system.utils.ConstantsUtil;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.view.VpRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import contract.QueryDetailsContract;
import dagger.Component;

/**
 * Created by shiqiang on 2017/4/21.
 */

public class QueryDetailsActivity extends BaseActivity implements QueryDetailsContract.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(R.id.recyclerview)
    VpRecyclerView recyclerview;
    @BindView(R.id.tv_no_order)
    TextView tvNoOrder;
    @BindView(R.id.rl_no_water)
    RelativeLayout rlNoWater;
    private String startTime;
    private String endTime;
    private String work;
    private QueryDetailsPresenterImpl queryDetailsPresenter;
    private int pager = 1;
    private List<QueryDetailsBean.DataBean.OrderListBean> orderList;
    private List<QueryDetailsBean.DataBean.OrderListBean> newOrderList;
    private QueryDetailsAdapter mAdapter;
    private boolean isSuccess = true;
    private String company;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_query;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

        startTime = extras.getString(ConstantsUtil.DETAILS_TIME);
        endTime = extras.getString(ConstantsUtil.END_TIME);
        work = extras.getString(ConstantsUtil.DETAILS_WORK);
        if (extras.getString(ConstantsUtil.DETAILS_COMPANY) == null){

            company = "-1" ;
        }else{

            company = extras.getString(ConstantsUtil.DETAILS_COMPANY);
        }

        System.out.println("This is a :" + startTime + work);
    }

    @Override
    protected void initView() {
        tvTitleName.setText("订单详情");
        tvNoOrder.setText("查询暂无订单信息");
        tvRight.setVisibility(View.GONE);

        newOrderList = new ArrayList();

    }

    @Override
    protected void initListener() {


        //设置RecycleView 的布局格式
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);


        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerview.refreshComplete();


                        //如果请求数据成功以后再次发起请求
                        //上拉更新1条

                        recyclerview.setLoadingMoreEnabled(true);
                        isSuccess = true;
                        newOrderList.clear();
                        pager = 1;
                        queryDetailsPresenter.loginNet();
                        mAdapter.notifyDataSetChanged();


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
                            queryDetailsPresenter.loginNet();


                        } else {

                            recyclerview.setLoadingMoreEnabled(false);
                            MyToast.show(QueryDetailsActivity.this, "没有更多数据了");

                        }

                    }
                }, 2 * 1000);
            }
        });

//        mAdapter = new StaggeredAdapter(mContext, mList);
//        mRecyclerview.setAdapter(mAdapter);


    }


    @Override
    protected void initData() {

        queryDetailsPresenter = new QueryDetailsPresenterImpl(this);
        queryDetailsPresenter.loginNet();


    }


    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getWork() {
        return work;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public int getPager() {
        return pager;
    }

    @Override
    public void loginSuceess(QueryDetailsBean queryDetailsBean) {


        isSuccess = true;

        orderList = queryDetailsBean.getData().getOrderList();

        newOrderList.addAll(orderList);

        System.out.println("This is pager" + pager + "--" + orderList.size() + "---" + newOrderList.size());

        if (newOrderList.size() == 0) {

            rlNoWater.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);

        }else{
            rlNoWater.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);


        }

        if (mAdapter == null) {

            mAdapter = new QueryDetailsAdapter(mContext, newOrderList);
            recyclerview.setAdapter(mAdapter);

        } else {
            mAdapter.setAdapter(newOrderList);
            mAdapter.notifyDataSetChanged();

        }

        mAdapter.setOnItemClickListener(new QueryDetailsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                System.out.println("我是点击" + position + newOrderList.get(position).getShip_mobile_phone());
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + newOrderList.get(position).getShip_mobile_phone()));
                phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(phoneIntent);

            }
        });

        if (orderList.size() < 10) {

            //不让在加载数据了
            isSuccess = false;


        }


    }

    @Override
    public void loginError(String error) {

        isSuccess = true;
        MyToast.show(this, error);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {

        finish();
    }

}
