package com.agoodwater.system.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.utils.ConstantsUtil;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.TimeUtils;
import com.agoodwater.system.view.MyMaterialCalendarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.agoodwater.system.utils.TimeUtils.getCurrentTime;

/**
 * Created by shiqiang on 2017/4/19.
 */

public class CheckTimeActivity extends BaseActivity implements OnDateSelectedListener, DayViewDecorator {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.rl_starttime)
    RelativeLayout rlStarttime;
    @BindView(R.id.tv_dialog_cancel)
    TextView tvDialogCancel;
    @BindView(R.id.tv_dialog_confirm)
    TextView tvDialogConfirm;
    @BindView(R.id.calendarView)
    MyMaterialCalendarView widget;
    @BindView(R.id.ll_calendar)
    LinearLayout llCalendar;
    @BindView(R.id.bt_check_time)
    Button btNext;
    @BindView(R.id.tv_title_query)
    TextView tvTitleQuery;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.rl_endtime)
    RelativeLayout rlEndtime;
    private String mTime;
    private String click_date;


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
        return R.layout.activity_check_time;
    }

    @Override
    protected void initView() {

        tvTitleName.setText("订单查询");
        tvRight.setVisibility(View.GONE);

        mTime = TimeUtils.ms2Time(System.currentTimeMillis());

        tvStarttime.setText(mTime);

        //得到当前时间初始化值
        tvEndtime.setText(mTime);


    }

    @Override
    protected void initListener() {

        widget.setOnDateChangedListener(this);
        //调用更改颜色变化的提交
        widget.invalidateDecorators();
        //将状态改变的监听加入到控件中
        widget.addDecorator(this);

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.iv_back, R.id.rl_starttime, R.id.tv_dialog_cancel, R.id.tv_dialog_confirm, R.id.bt_check_time , R.id.rl_endtime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:

                finish();
                break;


            case R.id.rl_starttime:

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

            case R.id.rl_endtime:

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
                        String startTimeStr = tvStarttime.getText().toString();

                        //将时间转化为毫秒值
                        try {
                            // 我们使用dateformat可以把日期对象给格式化字符串，它也可 以把字符串类型的日期，给格式化成日期对象

                            long startTime = TimeUtils.getLongTime(startTimeStr);
//                        // 这里使用的自定义的格式化
//                        DateFormat df2 = new SimpleDateFormat("yyyy-mm-dd");
//                        Date date2 = df2.parse(startTimeStr);
//                        long startTime = date2.getTime();
//                        ToastUtils.showToast(this,date2+"");


                            long endTime = TimeUtils.getLongTime(str);
//                        Log.e("xiaojun2222222222",startTime+"");
//                        Log.e("xiaojun333333333333",startTime+"");
                            if (endTime < startTime) {
                                MyToast.show(this, "结束日期不能小于初始日期");
                            } else {
                                //结束时间设置
                                setButtonVisible();
                                tvEndtime.setText(str);
                            }
//                        System.out.println("转换之后的日期对象的字符串格式:" + df2.format(date2));
                            // 转换之后的日期对象的字符串格式:1997-12-11
                            // 转换之后的日期对象的字符串格式:1997-23-11 11:23:15
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                    //根据不同的标始设置不同的时间
                    if (CLICKON_LOGO == START_TIME) {


                        String endTimeStr = tvEndtime.getText().toString();

                        //将时间转化为毫秒值
                        try {
                            // 我们使用dateformat可以把日期对象给格式化字符串，它也可 以把字符串类型的日期，给格式化成日期对象

                            long endTime = TimeUtils.getLongTime(endTimeStr);
//                        // 这里使用的自定义的格式化
//                        DateFormat df2 = new SimpleDateFormat("yyyy-mm-dd");
//                        Date date2 = df2.parse(startTimeStr);
//                        long startTime = date2.getTime();
//                        ToastUtils.showToast(this,date2+"");


                            long startTime = TimeUtils.getLongTime(str);
//                        Log.e("xiaojun2222222222",startTime+"");
//                        Log.e("xiaojun333333333333",startTime+"");
                            if (endTime < startTime) {
                                MyToast.show(this, "初始日期不能大于结束日期");
                            } else {
                                //初始时间的设置
                                setButtonVisible();
                                //开始时间设置
                                tvStarttime.setText(str);
                            }
//                        System.out.println("转换之后的日期对象的字符串格式:" + df2.format(date2));
                            // 转换之后的日期对象的字符串格式:1997-12-11
                            // 转换之后的日期对象的字符串格式:1997-23-11 11:23:15
                        } catch (Exception e) {
                            e.printStackTrace();
                        }













                    }

                }


                break;

            case R.id.bt_check_time:

                System.out.println(mTime + "----------------");

                //进入下一个页面,把时间以及查询标志发给服务器端
                //之前要判断一下时间的正确性
                String startTimeStr = tvStarttime.getText().toString();
                String endTimeStr = tvEndtime.getText().toString();

                System.out.println(startTimeStr + "---" + endTimeStr);
                long startTime = TimeUtils.getLongTime(startTimeStr);


                long endTime = TimeUtils.getLongTime(endTimeStr);

                System.out.println(startTime + "------------" + startTimeStr);
                System.out.println(endTime + "------------" + endTimeStr );
                if (endTime < startTime) {
                    MyToast.show(this, "结束日期不能小于初始日期");
                    return;
                }






                Bundle bundle = new Bundle();
                bundle.putString(ConstantsUtil.DETAILS_TIME, startTimeStr);
                bundle.putString(ConstantsUtil.END_TIME, endTimeStr);
                //查询全部员工为0
                bundle.putString(ConstantsUtil.DETAILS_WORK, "0");

                startActivity(QueryDetailsActivity.class, bundle);


                break;
        }
    }


    /**
     * 发送消息更新UI
     */
    private void sendMessageUI() {
        //将全部隐藏
        setButtonCalendar();
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


    /**
     * 设置日期button都不显示
     */
    private void setButtonCalendar() {
        btNext.setVisibility(View.GONE);
        llCalendar.setVisibility(View.GONE);
    }

    private void setButtonVisible() {

        btNext.setVisibility(View.VISIBLE);
        llCalendar.setVisibility(View.GONE);
    }

    private void setCalendarVisible() {

        btNext.setVisibility(View.GONE);
        llCalendar.setVisibility(View.VISIBLE);


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
