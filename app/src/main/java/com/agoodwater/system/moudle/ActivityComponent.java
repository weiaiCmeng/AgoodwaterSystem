package com.agoodwater.system.moudle;

import com.agoodwater.system.activity.EmployeeManagActivity;
import com.agoodwater.system.components.AppComponent;
import com.agoodwater.system.moudle.moudle.EmplyeeActivityModule;
import com.ljd.freewords.di.annotation.ActivityScope;

import dagger.Component;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:27
 */
@ActivityScope
@Component(dependencies = AppComponent.class , modules = EmplyeeActivityModule.class)
public interface ActivityComponent {

    void inject(EmployeeManagActivity activity);
}
