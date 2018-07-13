package com.agoodwater.system.utils;

/**
 * Created by shiqiang on 2016/8/16.
 */
public class ButtonUtils{

    //如果为false可点,为true不可点
        private static long lastClickTime;
        public synchronized static boolean isFastClick() {
            long time = System.currentTimeMillis();
            if ( time - lastClickTime < 5000) {
                return true;
            }
            lastClickTime = time;
            return false;
    }


    public synchronized static boolean isFastClick10() {
            long time = System.currentTimeMillis();
            if ( time - lastClickTime < 10000) {
                return true;
            }
            lastClickTime = time;
            return false;
    }
}
