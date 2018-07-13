package com.agoodwater.system.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.BillingAdapter;
import com.agoodwater.system.bean.BillingBean;
import com.agoodwater.system.presenter.BillingPresenter;
import com.agoodwater.system.presenter.StoreMangInfo;
import com.agoodwater.system.utils.ConstantsUtil;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.utils.TimeUtils;
import com.agoodwater.system.view.BillingView;
import com.agoodwater.system.view.MyPullUpListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by shiqiang on 2017/3/27.
 */

public class BillingActivity extends BaseActivity implements BillingView {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(R.id.tv_now_money)
    TextView tvNowMoney;
    @BindView(R.id.tv_before_money)
    TextView tvBeforeMoney;
    @BindView(R.id.lv_completed)
    MyPullUpListView lvBilling;
    @BindView(R.id.ptr_fresh)
    PtrClassicFrameLayout ptrFresh;
    private StoreMangInfo billingPresenter;


    //分页加载
    private int pager;

    //记录是否联网成功的标示
    boolean isSuccess = true;
    private List<BillingBean.DataBean.WaterAccountListBean> waterAccountList;

    //
    private List<BillingBean.DataBean.WaterAccountListBean> newWaterAccountList;
    private BillingAdapter adapter;



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_billing;
    }


    @Override
    protected void initView() {

        tvRight.setVisibility(View.GONE);
        tvTitleName.setText("账单结算");

        newWaterAccountList = new ArrayList<BillingBean.DataBean.WaterAccountListBean>();
    }

    @Override
    protected void initListener() {

        ptrFresh.setLastUpdateTimeRelateObject(this);
        ptrFresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                boolean flag = true;
                if (Build.VERSION.SDK_INT < 14) {
                    flag = lvBilling.getChildCount() > 0 && (lvBilling.getFirstVisiblePosition() > 0 || lvBilling.getChildAt(0).getTop() < lvBilling.getPaddingTop());
                } else {
                    flag = lvBilling.canScrollVertically(-1);
                }
                return !flag;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFresh.refreshComplete();

                        if (isSuccess){

                            //上拉更新1条
                            isSuccess = false ;
                            newWaterAccountList.clear();
                            adapter = null;

                        }
                        pager = 1;
                        if (billingPresenter != null){

                            billingPresenter.loginNet();
                        }
                    }
                }, 1000);

            }
        });

        lvBilling.initBottomView();
//        lvUserTickling.setAdapter(listViewAdapter);
        lvBilling.setMyPullUpListViewCallBack(new MyPullUpListView.MyPullUpListViewCallBack() {

            public void scrollBottomState() {


                if (isSuccess) {

                    isSuccess = false;
                    pager++;

                    if (billingPresenter != null){

                        billingPresenter.loginNet();
                    }

                    System.out.println("我的pager啊:" + pager);
                }

            }
        });


    }

    @Override
    protected void initData() {


        billingPresenter = new BillingPresenter(this);


        isSuccess = true;
        //上拉更新1条
        newWaterAccountList.clear();
        adapter = null;
        pager = 1;
        billingPresenter.loginNet();

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {

        finish();
    }

    @Override
    public String getUserId() {
        return SpUtils.getString(this, "userName", "");
    }

    @Override
    public String getPager() {

        System.out.println("我是getpager:" + pager);
        return pager + "";
    }

    @Override
    public void loginSuccess(BillingBean billingBean) {

        System.out.println("我是pager:" + pager);
        if (pager == 1) {
            newWaterAccountList.clear();
        }

        isSuccess = true ;

        tvNowMoney.setText("￥" + billingBean.getData().getWeekNowMoney());
        tvBeforeMoney.setText("￥" + billingBean.getData().getWeekBeforeMoney());



        waterAccountList = billingBean.getData().getWaterAccountList();
        newWaterAccountList.addAll(waterAccountList);

        if (adapter == null){

            adapter = new BillingAdapter(this, newWaterAccountList);

            lvBilling.setAdapter(adapter);

        }else{

          adapter.setListwaterOrder(newWaterAccountList);
          adapter.notifyDataSetChanged();



        }

        TextView tv = (TextView) lvBilling.findViewById(R.id.tv_many);
        if (waterAccountList.size() == 10) {

            tv.setText("正在加载中...");


        }

        if (waterAccountList == null || waterAccountList.size() < 10) {

            //不可再刷新
            isSuccess = false;
//                    lvCompleted.setFooterViewNull();


            tv.setText("没有更多数据了");

            System.out.println("pager" + pager);
            if (pager != 1) {
//                        MyToast.show(mainActivity,"我没有更多数据了");
            }

            System.out.println("wobei diao用了");


        }

        lvBilling.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("我点击的是" + TimeUtils.ms2Time(newWaterAccountList.get(position).getCreateTime()));

                Bundle bundle = new Bundle();
                bundle.putString(ConstantsUtil.DETAILS_TIME, TimeUtils.ms2Time(newWaterAccountList.get(position).getCreateTime()));
                bundle.putString(ConstantsUtil.END_TIME, TimeUtils.ms2Time(newWaterAccountList.get(position).getCreateTime()));
                //查询全部员工为0
                bundle.putString(ConstantsUtil.DETAILS_WORK, "0");
                bundle.putString(ConstantsUtil.DETAILS_COMPANY , "-1");

                startActivity(QueryDetailsActivity.class, bundle);



            }
        });




    }

    @Override
    public void loginError(String error) {

        if (pager != 1){
            pager-- ;

        }
        isSuccess = true ;

        MyToast.show(this,error);
    }
}
