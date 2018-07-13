package com.agoodwater.system.network;

import android.content.Context;
import android.text.TextUtils;
import com.orhanobut.logger.Logger;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {


    private static volatile Retrofit retrofit;

    private AppRetrofit(){

    }

    public static <T> T getNewsRetrofit(Class<T> clazz, String baseUrl) {

        if (TextUtils.isEmpty(baseUrl)) {
            throw new IllegalArgumentException("BaseUrl cannot be null");
        }
            if(retrofit == null){
                synchronized (AppRetrofit.class){
                    if (retrofit == null){
                        retrofit = new Retrofit.Builder()
                                .baseUrl(baseUrl) //刚刚添加进来的请求头
                                .client(getOKHTTP())
//                                .client(getCacheOkHttpClient(App.getApplication()))  //使用缓存,Interceptor截获每次网络请求用于缓存数据
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加Rxjava
                                //添加Gson解析
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                    }
                }
            }
            return retrofit.create(clazz);
    }

    /**
     * 判断是否需要缓存数据,默认为false,可以用单利在每次请求之前设置值
     */
    private static OkHttpClient getOKHTTP() {
        //是否有缓冲
//        if (App.getIsChach()){
//        if (false){
//          return   getCacheOkHttpClient(App.getApplication());
//
//        }else {

           return  getOKHttpClient() ;


//        }

    }

    private static OkHttpClient getOKHttpClient(){
       /* SSLSocketFactory sslSocketFactory = null;
        try {
            sslSocketFactory = getSSLSocketFactory_Certificate(App.getApplication(),"BKS", R.raw.server);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }*/

        //添加日志的缓存
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Logger.e("retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient build = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        Request.Builder builder1 = request.newBuilder();
//                        Request build = builder1.addHeader("Accept", "application/json, text/javascript, */*").build();
//                        return chain.proceed(build);
//                    }
//                })
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                //设置Https请求
//                .sslSocketFactory(sslSocketFactory)
                .build();

        return build ;
    }


    private static OkHttpClient getCacheOkHttpClient(final Context context){
        final File baseDir = context.getCacheDir();
        final File cacheDir = new File(baseDir, "HttpResponseCache");
//        Timber.e(cacheDir.getAbsolutePath());
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);   //缓存可用大小为10M

        //Retrofit网络请求时候的截获类Interceptor
        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
            if(!NetWorkUtils.isNetWorkAvailable(context)){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetWorkAvailable(context)) {
                int maxAge = 60;                  //在线缓存一分钟
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();

            } else {
                int maxStale = 60 * 60 * 24 * 4 * 7;     //离线缓存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)

                        .build();
            }


            }
        };


        return new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
    }


    private static SSLSocketFactory getSSLSocketFactory_Certificate(Context context, String keyStoreType, int keystoreResId)

    throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException

    {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        InputStream caInput = context.getResources().openRawResource(keystoreResId);

        Certificate ca = cf.generateCertificate(caInput);

        caInput.close();

        if(keyStoreType ==null|| keyStoreType.length() ==0) {

            keyStoreType = KeyStore.getDefaultType();

        }

        KeyStore keyStore = KeyStore.getInstance(keyStoreType);

        keyStore.load(null,null);

        keyStore.setCertificateEntry("ca", ca);

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);

        tmf.init(keyStore);

        TrustManager[] wrappedTrustManagers = MyTrustManager.getWrappedTrustManagers( tmf.getTrustManagers());
//        TrustManager[] wrappedTrustManagers =TrustManager.getWrappedTrustManagers(tmf.getTrustManagers());

        SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(null, wrappedTrustManagers,null);

        return sslContext.getSocketFactory();

    }




}
