package com.agoodwater.system.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by David.Li on 2016/3/6.
 */
public class SpUtils {
    /**
     * 得到存储的boolean值
     * @param context
     * @param key
     * @param value 默认的值
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sp.getBoolean(key, value);
    }

    /**
     * 存储boolean的值
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static String getString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sp.getString(key, value);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static int getInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sp.getInt(key, value);
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
}
