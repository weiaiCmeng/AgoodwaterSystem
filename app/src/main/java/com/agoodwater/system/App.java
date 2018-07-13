package com.agoodwater.system;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.multidex.MultiDex;

import com.agoodwater.system.components.AppComponent;
import com.agoodwater.system.components.DaggerAppComponent;
import com.agoodwater.system.moudle.AppModule;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import java.io.File;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by xiongmc on 2015/12/22.
 */
public class App extends Application {
    // 获取到主线程的上下文
    public static App mContext;
    // 主线程的id
    private static int mMainThreadId;
    // 获取到主线程
    private static Thread mMainThread;
    // 获取到主线程的handler
    private static Handler mMainThradHandler;
    // 获取到主线程的looper
    private static Looper mMainThradLooper;

    //用于网络请求是否缓存网络请求的数据
    public static boolean isChach = false;
    private AppComponent appComponent;

    public static boolean getIsChach() {
        return isChach;
    }

    public static void setChach(boolean isChach) {
        App.isChach = isChach;
    }



    /**
     * 全局Application，方便调用
     */
    public static App application;
    public static SharedPreferences SP;
    public static SharedPreferences.Editor EDIT;

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        application = this;


        //注册极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        /**
         * 发送代码错误给bugly
         */





        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 1 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.mipmap.ic_system;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.mipmap.ic_system;


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_system;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = false;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);


        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */

        /**
         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;
         * init方法会自动检测更新，不需要再手动调用Beta.checkUpdate(),如需增加自动检查时机可以使用Beta.checkUpdate(false,false);
         * 参数1： applicationContext
         * 参数2：appId
         * 参数3：是否开启debug
         */
        Bugly.init(getApplicationContext(),"9472b756db", true);












//        CrashReport.initCrashReport(getApplicationContext(), "9472b756db", false);
//        Bugly.init(getApplicationContext(), "9472b756db", false);
      /*  CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());*/
//        Logger.init("Json_pen");
        SP = getSharedPreferences("config", MODE_PRIVATE);
        EDIT = SP.edit();
        this.mContext = this;
        this.mMainThreadId = Process.myTid();
        this.mMainThread = Thread.currentThread();
        this.mMainThradHandler = new Handler();
        this.mMainThradLooper = getMainLooper();


    }

    private boolean shouldInit() {

        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }


    public AppComponent getAppComponent(){
        return appComponent;
    }


    //    public static CustomProgressDialog getDialog(Context context){
//         CustomProgressDialog  dialog=CustomProgressDialog.createDialog(context);
//        return dialog;
//    }
    public static App getApplication() {
        return mContext;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static Handler getMainThreadHandler() {
        return mMainThradHandler;
    }

    public static Looper getMainThreadLooper() {
        return mMainThradLooper;
    }

    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
