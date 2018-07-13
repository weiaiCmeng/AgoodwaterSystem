package com.agoodwater.system.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by jason on 2016/8/19.
 * des:${des}
 */
public class CommonUtils {



    /**
     * 获取分辨率_宽
     *
     * @param activity
     * @return
     */
    public static int getWidthPixels(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取分辨率_高
     *
     * @param activity
     * @return
     */
    public static int getHeightPixels(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
