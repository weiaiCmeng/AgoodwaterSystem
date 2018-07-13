package com.agoodwater.system.moudle;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:53
 */
@Module
public class AppModule {

    public Context context;
    private Retrofit retrofit;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext(){
        return context;
    }



    @Provides @Singleton
    public ToastUtil provideToastUtil(){
        return new ToastUtil(context);
    }
/*

    @Provides @Singleton
    public Retrofit provideRetrofit(){

        //刚刚添加进来的请求头
//                .client(getOKHTTP())
//                                .client(getCacheOkHttpClient(App.getApplication()))  //使用缓存,Interceptor截获每次网络请求用于缓存数据
//添加Rxjava
//添加Gson解析
        retrofit = new Retrofit.Builder()
                .baseUrl(UserContentURL.URL_SERVER) //刚刚添加进来的请求头
//                .client(getOKHTTP())
//                                .client(getCacheOkHttpClient(App.getApplication()))  //使用缓存,Interceptor截获每次网络请求用于缓存数据
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加Rxjava
                //添加Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }




    @Provides @Singleton
    public NetworkApi provideNetworkApi(){
        return retrofit.create(NetworkApi.class);
    }
*/

}
