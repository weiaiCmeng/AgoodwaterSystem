package com.agoodwater.system.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.agoodwater.system.R;
import com.agoodwater.system.activity.QueryDetailsActivity;
import com.agoodwater.system.adapter.CheCkDetailAdapter;
import com.agoodwater.system.bean.CheckDetailsBean;
import com.agoodwater.system.presenter.CheckDetailsPresenter;
import com.agoodwater.system.presenter.StoreMangInfo;
import com.agoodwater.system.utils.ConstantsUtil;
import com.agoodwater.system.utils.MyTimeSingle;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.utils.TimeUtils;
import com.agoodwater.system.view.CheckDetailsView;

import java.util.List;

import butterknife.OnClick;

import static com.agoodwater.system.App.mContext;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/6/26 17:55
 */
public class CompanyFragment extends BaseDetailsFragment implements CheckDetailsView {



    private StoreMangInfo checkDetailsPresenter;
    private List<CheckDetailsBean.DataBean.SalesDetileListBean> goodList;
    private CheCkDetailAdapter checkAdapter;

    //是否是第一次进入
    private boolean isCalendar = true;

    //是否联网成功
    private boolean isNet = true;


    @Override
    protected int getLayoutResourceId() {

        return R.layout.activity_check_details;
    }

    @Override
    protected void onVisible() {

        initListener();



        if (checkDetailsPresenter == null){

            checkDetailsPresenter = new CheckDetailsPresenter(this);
        }

        checkDetailsPresenter.loginNet();

    }


    @Override
    public String getUserId() {
        return SpUtils.getString(mainActivity, "userName", "");
    }

    @Override
    public String getStartTime() {
        return null;
    }

    @Override
    public String getEndTime() {
        return null;
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

            MyToast.show(mainActivity, "暂无查询信息");
        }




    }

    @Override
    public void loginError(String error) {
        isNet = true;
    }

    @OnClick({ R.id.rl_check_details, R.id.tv_dialog_cancel, R.id.tv_dialog_confirm, R.id.ll_today_details})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_today_details:

                Bundle bundle = new Bundle();
                bundle.putString(ConstantsUtil.DETAILS_TIME, MyTimeSingle.getTimeSingle().getTime());
                bundle.putString(ConstantsUtil.END_TIME, MyTimeSingle.getTimeSingle().getTime());
                //查询全部员工为0
                bundle.putString(ConstantsUtil.DETAILS_WORK, "0");

                startActivity(QueryDetailsActivity.class, bundle);

                break;


            case R.id.rl_check_details:

                //直接进入
                setCalendarVisible();

                break;


            case R.id.tv_dialog_cancel:
                setButtonVisible();
                break;
            case R.id.tv_dialog_confirm:


//                tvDialogConfirm.setBackgroundColor(getResources().getColor(R.color.colorNormal));
                if (TextUtils.isEmpty(click_date)) {
                    MyToast.show(mainActivity, "请选择日期");
                } else {

                    if (isNet) {


                        isNet = false;

                        isCalendar = false;
                        tvTodayTime.setText(click_date);
                        mTime = click_date;
                        MyTimeSingle.getTimeSingle().setTime(mTime);
                        checkDetailsPresenter.loginNet();
                        System.out.println("我的日期是" + mTime);
                    } else {

                        MyToast.show(mainActivity, "已提交请求,正在更新");

                    }


                }

                break;
        }
    }



}
