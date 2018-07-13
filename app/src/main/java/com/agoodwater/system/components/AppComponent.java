package com.agoodwater.system.components;

import android.content.Context;

import com.agoodwater.system.moudle.AppModule;
import com.agoodwater.system.moudle.ToastUtil;
import com.agoodwater.system.network.NetworkApi;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 15:03
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context getContext();
    ToastUtil getToastUtil();

//    Retrofit getRetrofit();
//
//    NetworkApi getNetworkApi();

}
