package com.agoodwater.system.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by shiqiang on 2016/9/18.
 */
public class UserNameUtils {

    /**
     * 判断用户名是否为空,true为空 false有值
     * @param context
     * @return
     */
    public static boolean isEmpty(Context context) {
        return (TextUtils.isEmpty(SpUtils.getString(context, "userId", "")));
    }



}
