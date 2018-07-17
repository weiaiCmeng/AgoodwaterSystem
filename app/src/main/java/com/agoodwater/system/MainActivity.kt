package com.agoodwater.system

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.KeyEvent
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast

import com.agoodwater.system.adapter.FragmentPagerAdapter
import com.agoodwater.system.fragment.CompletedPage
import com.agoodwater.system.fragment.OutstandingOrderPage
import com.agoodwater.system.fragment.StoreManagementPage
import com.agoodwater.system.fragment.ViewPagerFragment
import com.agoodwater.system.utils.MyToast
import com.agoodwater.system.utils.NetUtils
import com.agoodwater.system.view.NoScrollViewPager

import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask

import butterknife.BindView
import butterknife.ButterKnife
import com.agoodwater.system.activity.BaseActivity
import me.yokeyword.fragmentation.SupportActivity

import com.agoodwater.system.utils.MyToast.show

class MainActivity : BaseActivity() {

    var pagerList: MutableList<ViewPagerFragment> = ArrayList()
    @BindView(R.id.vp_home_content)
    internal lateinit var vpHomeContent: NoScrollViewPager
    @BindView(R.id.rb_outstanding)
    internal lateinit var rbOutstanding: RadioButton
    @BindView(R.id.rb_completed)
    internal lateinit var rbCompleted: RadioButton
    @BindView(R.id.rb_store)
    internal lateinit var rbStore: RadioButton
    @BindView(R.id.rg_new_group)
    internal lateinit var rgNewGroup: RadioGroup
    @BindView(R.id.rl_radioGroup)
    internal lateinit var rlRadioGroup: RelativeLayout


    override fun getLayoutResourceId(): Int {
       return R.layout.activity_main
    }

    override fun initView() {

        //1:未完成订单
        pagerList.add(OutstandingOrderPage())
        //2:已完成订单
        pagerList.add(CompletedPage())
        //3:门店管理
        pagerList.add(StoreManagementPage())
        vpHomeContent.adapter = FragmentPagerAdapter(supportFragmentManager, pagerList)

        //初始化第一个布局文件
        vpHomeContent.setCurrentItem(0, false)
        rbOutstanding.toggle()  }

    override fun initData() {
    }


    override fun initListener() {

        /**
         * 设置rediobutton点击相应viewpager的页码
         */
        rgNewGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_outstanding -> {


                    if (vpHomeContent != null) {

                        vpHomeContent.setCurrentItem(0, false)
                    }


                    if (!NetUtils.isNetworkAvailable(this@MainActivity)) {
                        show(this@MainActivity, "暂无网络信息,请检查网络!")
                    }
                }
                R.id.rb_completed -> {
                    //                        EventBus.getDefault().post(new CompletedEventBus());
                    vpHomeContent.setCurrentItem(1, false)

                    if (!NetUtils.isNetworkAvailable(this@MainActivity)) {
                        show(this@MainActivity, "暂无网络信息,请检查网络!")
                    }
                }
                R.id.rb_store -> {
                    //                        EventBus.getDefault().post(new StoreManagementEvent());
                    vpHomeContent.setCurrentItem(2, false)

                    if (!NetUtils.isNetworkAvailable(this@MainActivity)) {
                        MyToast.show(this@MainActivity, "暂无网络信息,请检查网络!")
                    }
                }
            }
        }



    }

    /**
     * 菜单、返回键响应
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click()      //调用双击退出函数
        } else false
    }
    private var isExit = false
    private fun exitBy2Click(): Boolean {
        var tExit: Timer
        if (isExit == false) {
            isExit = true // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            tExit = Timer()

            tExit.schedule(object : TimerTask() {
                override fun run() {
                    isExit = false // 取消退出
                }
            }, 2000) // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
            isExit = false
        }
        return isExit
    }

    companion object {
        /**
         * 双击退出函数
         */

    }

}
