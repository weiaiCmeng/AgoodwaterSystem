package com.agoodwater.system.model

import android.support.v4.util.ArrayMap
import com.agoodwater.system.bean.LoginBean
import com.agoodwater.system.contract.LoginContract
import com.agoodwater.system.contract.ResultCallBack
import com.agoodwater.system.network.ResponseSubscriber
import com.agoodwater.system.update.UpdateFractory

/**
 * created by shi on 2018/7/13/013
 */
class LoginModel : LoginContract.Model {

    private lateinit var map: MutableMap<String, String>
    override fun initLogin(userName: String, password: String, resultCallBack: ResultCallBack<String>) {

        //联网请求数据

        map = ArrayMap<String, String>()
        map.put("userName", userName)
        map.put("passWord", password)

        UpdateFractory.getBuild()
                .name("loginCall")
                .map(map)
                .build()
                .execute(object : ResponseSubscriber<LoginBean>() {
                    override fun onFailure(e: Throwable) {

                        e.printStackTrace()
                        resultCallBack.onFailure("服务器繁忙,请稍后重试")
                    }

                    override fun onSuccess(updateNetBean: LoginBean) {

                        val infoCode = updateNetBean.infoCode
                        if (infoCode == 200) {
                            resultCallBack.onSuccess(userName)
                        } else {
                            resultCallBack.onFailure("商户号或密码错误")
                        }
                    }
                })
    }
}
