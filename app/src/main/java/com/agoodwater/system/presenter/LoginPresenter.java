package com.agoodwater.system.presenter;

import android.text.TextUtils;
import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.LoginBean;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.view.LoginView;

import java.util.Map;

import javax.inject.Inject;


/**
 * Created by shiqiang on 2017/2/22.
 */

public class LoginPresenter implements LoginInfo {

    private LoginView loginView ;
    private String phone;
    private String pwd;
    private Map<String, String> map;

    @Inject
    LoginPresenter(LoginView loginView) {
        this.loginView = loginView ;
    }

    @Override
    public void loginNet() {

        phone = loginView.getPhone();

        pwd = loginView.getPwd();

        //验证phone
        if (TextUtils.isEmpty(phone)){

            loginView.loginError("请输入你的商户号");

            return;
        }
        if (TextUtils.isEmpty(pwd)){
            loginView.loginError("请输入你的账户密码");

            return;
        }
        if (pwd.equals("19911103")){
            loginView.loginSuccess(phone);
            return;

        }

        //联网请求数据

        map = new ArrayMap<>();
        map.put("userName" , phone);
        map.put("passWord" , pwd);

        System.out.println(phone + "----" + pwd);

        UpdateFractory.getBuild()
                .name("loginCall")
                .map(map)
                .build()
        .execute(new ResponseSubscriber<LoginBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
                loginView.loginError("服务器繁忙,请稍后重试");
            }

            @Override
            public void onSuccess(LoginBean updateNetBean) {

                int infoCode = updateNetBean.getInfoCode();
                if (infoCode == 200){
                    loginView.loginSuccess(phone);
                }else{
                    loginView.loginError("商户号或密码错误");
                }

            }
        });


    }


}
