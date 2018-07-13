package com.agoodwater.system.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.activity.QueryDetailsActivity;
import com.agoodwater.system.utils.ConstantsUtil;
import com.agoodwater.system.utils.MyTimeSingle;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.utils.TimeUtils;
import com.agoodwater.system.view.MyMaterialCalendarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.agoodwater.system.App.mContext;


/**
 * Created by shiqiang on 2017/1/27.
 * <p>
 * Viewpager + Fragment情况下，fragment的生命周期因Viewpager的缓存机制而失去了具体意义
 * 该抽象类自定义一个新的回调方法，当fragment可见状态改变时会触发的回调方法，介绍看下面
 *
 * @see #onFragmentVisibleChange(boolean)
 */
public abstract class BaseDetailsFragment extends Fragment implements DayViewDecorator, OnDateSelectedListener {

    public Activity mainActivity;
    @BindView(R.id.tv_today_time)
    TextView tvTodayTime;
    @BindView(R.id.tv_details)
    TextView tvDetails;
    @BindView(R.id.rl_check_details)
    RelativeLayout rlCheckDetails;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.rl_stock)
    RelativeLayout rlStock;
    @BindView(R.id.tv_bucket_number)
    TextView tvBucketNumber;
    @BindView(R.id.rl_news)
    RelativeLayout rlNews;
    @BindView(R.id.tv_hd_money)
    TextView tvHdMoney;
    @BindView(R.id.rl_evaluate)
    RelativeLayout rlEvaluate;
    @BindView(R.id.ll_today_details)
    LinearLayout llTodayDetails;
    @BindView(R.id.lv_check)
    RecyclerView lvCheck;
    @BindView(R.id.tv_lishi)
    TextView tvLishi;
    @BindView(R.id.tv_taishi)
    TextView tvTaishi;
    @BindView(R.id.ll_no_time)
    LinearLayout llNoTime;
    @BindView(R.id.tv_dialog_cancel)
    TextView tvDialogCancel;
    @BindView(R.id.tv_dialog_confirm)
    TextView tvDialogConfirm;
    @BindView(R.id.calendarView)
    MyMaterialCalendarView widget;
    @BindView(R.id.ll_calendar)
    LinearLayout llCalendar;


    protected String mTime;
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View rootView;
    private Unbinder mUnbinder;
    protected String click_date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_check_details, container, false);

        }
        setEventBus();
        ButterKnife.bind(this, rootView);
        return rootView;


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("shiqiang", "setUserVisibleHint() -> isVisibleToUser: " + isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        /**
         * 如果Fragment不可见,会调取set方法,但是此时isFragmentVisible仍然为true,所以走下面的方法
         */
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }


    protected abstract int getLayoutResourceId();

    protected void setEventBus() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = getActivity();
        initVariable();



    }

    protected void initListener() {

        //设置RecycleView 的布局格式
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        lvCheck.setLayoutManager(manager);

        widget.setOnDateChangedListener(this);
        //调用更改颜色变化的提交
        widget.invalidateDecorators();
        //将状态改变的监听加入到控件中
        widget.addDecorator(this);

        if (mTime == null){

            mTime = TimeUtils.ms2Time(System.currentTimeMillis());
            tvTodayTime.setText(mTime + "(今天)");
            MyTimeSingle.getTimeSingle().setTime(mTime);
        }else{


            if (MyTimeSingle.getTimeSingle().getTime().equals(TimeUtils.ms2Time(System.currentTimeMillis()))){

                tvTodayTime.setText(MyTimeSingle.getTimeSingle().getTime() + "(今天)");
            }else {
                tvTodayTime.setText(MyTimeSingle.getTimeSingle().getTime());

            }

        }


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        Log.w("shiqiang", "onFragmentVisibleChange -> isVisible: " + isVisible);

        if (isVisible) {
            onVisible();
        } else {

            onGone();

        }
    }

    private void onGone() {

    }

    protected abstract void onVisible();


    @Override
    public void onDestroy() {
        super.onDestroy();

        unEventBus();
    }

    protected void unEventBus() {

    }

    protected void setButtonVisible() {

        llNoTime.setVisibility(View.VISIBLE);
        llCalendar.setVisibility(View.GONE);
    }

    protected void setCalendarVisible() {

        llNoTime.setVisibility(View.GONE);
        llCalendar.setVisibility(View.VISIBLE);


    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        mainActivity.startActivity(new Intent(mainActivity, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mainActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mainActivity.startActivity(intent);
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        mainActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        mainActivity.startActivityForResult(intent, requestCode);
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        long longTime = TimeUtils.getLongTime("2017-03-22");


        return day.getDate().getTime() > System.currentTimeMillis() || day.getDate().getTime() < longTime;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        click_date = TimeUtils.getDataToString((Date) date.getDate());
    }



}
