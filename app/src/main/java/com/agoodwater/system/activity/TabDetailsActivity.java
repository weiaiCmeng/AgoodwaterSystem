package com.agoodwater.system.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.TabFragmentAdapter;
import com.agoodwater.system.fragment.CompanyFragment;
import com.agoodwater.system.fragment.PersonalFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.TabLayout.GRAVITY_FILL;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/6/26 17:50
 */
public class TabDetailsActivity extends BaseActivity {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    private ArrayList<Fragment> fragments;
    private Fragment companyFragment;
    private Fragment personFragment;
    private ArrayList<String> strTotal;
    private TabFragmentAdapter tabFragmentAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tabdetails;
    }

    @Override
    protected void initView() {

        tvTitleName.setText("收益查询");
        tvRight.setVisibility(View.GONE);

        strTotal = new ArrayList<>();
        strTotal.add("个人订单");
        strTotal.add("公司订单");
        fragments = new ArrayList<Fragment>();
        personFragment = new PersonalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", strTotal.get(0));
        personFragment.setArguments(bundle);

        fragments.add(personFragment);

        companyFragment = new CompanyFragment();
        Bundle companybundle = new Bundle();
        companybundle.putString("text", strTotal.get(1));
        companyFragment.setArguments(companybundle);
        fragments.add(companyFragment);
        setPager();

    }

    private void setPager() {

        if (tabFragmentAdapter == null) {

            tabFragmentAdapter = new TabFragmentAdapter(fragments, strTotal, getSupportFragmentManager(), this);
            viewPager.setAdapter(tabFragmentAdapter);
        } else {
            tabFragmentAdapter.setNewAdapter(fragments, strTotal);
            tabFragmentAdapter.notifyDataSetChanged();
        }


        tablayout.setupWithViewPager(viewPager);

        tablayout.setTabGravity(GRAVITY_FILL);
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayout, 60, 60);
            }
        });
        tablayout.setTabTextColors(getResources().getColor(R.color.color5d5d5d), getResources().getColor(R.color.color3dceff));
    }

    @Override
    protected void initData() {

    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {

        finish();
    }
}
