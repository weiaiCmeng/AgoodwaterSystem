package com.agoodwater.system.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.TextView

import com.agoodwater.system.MainActivity
import com.agoodwater.system.R
import com.agoodwater.system.adapter.OutstandingAdapter
import com.agoodwater.system.bean.OutstandingBean
import com.agoodwater.system.components.AppComponent
import com.agoodwater.system.components.DaggerMainActivityComponent
import com.agoodwater.system.dialog.SingleSelectDialog
import com.agoodwater.system.moudle.moudle.ActivityModule
import com.agoodwater.system.moudle.moudle.MainActivityModule
import com.agoodwater.system.presenter.OutstandingInfo
import com.agoodwater.system.presenter.Outstandingpresenter
import com.agoodwater.system.receiver.OrderEvent
import com.agoodwater.system.singletop.OrderMapSingleTop
import com.agoodwater.system.singletop.WaterListSingleTop
import com.agoodwater.system.utils.MyToast
import com.agoodwater.system.utils.NetUtils
import com.agoodwater.system.utils.SpUtils
import com.agoodwater.system.view.OutstandingView
import com.umeng.analytics.MobclickAgent

import java.util.ArrayList

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife
import de.greenrobot.event.EventBus
import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler

/**
 * Created by shiqiang on 2017/3/22.
 */
class OutstandingOrderPage : ViewPagerFragment(), OutstandingView, OutstandingAdapter.Callback {

    @BindView(R.id.iv_back)
    internal lateinit var ivBack: ImageView
    @BindView(R.id.tv_title_name)
    internal lateinit var tvTitleName: TextView
    @BindView(R.id.tv_right)
    internal lateinit var tvRight: TextView
    @BindView(R.id.ll_comp_quit)
    internal lateinit var llCompQuit: LinearLayout
    @BindView(R.id.lv_outstanding)
    internal lateinit var lvOutstanding: ListView
    @BindView(R.id.ptr_fresh)
    internal lateinit var ptrFresh: PtrClassicFrameLayout
    @BindView(R.id.tv_no_order)
    internal lateinit var tvNoOrder: TextView
    @BindView(R.id.rl_no_water)
    internal lateinit var rlNoWater: RelativeLayout
    private lateinit var orderList: List<OutstandingBean.DataBean.OrderListBean>
    private lateinit var disWaterList: List<OutstandingBean.DataBean.DisListBean>
    private lateinit var adapter: OutstandingAdapter
    //订单号
    private var orderNo: String? = null

    //送水跟退桶的标示 1 为送水 0 为退桶
    private var status: String? = null
    //checkBox选择的标示:选中为当前送水工的id, 没有选择为-1 ;
    private var selectedPosition: Int = 0

    private lateinit var singleSelectDialog: SingleSelectDialog

    @Inject
    internal lateinit var outstandingpresenter: Outstandingpresenter


    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_outstanding
    }

    override fun setDaggerListener(appComponent: AppComponent) {

        DaggerMainActivityComponent.builder().appComponent(appComponent)
                .mainActivityModule(MainActivityModule(this))
                .activityModule(ActivityModule(mainActivity))
                .build()
                .inject(this)
    }

    override fun onVisible() {
        initDate()
    }

    override fun setEventBus() {
        EventBus.getDefault().register(this)
    }

    private fun initDate() {

        tvTitleName.text = "订单处理"
        tvRight.visibility = View.GONE
        ivBack.visibility = View.GONE

        ptrFresh.setLastUpdateTimeRelateObject(mainActivity)
        ptrFresh.setPtrHandler(object : PtrHandler {
            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
                var flag = true
                if (Build.VERSION.SDK_INT < 14) {
                    flag = lvOutstanding.childCount > 0 && (lvOutstanding.firstVisiblePosition > 0 || lvOutstanding.getChildAt(0).top < lvOutstanding.paddingTop)
                } else {
                    flag = lvOutstanding.canScrollVertically(-1)
                }
                return !flag
            }

            override fun onRefreshBegin(frame: PtrFrameLayout) {

                frame.postDelayed({
                    ptrFresh.refreshComplete()
                    outstandingpresenter.loginNet()
                }, 1000)

            }
        })

        outstandingpresenter.loginNet()

    }

    override fun getUserId(): String {
        return SpUtils.getString(mainActivity, "userName", "")
    }

    override fun getOrderNo(): String? {
        return orderNo
    }

    override fun getStatus(): String? {
        return status
    }

    override fun getSongNum(): String {
        return selectedPosition.toString() + ""
    }

    override fun successNet(outstandingBean: OutstandingBean) {


        disWaterList = outstandingBean.data.disList
        orderList = outstandingBean.data.orderList


        if (disWaterList != null) {

            WaterListSingleTop.getOrderMapSingleTop().map = disWaterList
        }
        updateUI(outstandingBean)
    }
    private fun updateUI(outstandingBean: OutstandingBean) {
        if (orderList.size == 0) {
            ptrFresh.visibility = View.GONE
            rlNoWater.visibility = View.VISIBLE

        } else {
            ptrFresh.visibility = View.VISIBLE
            rlNoWater.visibility = View.GONE

            if (adapter != null) {
                adapter.setAdapter(outstandingBean)
                adapter.notifyDataSetChanged()
            } else {
                adapter = OutstandingAdapter(mainActivity as MainActivity, outstandingBean, this)
                lvOutstanding.adapter = adapter
            }
        }
    }

    override fun confirmOrderSuccess(confirmSuccess: String) {

        singleSelectDialog.dismissDialog()
        MyToast.show(mainActivity, confirmSuccess)
        MobclickAgent.onEvent(mainActivity, "orderdis", OrderMapSingleTop.getOrderMapSingleTop().map)
        //提交成功联网
        outstandingpresenter.loginNet()
    }

    override fun error(error: String) {

        if (error == "-1") {

            //用于订单已经分配的情况
            MyToast.show(mainActivity, "订单已经重分配,正在为您刷新!")
            outstandingpresenter.loginNet()

        } else {

            MyToast.show(mainActivity, error)
        }


    }

    override fun click(v: View) {

        val tag = v.tag as Int
        when (v.id) {

        //提交按钮
            R.id.btn_confirm -> {

                if (!NetUtils.isNetworkAvailable(mainActivity)) {

                    MyToast.show(mainActivity, "暂无网络信息,请检查网络!")
                    return
                }

                //分配送水工
                println("分配送水工")

                //设置订单跟标示
                orderNo = orderList[tag].order_no


                if (TextUtils.isEmpty(orderList[tag].goods_name)) {
                    //退桶订单
                    status = "1"


                } else {
                    status = "0"
                }

                if (disWaterList != null && disWaterList.size != 0) {


                    dialogSend()
                }
            }


            R.id.iv_phone_call -> {

                //用intent启动拨打电话
                val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + orderList[tag].ship_mobile_phone))
                phoneIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(phoneIntent)
            }
        }


    }


    private fun dialogSend() {


        // 取消发布

        val values = ArrayList<String>()
        for (i in disWaterList.indices) {
            values.add(disWaterList[i].user_name)
        }

        singleSelectDialog = SingleSelectDialog(mainActivity, "请您分配送水工", values)
        //        dialog.setSelect(-1);


        singleSelectDialog.setOnSubmitClickListener { pos ->
            //联网操作
            //如果选中就把当前的id设置给标示
            selectedPosition = disWaterList[pos].user_num
            confirmOrder()
        }
        singleSelectDialog.show()


    }

    private fun confirmOrder() {

        outstandingpresenter.confirmNet()

    }


    fun onEventMainThread(event: OrderEvent) {

        println("我收到了eventBus的消息")
        outstandingpresenter.loginNet()
    }


    override fun unEventBus() {
        EventBus.getDefault().unregister(this)
    }

}
