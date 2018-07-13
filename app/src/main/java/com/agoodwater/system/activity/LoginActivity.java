package com.agoodwater.system.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.agoodwater.system.R;
import com.agoodwater.system.components.AppComponent;
import com.agoodwater.system.components.DaggerLoginActivityComponent;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.LoginActivityModule;
import com.agoodwater.system.presenter.LoginPresenter;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.view.LoginView;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by shiqiang on 2017/2/22.
 */

public class LoginActivity extends BaseActivity implements LoginView {


    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_passWord)
    EditText etPassWord;
    @BindView(R.id.bt_login)
    Button btLogin;
//    private LoginPresenter loginPresenter;


    @Inject
    LoginPresenter loginPresenter ;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        super.setupActivityComponent(appComponent);
        DaggerLoginActivityComponent.builder()
                .appComponent(appComponent)
                .loginActivityModule(new LoginActivityModule(this))
                .activityModule(new ActivityModule(this)).build()
                .inject(this);


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        initActivity();
    }

    private void initActivity() {

       String userId = SpUtils.getString(LoginActivity.this, "userName", "-1");
        System.out.println(userId + "我爱上");
        if (!"-1".equals(userId)) {

            System.out.println(userId + " ---------------");
            initSplash();
        }
    }

    private void initSplash() {
        startActivity(SplashActivity.class);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();



//        loginPresenter = new LoginPresenter(this);
    }
    @Override
    public String getPhone() {
        return etUser.getText().toString().trim();
    }

    @Override
    public String getPwd() {

        return etPassWord.getText().toString().trim();
    }

    @Override
    public void loginSuccess(String success) {

        SpUtils.setString(this,"userName",success);
        //设置推送声音
        SpUtils.setBoolean(this, "jpush", true);


        /**
         * 友盟统计登录的账号信息
         */
        MobclickAgent.onProfileSignIn(success);

        /**
         * 给应用添加jpush的标志,以用户的用户id为标识;
         */
        JPushInterface.setAlias(LoginActivity.this, success, null);

        /**
         * bugly设置账号用于查询某个用户发生错误
         */
        CrashReport.setUserId(success);

        //友盟统计设置账户名统计
        MobclickAgent.onProfileSignIn(success);
        System.out.println(success + "+++++");
        MyToast.show(LoginActivity.this, "登录成功");

        initSplash();

    }

    @Override
    public void loginError(String error) {

        MyToast.show(LoginActivity.this, error);


    }


    @OnClick(R.id.bt_login)
    public void onClick() {

//        RxBus.getDefault().post(new UserEvent(1, "yoyo"));
//        EventBus.getDefault().post(new UserEvent(1, "yoyo"));
        loginPresenter.loginNet();
    }
}
