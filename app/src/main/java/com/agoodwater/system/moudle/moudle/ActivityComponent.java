package com.agoodwater.system.moudle.moudle;

import com.agoodwater.system.activity.EmployeeManagActivity;
import com.agoodwater.system.components.AppComponent;

import dagger.Component;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:27
 */
@PerActivity
@Component(dependencies = AppComponent.class , modules = {EmplyeeActivityModule.class ,ActivityModule.class})
public interface ActivityComponent {

    void inject(EmployeeManagActivity activity);
}
