package com.agoodwater.system.activity;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agoodwater.system.MainActivity;
import com.agoodwater.system.R;
import com.agoodwater.system.bean.ServionBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.NetUtils;
import com.agoodwater.system.utils.UpdateManager;
import com.agoodwater.system.utils.VersionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;



/**
 * Created by shiqiang on 2016/11/24.
 */

public class SplashActivity extends BaseActivity {


    //进入页面的时间
    private long initTime;
    //结束时的时间
    private long endTime;
    private StringBuffer stringBuffer;

    private boolean isFirst = true ;
    private Map<String, String> map;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        initTime = System.currentTimeMillis();
    }

    @Override
    protected void initData() {
        updateServer();
    }




    /**
     * 更新UI
     */
    private void updateServer() {

        //判断是否有网路
        if (NetUtils.isNetworkAvailable(SplashActivity.this)) {
//            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_LONG).show();

            //更新版本
            updateServionNet();

        } else {


            Toast.makeText(getApplicationContext(), "暂无可用网络,请您联网！", Toast.LENGTH_LONG).show();

            enitAcitivity();


        }




    }

    /**
     * 进入页面的设置
     */
    private void enitAcitivity() {

        endTime = System.currentTimeMillis();
        long startTime = endTime - initTime;

        System.out.println(startTime + "------------" + initTime  + "----" + endTime);
        if (startTime >= 2000){

            initMainActy();

        }else{

            //使用RxJava避免使用Handler导致的内存泄露,使用Handler需要使用静态内部类
            Observable<Long> timer = Observable.timer(1500, TimeUnit.MILLISECONDS);
            timer.observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    System.out.println(aLong + "heihei");
                    initMainActy();

                }
            });


        }


    }



    private void initMainActy() {

        startActivity(MainActivity.class);

        //两个参数分别表示进入的动画,退出的动画
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();


    }


    private void updateServionNet() {

        map = new HashMap<>();
        map.put("para" , "appSystem");

        UpdateFractory.getBuild()
                .name("updateServerCall")
                .map(map)
                .build()

        .execute(new ResponseSubscriber<ServionBean>() {
            @Override
            public void onFailure(Throwable e) {

                MyToast.show(SplashActivity.this,"服务器繁忙,请你稍后重试!");


                enitAcitivity();
            }

            @Override
            public void onSuccess(ServionBean servionBean) {

                final int servionNum = servionBean.getData().get(0).getServionNum();
                String servionDes = servionBean.getData().get(0).getServionDes();
                int currentVersionCode = VersionUtils.getCurrentVersionCode();
                final String url = servionBean.getData().get(0).getUrl();

                String descr = servionBean.getData().get(0).getDescr();

                System.out.println("我是更新的数据:" + descr + url);
//                String descr = "最新更新~1.嘿嘿~2.哈哈";
//                String descr = "发现新版本~1.呵呵~2.哈哈";
                stringBuffer = new StringBuffer();

                if(TextUtils.isEmpty(descr)){

                    stringBuffer.append("请你更新!");
                }else{

                    String[] split = descr.split("~");

                    for (int i = 0; i < split.length; i++) {

                        if (i == split.length -1){

                            stringBuffer.append(split[i]);

                        }else{
                            stringBuffer.append(split[i] + "\n");
                        }
                    }
                }



//                System.out.println("currentVersionCode" + currentVersionCode + "servionNum" + servionNum);


                System.out.println();

                if (currentVersionCode >= servionNum){
//                if (currentVersionCode < servionNum){

                    enitAcitivity();

                }else {


                    if (isFirst) {

                        isFirst = false;



                        /**
                         * 自定义dialog弹出toast
                         */
                        final Dialog confirmDialog = new Dialog(SplashActivity.this, R.style.Theme_AppCompat_Light_Dialog);
                        View dialogView = LayoutInflater.from(SplashActivity.this).inflate(R.layout.dialog_order_start, null);

                        TextView tvStartContent = (TextView) dialogView.findViewById(R.id.tv_start_content);
                        TextView tvDesc = (TextView) dialogView.findViewById(R.id.tv_desc);

                        tvStartContent.setText("服务器发现新版本：" + servionDes + "");
                        tvDesc.setVisibility(View.VISIBLE);
                        tvDesc.setText(stringBuffer);

                        /**
                         * 最终订单确认按钮
                         */
                        dialogView.findViewById(R.id.bt_start_confirm).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                //这里来检测版本是否需要更新
                                UpdateManager mUpdateManager = new UpdateManager(SplashActivity.this, url);
                                mUpdateManager.showNoticeDialog();

                                confirmDialog.dismiss();
                            }
                        });
                        /**
                         * 最终订单取消按钮
                         */
                        dialogView.findViewById(R.id.bt_start_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                initMainActy();

                                confirmDialog.dismiss();
                            }
                        });


                        confirmDialog.setContentView(dialogView);
                        confirmDialog.setCanceledOnTouchOutside(false);
                        confirmDialog.show();


                    }

                }

            }
        });

    }


}
