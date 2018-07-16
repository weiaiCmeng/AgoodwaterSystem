package com.agoodwater.system.activity

import android.app.Dialog
import android.content.DialogInterface
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.agoodwater.system.MainActivity
import com.agoodwater.system.R
import com.agoodwater.system.bean.ServionBean
import com.agoodwater.system.dialog.CommonDialog
import com.agoodwater.system.network.ResponseSubscriber
import com.agoodwater.system.update.UpdateFractory
import com.agoodwater.system.utils.MyToast
import com.agoodwater.system.utils.NetUtils
import com.agoodwater.system.utils.UpdateManager
import com.agoodwater.system.utils.VersionUtils

import java.util.HashMap
import java.util.concurrent.TimeUnit

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1


/**
 * Created by shiqiang on 2016/11/24.
 */

class SplashActivity : BaseActivity() {


    //进入页面的时间
    private var initTime: Long = 0
    //结束时的时间
    private var endTime: Long = 0
    private lateinit var stringBuffer: StringBuffer

    private var isFirst = true
    private lateinit var map: MutableMap<String, String>

    private lateinit var confirmbuilder: CommonDialog.Builder

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        initTime = System.currentTimeMillis()
    }

    override fun initData() {
        updateServer()
    }


    /**
     * 更新UI
     */
    private fun updateServer() {

        //判断是否有网路
        if (NetUtils.isNetworkAvailable(this@SplashActivity)) {
            //更新版本
            updateServionNet()
        } else {
            Toast.makeText(applicationContext, "暂无可用网络,请您联网！", Toast.LENGTH_LONG).show()
            enitAcitivity()
        }
    }

    /**
     * 进入页面的设置
     */
    private fun enitAcitivity() {

        endTime = System.currentTimeMillis()
        val startTime = endTime - initTime
        if (startTime >= 2000) {
            initMainActy()
        } else {
            //使用RxJava避免使用Handler导致的内存泄露,使用Handler需要使用静态内部类
            val timer = Observable.timer(1500, TimeUnit.MILLISECONDS)
            timer.observeOn(AndroidSchedulers.mainThread())
                    .subscribe { aLong ->
                        initMainActy()
                    }
        }
    }


    private fun initMainActy() {

        startActivity(MainActivity::class.java)
        //两个参数分别表示进入的动画,退出的动画
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    private fun updateServionNet() {

        map = HashMap()
        map.put("para", "appSystem")

        UpdateFractory.getBuild()
                .name("updateServerCall")
                .map(map)
                .build()
                .execute(object : ResponseSubscriber<ServionBean>() {
                    override fun onFailure(e: Throwable) {
                        MyToast.show(this@SplashActivity, "服务器繁忙,请你稍后重试!")
                        enitAcitivity()
                    }

                    override fun onSuccess(servionBean: ServionBean) {
                        val servionNum = servionBean.data[0].servionNum
                        val servionDes = servionBean.data[0].servionDes
                        val currentVersionCode = VersionUtils.getCurrentVersionCode()
                        val url = servionBean.data[0].url
                        val descr = servionBean.data[0].descr
                        stringBuffer = StringBuffer()
                        if (TextUtils.isEmpty(descr)) {
                            stringBuffer.append("请你更新!")
                        } else {
                            val split = descr.split("~".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                            for (i in split.indices) {
                                if (i == split.size - 1) {
                                    stringBuffer.append(split[i])
                                } else {
                                    stringBuffer.append(split[i] + "\n")
                                }
                            }
                        }

                        if (currentVersionCode >= servionNum) {
                            //                if (currentVersionCode < servionNum){
                            enitAcitivity()
                        } else {

                            if (isFirst) {
                                isFirst = false

                                confirmbuilder = CommonDialog.Builder(this@SplashActivity)
                                confirmbuilder.setTitle("请确认你的选择")
                                        .setTitleColor(R.color.color5d5d5d)
                                        .setMessage("服务器发现新版本：$servionDes" + stringBuffer)
                                        .setMessageColor(R.color.colorRed)
                                        .setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->

                                            initMainActy()

                                        })
                                        .setPositiveButton("确认", DialogInterface.OnClickListener { dialog, which ->
                                            //联网操作
                                            //这里来检测版本是否需要更新
                                            val mUpdateManager = UpdateManager(this@SplashActivity, url)
                                            mUpdateManager.showNoticeDialog()
                                            confirmbuilder.hidden()
                                        })
                                        .create().show()
                            }
                        }
                    }
                })
    }

}
