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
import com.agoodwater.system.adapter.SettlementAdapter;
import com.agoodwater.system.bean.SettlementBean;
import com.agoodwater.system.components.AppComponent;
import com.agoodwater.system.components.DaggerSettlementActivityComponent;
import com.agoodwater.system.contract.SettlementActivityContract;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.SettlementActivityModule;
import com.agoodwater.system.presenter.SettlementActivityPresenter;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.view.VpRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
import java.util.Map;
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
 * 创建时间:2017/8/2 17:45
 */
public class SettlementRecordActivity extends BaseActivity implements SettlementActivityContract.View{
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
    SettlementActivityPresenter presenter ;
    private String userName;
    private List<SettlementBean.CompanyendListBean> companyendList;
    private boolean isSuccess = true;
    private SettlementAdapter adapter;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_company;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

        userName = extras.getString("userName");
        System.out.println(userName);



    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSettlementActivityComponent.builder()
                .appComponent(appComponent)
                .settlementActivityModule(new SettlementActivityModule(this))
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void initListener() {
        //设置RecycleView 的布局格式
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);


    }

    @Override
    protected void initView() {

        tvRight.setVisibility(View.GONE);
        tvTitleName.setText("结算记录");
        tvNoOrder.setText("暂无结算记录");
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



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public String getwaterName() {

        return SpUtils.getString(this, "userName", "");
    }

    @Override
    public String getUseName() {

        return userName ;

    }

    @Override
    public void loginSuccess(SettlementBean settlementBean) {


        isSuccess = true;
        companyendList = settlementBean.getCompanyendList();

        if (companyendList.size() == 0){

            rlNoWater.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);

        }else{
            rlNoWater.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);

        }


        if (adapter == null) {

            adapter = new SettlementAdapter(this, companyendList);
            recyclerview.setAdapter(adapter);
        } else {

            adapter.setAdapter(companyendList);
            adapter.notifyDataSetChanged();

        }




    }

    @Override
    public void LoginError(String error) {

    }
}
