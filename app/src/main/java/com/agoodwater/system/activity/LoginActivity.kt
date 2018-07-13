package com.agoodwater.system.activity

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

import com.agoodwater.system.R
import com.agoodwater.system.components.AppComponent
import com.agoodwater.system.components.DaggerLoginActivityComponent
import com.agoodwater.system.moudle.moudle.ActivityModule
import com.agoodwater.system.moudle.moudle.LoginActivityModule
import com.agoodwater.system.presenter.LoginPresenter
import com.agoodwater.system.utils.MyToast
import com.agoodwater.system.utils.SpUtils
import com.agoodwater.system.view.LoginView
import com.tencent.bugly.crashreport.CrashReport
import com.umeng.analytics.MobclickAgent

import javax.inject.Inject

import butterknife.BindView
import butterknife.OnClick
import cn.jpush.android.api.JPushInterface
import com.agoodwater.system.contract.LoginContract

/**
 * Created by shiqiang on 2017/2/22.
 */

class LoginActivity : BaseActivity(), LoginContract.View {


    @BindView(R.id.iv_photo)
    internal lateinit var ivPhoto: ImageView
    @BindView(R.id.et_user)
    internal lateinit var etUser: EditText
    @BindView(R.id.et_passWord)
    internal lateinit var etPassWord: EditText
    @BindView(R.id.bt_login)
    internal lateinit var btLogin: Button

    @Inject
    internal lateinit var loginPresenter: LoginPresenter

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_login
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        super.setupActivityComponent(appComponent)
        DaggerLoginActivityComponent.builder()
                .appComponent(appComponent)
                .loginActivityModule(LoginActivityModule(this))
                .activityModule(ActivityModule(this)).build()
                .inject(this)


    }

    override fun initView() {

    }

    override fun initData() {

        initActivity()
    }

    private fun initActivity() {

        val userId = SpUtils.getString(this@LoginActivity, "userName", "-1")
        if ("-1" != userId) {
            initSplash()
        }
    }

    private fun initSplash() {
        startActivity(SplashActivity::class.java)
        finish()
    }

    override fun getPhone(): String {
        return etUser.text.toString().trim()
    }

    override fun getPwd(): String {

        return etPassWord.text.toString().trim()
    }

    override fun loginSuccess(success: String) {

        SpUtils.setString(this, "userName", success)
        //设置推送声音
        SpUtils.setBoolean(this, "jpush", true)


        /**
         * 友盟统计登录的账号信息
         */
        MobclickAgent.onProfileSignIn(success)

        /**
         * 给应用添加jpush的标志,以用户的用户id为标识;
         */
        JPushInterface.setAlias(this@LoginActivity, success, null)

        /**
         * bugly设置账号用于查询某个用户发生错误
         */
        CrashReport.setUserId(success)

        //友盟统计设置账户名统计
        MobclickAgent.onProfileSignIn(success)
        MyToast.show(this@LoginActivity, "登录成功")
        initSplash()
    }

    override fun loginError(error: String) {
        MyToast.show(this@LoginActivity, error)
    }


    @OnClick(R.id.bt_login)
    fun onClick() {
        loginPresenter.loginNet()
    }
}
