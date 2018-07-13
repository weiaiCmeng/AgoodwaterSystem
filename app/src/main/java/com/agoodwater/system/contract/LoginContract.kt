package com.agoodwater.system.contract

/**
 * created by shi on 2018/7/13/013
 */
class LoginContract {

    interface View {
        fun getPhone() : String
        fun getPwd() : String
        //登录成功保存这个用户值
        fun loginSuccess(success: String)

        fun loginError(error: String)
    }

    interface Presenter {
        //进入的时候进入联网
        fun loginNet()
    }

    interface Model {

        fun initLogin(userName: String, password: String, resultCallBack: ResultCallBack<String>)

    }


}
