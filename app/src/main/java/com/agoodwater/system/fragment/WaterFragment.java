package com.agoodwater.system.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.WaterDetailsAdapter;
import com.agoodwater.system.bean.OutstandingBean;
import com.agoodwater.system.bean.WaterCompletedBean;
import com.agoodwater.system.contract.WaterContract;
import com.agoodwater.system.dialog.SingleSelectDialog;
import com.agoodwater.system.presenter.WaterPresenter;
import com.agoodwater.system.singletop.WaterListSingleTop;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.NetUtils;
import com.agoodwater.system.utils.SpUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by shiqiang on 2016/10/21.
 */
public class WaterFragment extends BasePagerFragment implements WaterContract.View , WaterDetailsAdapter.OnRecyclerViewItemClickListener{
    private StringBuffer sb;

    private int pager = 1;

    //记录是否联网成功的标示
    boolean isSuccess = true;


    private PopupWindow popupWindow;

    private int width;
    private List<OutstandingBean.DataBean.DisListBean> disWaterList;
    private String orderNo;
    private String status;
    private int selectedPosition;
    private ListView lvSend;
    private Map<Integer, Boolean> isSelected;

    private SingleSelectDialog singleSelectDialog;
    private Map<String, String> outstandingMap;
    private Map<String, String> confirmMap;
    private WaterPresenter waterPresenter;
    private List<WaterCompletedBean.DataBean.OrderListBean> waterOrder;
    private List<WaterCompletedBean.DataBean.OrderListBean> newWaterOrder;
    private WaterDetailsAdapter mAdapter;

    public WaterFragment(Activity activity) {
        super(activity);
    }


    @Override
    public void initData() {

//        EventBus.getDefault().register(this);

        newWaterOrder = new ArrayList();

        System.out.println("我是已完成initData");

        tvNoOrder.setText("暂无已完成订单!");


        //使用单例模式取数据
        disWaterList = WaterListSingleTop.getOrderMapSingleTop().getMap();



        //设置RecycleView 的布局格式
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);

        waterPresenter = new WaterPresenter(this);

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
                                isSuccess = true;
                                newWaterOrder.clear();
                                pager = 1;
                                waterPresenter.loginNet();
                                mAdapter.notifyDataSetChanged();

                            }
                        });

                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerview.refreshComplete();


                        //如果请求数据成功以后再次发起请求
                        //上拉更新1条

                        recyclerview.setLoadingMoreEnabled(true);
                        isSuccess = true;
                        newWaterOrder.clear();
                        pager = 1;
                        waterPresenter.loginNet();
                        mAdapter.notifyDataSetChanged();


                    }
                }, 2 * 1000);*/
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
                                if (isSuccess){

                                    return true;
                                }else{
                                    recyclerview.setLoadingMoreEnabled(false);
                                    MyToast.show(mActivity , "没有更多数据了");
                                    return false ;

                                }
                            }
                        })
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                System.out.println(aLong + "heihei");
                                pager++;
                                waterPresenter.loginNet();


                            }
                        });





              /*  new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerview.loadMoreComplete();

                        if (isSuccess) {
                            pager++;
                            waterPresenter.loginNet();


                        } else {

                            recyclerview.setLoadingMoreEnabled(false);
                            MyToast.show(mActivity , "没有更多数据了");

                        }

                    }
                }, 2 * 1000);*/
            }
        });



        waterPresenter.loginNet();



    }








    @Override
    public void onClick(View v) {

        int tag = (int) v.getTag();

        switch (v.getId()) {
            case R.id.btn_confirm:


                System.out.println("我是点击按钮" + tag);

                if (!NetUtils.isNetworkAvailable(mActivity)){

                    MyToast.show(mActivity,"暂无网络信息,请检查网络!");
                    return;
                }

                //分配送水工
                System.out.println("分配送水工");

                //设置订单跟标示
                orderNo = newWaterOrder.get(tag).getOrder_no();


                    status = "0" ;

                dialogSend();

//                if (!ButtonUtils.isFastClick()){
//
//                    MyToast.show(mainActivity,"申请已提交,请稍等...");
//                    return;
//
//                }

//                checkNet(newWaterOrder.get(tag).getOrder_no());

                break;


            case R.id.iv_phone_call:


                //用intent启动拨打电话
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + newWaterOrder.get(tag).getShip_mobile_phone()));
                phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(phoneIntent);


                break;


        }


    }

    private void dialogSend() {



        // 取消发布

        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < disWaterList.size(); i++) {
            values.add(disWaterList.get(i).getUser_name());
        }

        singleSelectDialog = new SingleSelectDialog(mActivity, "请您分配送水工", values);
//        dialog.setSelect(-1);



        singleSelectDialog.setOnSubmitClickListener(new SingleSelectDialog.OnSubmitClickListener() {
            @Override
            public void onSubmitClick(int pos) {
                //联网操作
                //如果选中就把当前的id设置给标示
                selectedPosition = disWaterList.get(pos).getUser_num();
                waterPresenter.confirmWater();
            }
        });
        singleSelectDialog.show();

    }

    @Override
    public String getUserName() {
        return SpUtils.getString(mActivity, "userName", "");
    }

    @Override
    public String getPageNum() {
        return pager + "";
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
    public void updateNet(WaterCompletedBean completedOrderBean) {

        //联网成功的标示


        isSuccess = true;

        waterOrder = completedOrderBean.getData().getOrderList();

        newWaterOrder.addAll(waterOrder);

        System.out.println("This is pager" + pager + "--" + waterOrder.size() + "---" + newWaterOrder.size());

        if (newWaterOrder.size() == 0) {

            rlNoWater.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);

        }else{
            rlNoWater.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);




        }

        if (mAdapter == null) {

            mAdapter = new WaterDetailsAdapter(mActivity, newWaterOrder);
            recyclerview.setAdapter(mAdapter);

        } else {
            mAdapter.setAdapter(newWaterOrder);
            mAdapter.notifyDataSetChanged();

        }
        mAdapter.setOnItemClickListener(this);


        if (waterOrder.size() < 10) {

            //不让在加载数据了
            isSuccess = false;


        }





    }

    @Override
    public void confirmOrder(String confirmSuccess) {

        singleSelectDialog.dismissDialog();

        newWaterOrder.clear();
        mAdapter = null;
        pager = 1;

        waterPresenter.loginNet();

    }

    @Override
    public void error(String error) {



    }



}
