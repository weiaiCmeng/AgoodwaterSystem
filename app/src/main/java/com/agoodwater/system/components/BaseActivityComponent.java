package com.agoodwater.system.components;

import android.app.Activity;

import com.agoodwater.system.moudle.moudle.ActivityComponent;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.PerActivity;

import dagger.Component;
import dagger.Module;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 16:22
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface BaseActivityComponent {
    Activity getActivity();
}
