package com.agoodwater.system.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.agoodwater.system.App;

/**
 * Created by shiqiang on 2017/4/17.
 */

public class VersionUtils {

    public static PackageManager pm = App.getApplication().getPackageManager();

    /**
     *
     * 获取当前软件版本号
     * return 当前版本号
     */
    public static int getCurrentVersionCode(){

        PackageInfo pinfo = null;
        try {
            pinfo = pm.getPackageInfo(App.getApplication().getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("MyTest","获取当前版本号时出现异常"+e);
        }
        int versionCode = pinfo.versionCode;
        Log.i("MyTest","当前版本号："+versionCode);
        return versionCode;
    }



}
