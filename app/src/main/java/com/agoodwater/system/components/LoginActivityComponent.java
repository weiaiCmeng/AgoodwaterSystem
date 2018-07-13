package com.agoodwater.system.components;

import com.agoodwater.system.activity.EmployeeManagActivity;
import com.agoodwater.system.activity.LoginActivity;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.EmplyeeActivityModule;
import com.agoodwater.system.moudle.moudle.LoginActivityModule;
import com.agoodwater.system.moudle.moudle.PerActivity;
import com.agoodwater.system.presenter.LoginPresenter;

import dagger.Component;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 17:16
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = {LoginActivityModule.class , ActivityModule.class})
public interface LoginActivityComponent extends BaseActivityComponent{

    void inject(LoginActivity activity);


}
