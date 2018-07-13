package com.agoodwater.system.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 对Activity进行退出操作等
 * Created by shiqiang on 2016/9/14.
 */
public class ActivityUtils {

    private List<Activity> mList = new LinkedList<Activity>();
    private static ActivityUtils instance;


    public synchronized static ActivityUtils getInstance() {
        if (null == instance) {
            instance = new ActivityUtils();
        }
        return instance;
    }


    // 添加 Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    //退出activity
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

}
