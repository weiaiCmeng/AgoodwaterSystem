package com.agoodwater.system.utils;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by shiqiang on 2016/10/8.
 */
public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(File file);
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
