package com.agoodwater.system.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.MainActivity;
import com.agoodwater.system.R;
import com.agoodwater.system.adapter.OutstandingAdapter;
import com.agoodwater.system.bean.OutstandingBean;
import com.agoodwater.system.dialog.SingleSelectDialog;
import com.agoodwater.system.presenter.OutstandingInfo;
import com.agoodwater.system.presenter.Outstandingpresenter;
import com.agoodwater.system.receiver.OrderEvent;
import com.agoodwater.system.singletop.OrderMapSingleTop;
import com.agoodwater.system.singletop.WaterListSingleTop;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.NetUtils;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.view.OutstandingView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class OutstandingOrderPage extends ViewPagerFragment implements OutstandingView, OutstandingAdapter.Callback {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(R.id.lv_outstanding)
    ListView lvOutstanding;
    @BindView(R.id.ptr_fresh)
    PtrClassicFrameLayout ptrFresh;
    @BindView(R.id.tv_no_order)
    TextView tvNoOrder;
    @BindView(R.id.rl_no_water)
    RelativeLayout rlNoWater;
    private OutstandingInfo outstandingpresenter;

    private static final String TAG = "OutstandingOrderPage";
    private List<OutstandingBean.DataBean.OrderListBean> orderList;
    private List<OutstandingBean.DataBean.DisListBean> disWaterList;
    private OutstandingAdapter adapter;
    //订单号
    private String orderNo;

    //送水跟退桶的标示 1 为送水 0 为退桶
    private String status;
    //checkBox选择的标示:选中为当前送水工的id, 没有选择为-1 ;
    private int selectedPosition;

    private SingleSelectDialog singleSelectDialog;


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
        return R.layout.fragment_outstanding;
    }


    @Override
    protected void onVisible() {

        initDate();
    }

    @Override
    protected void setEventBus() {
        EventBus.getDefault().register(this);
    }

    private void initDate() {

        tvTitleName.setText("订单处理");
        tvRight.setVisibility(View.GONE);
        ivBack.setVisibility(View.GONE);

        if (outstandingpresenter == null){

            outstandingpresenter = new Outstandingpresenter(this);
        }



        ptrFresh.setLastUpdateTimeRelateObject(mainActivity);
        ptrFresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                boolean flag = true;
                if (Build.VERSION.SDK_INT < 14) {
                    flag = lvOutstanding.getChildCount() > 0 && (lvOutstanding.getFirstVisiblePosition() > 0 || lvOutstanding.getChildAt(0).getTop() < lvOutstanding.getPaddingTop());
                } else {
                    flag = lvOutstanding.canScrollVertically(-1);
                }
                return !flag;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFresh.refreshComplete();
                        outstandingpresenter.loginNet();
                    }
                }, 1000);

            }
        });


        outstandingpresenter.loginNet();


    }

    @Override
    public String getUserId() {
        return SpUtils.getString(mainActivity,"userName","");
    }

    @Override
    public String getOrderNo() {
        return orderNo;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getSongNum() {
        return selectedPosition + "";
    }

    @Override
    public void successNet(OutstandingBean outstandingBean) {


        disWaterList = outstandingBean.getData().getDisList();
        orderList = outstandingBean.getData().getOrderList();


        if(disWaterList != null){

            WaterListSingleTop.getOrderMapSingleTop().setMap(disWaterList);
        }


        updateUI(outstandingBean);


    }

    private void updateUI(OutstandingBean outstandingBean) {

        if (orderList.size() == 0) {

            ptrFresh.setVisibility(View.GONE);
            rlNoWater.setVisibility(View.VISIBLE);


        } else {
            ptrFresh.setVisibility(View.VISIBLE);
            rlNoWater.setVisibility(View.GONE);

            if (adapter != null){
                adapter.setAdapter(outstandingBean);
                adapter.notifyDataSetChanged();

            }else{
                adapter = new OutstandingAdapter((MainActivity) mainActivity, outstandingBean, this);

                lvOutstanding.setAdapter(adapter);

            }
        }








    }

    @Override
    public void confirmOrderSuccess(String confirmSuccess) {


        System.out.println("提交成功了....");

        singleSelectDialog.dismissDialog();

        MyToast.show(mainActivity,confirmSuccess);

        MobclickAgent.onEvent(mainActivity, "orderdis", OrderMapSingleTop.getOrderMapSingleTop().getMap());

//        confirmDialog.dismiss();

        //提交成功联网
        outstandingpresenter.loginNet();
    }

    @Override
    public void error(String error) {

        if (error.equals("-1")){

            //用于订单已经分配的情况
            MyToast.show(mainActivity,"订单已经重分配,正在为您刷新!");
            outstandingpresenter.loginNet();

        }else{

            MyToast.show(mainActivity,error);
        }


    }

    @Override
    public void click(View v) {

        int tag = (int) v.getTag();



        switch (v.getId()) {


            //提交按钮
            case R.id.btn_confirm:

                System.out.println("我是点击按钮" + tag);

                if (!NetUtils.isNetworkAvailable(mainActivity)){

                    MyToast.show(mainActivity,"暂无网络信息,请检查网络!");
                    return;
                }

                //分配送水工
                System.out.println("分配送水工");

                //设置订单跟标示
                orderNo = orderList.get(tag).getOrder_no();


                if (TextUtils.isEmpty(orderList.get(tag).getGoods_name())){
                    //退桶订单
                    status = "1";


                }else{
                    status = "0" ;
                }

                if (disWaterList != null && disWaterList.size() != 0){



                    dialogSend();
                }


                break;


            case R.id.iv_phone_call:

                //用intent启动拨打电话
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + orderList.get(tag).getShip_mobile_phone()));
                phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(phoneIntent);

                break;


        }




    }



    private void dialogSend() {


        // 取消发布

        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < disWaterList.size(); i++) {
            values.add(disWaterList.get(i).getUser_name());
        }

        singleSelectDialog = new SingleSelectDialog(mainActivity, "请您分配送水工", values);
//        dialog.setSelect(-1);



        singleSelectDialog.setOnSubmitClickListener(new SingleSelectDialog.OnSubmitClickListener() {
            @Override
            public void onSubmitClick(int pos) {
                //联网操作
                //如果选中就把当前的id设置给标示
                selectedPosition = disWaterList.get(pos).getUser_num();
                confirmOrder();
            }
        });
        singleSelectDialog.show();



    }

    private void confirmOrder() {

        outstandingpresenter.confirmNet();

    }



    public void onEventMainThread(OrderEvent event) {

        System.out.println("我收到了eventBus的消息");
        outstandingpresenter.loginNet();
    }


    @Override
    protected void unEventBus() {
        EventBus.getDefault().unregister(this);
    }
}
