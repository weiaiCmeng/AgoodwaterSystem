package com.agoodwater.system.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.view.VpRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 菜单详情页基类
 *
 * @author Kevin
 * @date 2015-10-18
 */
public abstract class BasePagerFragment {

    public Activity mActivity;
    public View mRootView;// 菜单详情页根布局

    public int page = 0;
    @BindView(R.id.recyclerview)
    VpRecyclerView recyclerview;
    @BindView(R.id.tv_no_order)
    TextView tvNoOrder;
    @BindView(R.id.rl_no_water)
    RelativeLayout rlNoWater;
    @BindView(R.id.fl_query_content)
    FrameLayout flQueryContent;


    public BasePagerFragment(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }


    // 初始化布局,必须子类实现
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_completed, null);
        ButterKnife.bind(this, view);
        return view;
    }

    // 初始化数据
    public void initData() {
    }


}
