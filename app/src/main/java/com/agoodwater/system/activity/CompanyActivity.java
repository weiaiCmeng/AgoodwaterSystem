package com.agoodwater.system.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.CompanyAdapter;
import com.agoodwater.system.bean.CompanyBean;
import com.agoodwater.system.components.AppComponent;
import com.agoodwater.system.components.DaggerCompanyActivityComponent;
import com.agoodwater.system.contract.CompanyActivityContract;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.CompanyActivityModule;
import com.agoodwater.system.presenter.CompanyActivityPresenter;
import com.agoodwater.system.utils.ConstantsUtil;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.utils.TimeUtils;
import com.agoodwater.system.view.VpRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/27 12:16
 */
public class CompanyActivity extends BaseActivity implements CompanyActivityContract.View, CompanyAdapter.OnRecyclerViewItemClickListener {
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

    @Inject
    CompanyActivityPresenter presenter;
    private List<CompanyBean.CompanyAccountListBean> companyAccountList;
    private boolean isSuccess = true;
    private CompanyAdapter adapter;
    private String mTime;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_company;
    }

    @Override
    protected void initListener() {
        //设置RecycleView 的布局格式
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);


    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerCompanyActivityComponent.builder()
                .appComponent(appComponent)
                .companyActivityModule(new CompanyActivityModule(this))
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);


    }

    @Override
    protected void initView() {
        tvRight.setVisibility(View.GONE);
        tvTitleName.setText("公司用户");
        tvNoOrder.setText("暂无公司订单");


    }

    @Override
    protected void initData() {

        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {


                //使用RxJava避免使用Handler导致的内存泄露,使用Handler需要使用静态内部类
                Observable<Long> timer = Observable.timer(1500, TimeUnit.MILLISECONDS);
                timer.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                System.out.println(aLong + "heihei");
                                recyclerview.refreshComplete();


                                //如果请求数据成功以后再次发起请求
                                //上拉更新1条
                                recyclerview.setLoadingMoreEnabled(true);

                                if (isSuccess) {

                                    isSuccess = false;
                                    presenter.loginNet();
                                }
                            }
                        });

            }

            @Override
            public void onLoadMore() {

                //使用RxJava避免使用Handler导致的内存泄露,使用Handler需要使用静态内部类
                Observable<Long> timer = Observable.timer(2000, TimeUnit.MILLISECONDS);
                timer.observeOn(AndroidSchedulers.mainThread())
                        .filter(new Func1<Long, Boolean>() {
                            @Override
                            public Boolean call(Long aLong) {
                                recyclerview.loadMoreComplete();
                                isSuccess = true;
                                recyclerview.setLoadingMoreEnabled(false);

                                return false;

                            }
                        })

                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {

                            }
                        });

            }
        });


        presenter.loginNet();

    }


    @Override
    public String getWaterNum() {
        return SpUtils.getString(this, "userName", "");
    }

    @Override
    public void loginSuccess(CompanyBean companyBean) {

        isSuccess = true;

        companyAccountList = companyBean.getCompanyAccountList();

        if (companyAccountList.size() == 0){

            rlNoWater.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);

        }else{
            rlNoWater.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);

        }

        if (adapter == null) {


            adapter = new CompanyAdapter(this, companyAccountList);
            recyclerview.setAdapter(adapter);
        } else {

            adapter.setAdapter(companyAccountList);
            adapter.notifyDataSetChanged();

        }

        adapter.setOnItemClickListener(this);


    }

    @Override
    public void loginError(String error) {
        MyToast.show(this, error);
        isSuccess = true;
    }

    @Override
    public void onClick(View v) {

        //点击按钮的标识
        int tag = (int) v.getTag();

        switch (v.getId()) {


            case R.id.bt_settlement_record:

                System.out.println("我是结算记录");
                String userName = companyAccountList.get(tag).getUserName();
                Bundle bundle = new Bundle();
                bundle.putString("userName" , userName);
                startActivity(SettlementRecordActivity.class , bundle);


                break;

            case R.id.bt_water_record:
                System.out.println("我是送水记录");
                mTime = TimeUtils.ms2Time(System.currentTimeMillis());
                Bundle bundleRecord = new Bundle();
                bundleRecord.putString(ConstantsUtil.DETAILS_TIME, mTime);
                bundleRecord.putString(ConstantsUtil.END_TIME, mTime);
                //查询全部员工为0
                bundleRecord.putString(ConstantsUtil.DETAILS_WORK, "0");
                bundleRecord.putString(ConstantsUtil.DETAILS_COMPANY,companyAccountList.get(tag).getUserName());

                startActivity(QueryDetailsActivity.class, bundleRecord);

                break;


        }

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {

        finish();
    }
}
