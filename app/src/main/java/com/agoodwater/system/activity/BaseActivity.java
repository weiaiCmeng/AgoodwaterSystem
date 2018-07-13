package com.agoodwater.system.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.agoodwater.system.App;
import com.agoodwater.system.components.AppComponent;
import com.agoodwater.system.utils.KeyboardUtils;
import com.agoodwater.system.utils.SpUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by shiqiang on 2017/3/23.
 */

public abstract class BaseActivity extends SupportActivity {



    protected Context mContext ;
    private Unbinder bind;
    private int layoutResourceId;

    public AppComponent getAppComponent(){
        return ((App)getApplication()).getAppComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(((App)getApplication()).getAppComponent());
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutResourceId());
        bind = ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initView();
        initListener();
        initData();
        initBundle(savedInstanceState);


    }

    protected   void setupActivityComponent(AppComponent appComponent){};

    /**
     * 获取资源布局id
     *
     * @return 资源布局id
     */
    protected abstract int getLayoutResourceId();


    /**
     * 获取用户名
     * @return
     */
    protected String getUserName() {
        return SpUtils.getString(mContext, "userName", "");
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * Bundle保存activity实例
     */
    protected void initBundle(android.os.Bundle bundle){
        System.out.println("我是base里面的Bundle");

    };

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 监听事件处理
     */
    protected void initListener(){};

    /**
     * 初始化数据
     */
    protected abstract void initData();



    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }


    /**
     * 是否拦截事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (KeyboardUtils.isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }




}
