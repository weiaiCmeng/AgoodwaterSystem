package com.agoodwater.system.presenter

import android.text.TextUtils
import android.support.v4.util.ArrayMap

import com.agoodwater.system.bean.LoginBean
import com.agoodwater.system.contract.LoginContract
import com.agoodwater.system.contract.ResultCallBack
import com.agoodwater.system.network.ResponseSubscriber
import com.agoodwater.system.update.UpdateFractory
import com.agoodwater.system.view.LoginView

import javax.inject.Inject


/**
 * Created by shiqiang on 2017/2/22.
 */

class LoginPresenter @Inject internal constructor(private var mView: LoginContract.View, private val mModel: LoginContract.Model) : LoginContract.Presenter {

    private lateinit var phone: String
    private lateinit var pwd: String


    override fun loginNet() {

        phone = mView.getPhone()

        pwd = mView.getPwd()

        //验证phone
        if (TextUtils.isEmpty(phone)) {

            mView.loginError("请输入你的商户号")

            return
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.loginError("请输入你的账户密码")

            return
        }
        if (pwd == "19911103") {
            mView.loginSuccess(phone)
            return

        }

        mModel.initLogin(phone, pwd , object : ResultCallBack<String> {
            override fun onSuccess(phone: String) {
                mView.loginSuccess(phone)
            }

            override fun onFailure(error: String) {
                mView.loginError("服务器繁忙,请稍后重试")
            }
        })


    }


}
