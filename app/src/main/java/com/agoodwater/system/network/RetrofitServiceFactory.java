package com.agoodwater.system.network;

/**
 * 根据不同功能模块返回不同的Service接口对象
 */
public class RetrofitServiceFactory {
    private static final String Base_URL = UserContentURL.URL_SERVER;

    public static NetworkApi getAppService(){
        return AppRetrofit.getNewsRetrofit(NetworkApi.class,Base_URL);
    }
}
