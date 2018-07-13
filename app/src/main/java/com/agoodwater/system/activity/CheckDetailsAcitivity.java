package com.agoodwater.system.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.CheCkDetailAdapter;
import com.agoodwater.system.bean.CheckDetailsBean;
import com.agoodwater.system.contract.BucketContract;
import com.agoodwater.system.presenter.CheckDetailsPresenter;
import com.agoodwater.system.presenter.StoreMangInfo;
import com.agoodwater.system.utils.ConstantsUtil;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.utils.TimeUtils;
import com.agoodwater.system.view.CheckDetailsView;
import com.agoodwater.system.view.MyMaterialCalendarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.agoodwater.system.R.id.iv_back;
import static com.agoodwater.system.R.id.tv_today_time;
import static com.agoodwater.system.utils.MyToast.show;


/**
 * 送水详情
 * Created by shiqiang on 2017/3/22.
 */

public class CheckDetailsAcitivity extends BaseActivity implements CheckDetailsView, DayViewDecorator, OnDateSelectedListener {


    @BindView(tv_today_time)
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
    @BindView(R.id.lv_check)
    RecyclerView lvCheck;
    @BindView(R.id.tv_dialog_cancel)
    TextView tvDialogCancel;
    @BindView(R.id.tv_dialog_confirm)
    TextView tvDialogConfirm;
    @BindView(R.id.calendarView)
    MyMaterialCalendarView widget;
    @BindView(R.id.ll_calendar)
    LinearLayout llCalendar;
    @BindView(R.id.ll_no_time)
    LinearLayout llNoTime;
    @BindView(R.id.ll_today_details)
    LinearLayout llTodayDetails;
    @BindView(R.id.tv_lishi)
    TextView tvLishi;
    @BindView(R.id.tv_taishi)
    TextView tvTaishi;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_end_details)
    TextView tvEndDetails;
    @BindView(R.id.rl_end_details)
    RelativeLayout rlEndDetails;

    private StoreMangInfo checkDetailsPresenter;
    private List<CheckDetailsBean.DataBean.SalesDetileListBean> goodList;
    private CheCkDetailAdapter checkAdapter;
    private String click_date;

    //是否是第一次进入
    private boolean isCalendar = true;

    //是否联网成功
    private boolean isNet = true;


    private String startTime;
    private String endTime;


    private int CLICKON_LOGO = 0;
    private final int START_TIME = 1;
    private final int END_TIME = 2;

    /**
     * 判断点击初始时间跟结束时间改变的状态码
     */
    private int click_now = 0;

    private Handler mhandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case R.id.rl_starttime:
                case R.id.rl_endtime:
                    //更新你相应的UI
                    setCalendarVisible();
                    break;
            }
        }
    };


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_check_details;
    }

    @Override
    protected void initView() {

        tvTitleName.setText("送水详情");
        tvRight.setVisibility(View.GONE);


        startTime = TimeUtils.ms2Time(System.currentTimeMillis());
        endTime = TimeUtils.ms2Time(System.currentTimeMillis());

        tvTodayTime.setText(startTime);

        tvEndTime.setText(endTime);
    }

    @Override
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

    }

    @Override
    protected void initData() {

        checkDetailsPresenter = new CheckDetailsPresenter(this);

        checkDetailsPresenter.loginNet();


    }


    @Override
    public String getUserId() {
        return SpUtils.getString(this, "userName", "");
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
    public void loginSuccess(CheckDetailsBean checkDetailsBean) {

        isNet = true;
        tvAllMoney.setText(checkDetailsBean.getData().getMoney() + "");
        tvBucketNumber.setText(checkDetailsBean.getData().getTongNum() + "桶");
        tvHdMoney.setText(checkDetailsBean.getData().getOrderSize() + "单");



        if (goodList != null) {
            goodList.clear();
        }
        goodList = checkDetailsBean.getData().getSalesDetileList();

        if (goodList.size() != 0) {


            if (isCalendar) {

                checkAdapter = new CheCkDetailAdapter(mContext, goodList);
//                checkAdapter = new CheckDetailsListView(this, goodList);
                lvCheck.setAdapter(checkAdapter);
            } else {

                System.out.println("我是更新");
                checkAdapter.setAdapter(goodList);
                checkAdapter.notifyDataSetChanged();
                setButtonVisible();

            }

        } else {

            if (isCalendar) {

                checkAdapter = new CheCkDetailAdapter(mContext, goodList);
                lvCheck.setAdapter(checkAdapter);
            } else {

                System.out.println("我是更新");
                checkAdapter.setAdapter(goodList);
                checkAdapter.notifyDataSetChanged();
                setButtonVisible();

            }

            show(this, "暂无查询信息");
        }

    }

    @Override
    public void loginError(String error) {

        isNet = true;
    }

    @OnClick({R.id.rl_check_details,R.id.rl_end_details, R.id.tv_dialog_cancel, R.id.tv_dialog_confirm, R.id.ll_today_details, iv_back})
    public void onClick(View view) {
        switch (view.getId()) {

            case iv_back:

                finish();
                break;

            case R.id.ll_today_details:

                Bundle bundle = new Bundle();
                bundle.putString(ConstantsUtil.DETAILS_TIME, startTime);
                bundle.putString(ConstantsUtil.END_TIME, endTime);
                //查询全部员工为0
                bundle.putString(ConstantsUtil.DETAILS_WORK, "0");
                bundle.putString(ConstantsUtil.DETAILS_COMPANY, "-1");

                startActivity(QueryDetailsActivity.class, bundle);

                break;


            case R.id.rl_check_details:

                //直接进入

                setCalendarVisible();

                //如果条目点击状态改变了就讲日历控件关闭在重新打开
                if (click_now == R.id.rl_endtime) {
                    sendMessageUI();


                } else {
                    //直接进入
                    setCalendarVisible();

                }
                //开始时间查询标志
                CLICKON_LOGO = START_TIME;
                click_now = R.id.rl_starttime;



                break;


            case R.id.rl_end_details:

                //结束日期的选择
                //直接进入
                //如果条目点击状态改变了就讲日历控件关闭在重新打开
                if (click_now == R.id.rl_starttime) {
                    //发送消息在主线程中更新UI
                    sendMessageUI();
                } else {
                    //直接进入
                    setCalendarVisible();
                }

//                //结束时间查询标志
                CLICKON_LOGO = END_TIME;
                click_now = R.id.rl_endtime;

                break;


            case R.id.tv_dialog_cancel:
                setButtonVisible();
                break;
            case R.id.tv_dialog_confirm:



//                tvDialogConfirm.setBackgroundColor(getResources().getColor(R.color.colorNormal));
                if (click_date == null) {
                    MyToast.show(this, "请选择日期");
                } else {

                    String str = getTimeStr();
                    if (CLICKON_LOGO == END_TIME) {
                        startTime = tvTodayTime.getText().toString();

                        //将时间转化为毫秒值
                        try {
                            // 我们使用dateformat可以把日期对象给格式化字符串，它也可 以把字符串类型的日期，给格式化成日期对象

                            long startTimeLong = TimeUtils.getLongTime(startTime);
//                        // 这里使用的自定义的格式化
//                        DateFormat df2 = new SimpleDateFormat("yyyy-mm-dd");
//                        Date date2 = df2.parse(startTimeStr);
//                        long startTime = date2.getTime();
//                        ToastUtils.showToast(this,date2+"");


                            long endTimeLong = TimeUtils.getLongTime(str);
//                        Log.e("xiaojun2222222222",startTime+"");
//                        Log.e("xiaojun333333333333",startTime+"");
                            if (endTimeLong < startTimeLong) {
                                MyToast.show(this, "结束日期不能小于初始日期");
                            } else {
                                //结束时间设置
                                setButtonVisible();

                                if (isNet) {
                                    isNet = false;
                                    isCalendar = false;
                                    tvEndTime.setText(str);
                                    endTime = str ;
                                    checkDetailsPresenter.loginNet();
                                } else {

                                    MyToast.show(this, "已提交请求,正在更新");

                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                    //根据不同的标始设置不同的时间
                    if (CLICKON_LOGO == START_TIME) {


                        endTime = tvEndTime.getText().toString();

                        //将时间转化为毫秒值
                        try {
                            // 我们使用dateformat可以把日期对象给格式化字符串，它也可 以把字符串类型的日期，给格式化成日期对象

                            long endTimeLong = TimeUtils.getLongTime(endTime);
//                        // 这里使用的自定义的格式化
//                        DateFormat df2 = new SimpleDateFormat("yyyy-mm-dd");
//                        Date date2 = df2.parse(startTimeStr);
//                        long startTime = date2.getTime();
//                        ToastUtils.showToast(this,date2+"");


                            long startTimeLong = TimeUtils.getLongTime(str);
//                        Log.e("xiaojun2222222222",startTime+"");
//                        Log.e("xiaojun333333333333",startTime+"");
                            if (endTimeLong < startTimeLong) {
                                MyToast.show(this, "初始日期不能大于结束日期");
                            } else {
                                //初始时间的设置
                                setButtonVisible();
                                //开始时间设置

                                if (isNet) {
                                    isNet = false;
                                    isCalendar = false;
                                    tvTodayTime.setText(str);
                                    startTime = str ;
                                    checkDetailsPresenter.loginNet();
                                } else {

                                    MyToast.show(this, "已提交请求,正在更新");

                                }


                            }
//                        System.out.println("转换之后的日期对象的字符串格式:" + df2.format(date2));
                            // 转换之后的日期对象的字符串格式:1997-12-11
                            // 转换之后的日期对象的字符串格式:1997-23-11 11:23:15
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                }




















//                tvDialogConfirm.setBackgroundColor(getResources().getColor(R.color.colorNormal));
                /*if (TextUtils.isEmpty(click_date)) {
                    show(this, "请选择日期");
                } else {

                    if (isNet) {


                        isNet = false;

                        isCalendar = false;
                        tvTodayTime.setText(click_date);
                        mTime = click_date;
                        checkDetailsPresenter.loginNet();
                        System.out.println("我的日期是" + mTime);
                    } else {

                        MyToast.show(this, "已提交请求,正在更新");

                    }


                }*/

                break;
        }
    }

    private void setButtonVisible() {

        llNoTime.setVisibility(View.VISIBLE);
        llCalendar.setVisibility(View.GONE);
    }

    private void setCalendarVisible() {

        llNoTime.setVisibility(View.GONE);
        llCalendar.setVisibility(View.VISIBLE);


    }


    /**
     * 发送消息更新UI
     */
    private void sendMessageUI() {
        //将全部隐藏
        setButtonVisible();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(300);
                //发送消息
                Message msg = Message.obtain();
                msg.what = R.id.rl_starttime;
                mhandler.sendMessage(msg);

            }
        }).start();
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


    /**
     * 将得到的日期格式化"YYYY-MM-dd"
     */

    @NonNull
    private String getTimeStr() {
        //将日期中的数字返回给textView 时间显示
        return click_date;
    }

}
