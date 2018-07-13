package com.agoodwater.system.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.activity.BillingActivity;
import com.agoodwater.system.activity.CheckDetailsAcitivity;
import com.agoodwater.system.activity.CheckEmplyeeAcitivity;
import com.agoodwater.system.activity.CompanyActivity;
import com.agoodwater.system.activity.EmployeeManagActivity;
import com.agoodwater.system.activity.LoginActivity;
import com.agoodwater.system.activity.SongUdapteActivity;
import com.agoodwater.system.activity.TabDetailsActivity;
import com.agoodwater.system.bean.StoreBean;
import com.agoodwater.system.dialog.CommonDialog;
import com.agoodwater.system.presenter.StoreManagerPresenter;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.utils.TimeUtils;
import com.agoodwater.system.view.StoreView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class StoreManagementPage extends ViewPagerFragment implements StoreView {

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.tv_today_time)
    TextView tvTodayTime;
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
    @BindView(R.id.rl_belling)
    RelativeLayout rlBelling;
    @BindView(R.id.rl_employee_manager)
    RelativeLayout rlEmployeeManager;
    @BindView(R.id.rl_employee_count)
    RelativeLayout rlEmployeeCount;
    @BindView(R.id.cb_chose_sound)
    CheckBox cbChoseSound;
    @BindView(R.id.rl_ling_water)
    RelativeLayout rlLingWater;
    @BindView(R.id.ll_today_check)
    LinearLayout llTodayCheck;
    @BindView(R.id.tv_quit)
    TextView tvQuit;
    @BindView(R.id.tv_details)
    TextView tvDetails;
    @BindView(R.id.tv_servion_desc)
    TextView tvServionDesc;
    @BindView(R.id.rl_song_update)
    RelativeLayout rlSongUpdate;
    @BindView(R.id.rl_company)
    RelativeLayout rlCompany;
    Unbinder unbinder;
    private StoreManagerPresenter storemp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResourceId(), container, false);

        }
        setEventBus();
        ButterKnife.bind(this, rootView);
        return rootView;


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void onVisible() {
        updateNet();
    }

    private void updateNet() {

        boolean jpush = SpUtils.getBoolean(mainActivity, "jpush", true);
        cbChoseSound.setChecked(jpush);

        cbChoseSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    System.out.println("我是推送接受");
                    SpUtils.setBoolean(mainActivity, "jpush", true);

                } else {

                    jpushNonAccept();
                    System.out.println("我是不接受推送");


                }


            }
        });


        tvServionDesc.setText("当前版本:" + getVersion());
        //设置今天的日期

        tvTodayTime.setText(TimeUtils.ms2Time(System.currentTimeMillis()) + "(今天)");


        if (storemp == null) {
            storemp = new StoreManagerPresenter(this);
        }

        storemp.loginNet();


    }

    private void jpushNonAccept() {

        final CommonDialog.Builder builder = new CommonDialog.Builder(mainActivity);
//       final CommonDialog.Builder builder = (CommonDialog.Builder) DialogFactory.creatDialog(mainActivity, "common");

        builder.setTitle("警告")
                .setTitleColor(R.color.colorRed)
                .setMessage("关闭推送将不会响铃提醒订单!")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cbChoseSound.setChecked(true);
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cbChoseSound.setChecked(false);
                        System.out.println("我是不接受推送了....");
                        SpUtils.setBoolean(mainActivity, "jpush", false);
                        builder.hidden();
                    }
                })
                .create().show();

    }

    @OnClick({R.id.rl_check_details, R.id.rl_company ,  R.id.ll_today_check, R.id.rl_belling, R.id.rl_employee_manager, R.id.rl_employee_count, R.id.tv_quit, R.id.rl_song_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_check_details:
                //查看详情
            case R.id.ll_today_check:

                startActivity(CheckDetailsAcitivity.class);


                break;

            case R.id.rl_company:


                startActivity(CompanyActivity.class);

                break;

            case R.id.rl_belling:

                startActivity( BillingActivity.class);

                break;
            case R.id.rl_employee_manager:

                startActivity( EmployeeManagActivity.class);

                break;
            case R.id.rl_employee_count:

                startActivity( CheckEmplyeeAcitivity.class);

                break;

            case R.id.tv_quit:

                //退出按钮


                quitLogin();

                break;

            case R.id.rl_song_update:

                //送水工下载二维码

                startActivity(SongUdapteActivity.class);


                break;
        }
    }

    @Override
    public String getUserId() {
        return SpUtils.getString(mainActivity, "userName", "");
    }

    @Override
    public void loginSuccess(StoreBean storeBean) {

        tvStore.setText(storeBean.getData().getWaterName());

        tvAllMoney.setText(storeBean.getData().getMoney() + "");

        tvBucketNumber.setText(storeBean.getData().getTongNum() + "桶");

        tvHdMoney.setText(storeBean.getData().getOrderSize() + "单");


    }

    @Override
    public void loginError(String error) {

        MyToast.show(mainActivity, error);

    }

    private void quitLogin() {


        CommonDialog.Builder builder = new CommonDialog.Builder(mainActivity);
        builder.setTitle("请确认你的选择")
                .setTitleColor(R.color.colorRed)
                .setMessage("你是否确认退出?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //友盟统计账号的退出
//                        MobclickAgent.onProfileSignOff();

                        SpUtils.setString(mainActivity, "userName", "-1");
                        startActivity(new Intent(mainActivity, LoginActivity.class));
                        mainActivity.finish();

                    }
                })
                .create().show();

    }


    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public String getVersion() {
        try {
            PackageManager manager = mainActivity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mainActivity.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
