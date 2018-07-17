package com.agoodwater.system.components;

import com.agoodwater.system.MainActivity;
import com.agoodwater.system.activity.LoginActivity;
import com.agoodwater.system.fragment.OutstandingOrderPage;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.LoginActivityModule;
import com.agoodwater.system.moudle.moudle.MainActivityModule;
import com.agoodwater.system.moudle.moudle.PerActivity;

import dagger.Component;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 17:16
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = {MainActivityModule.class , ActivityModule.class})
public interface MainActivityComponent extends BaseActivityComponent{

    void inject(MainActivity activity);
    void inject(OutstandingOrderPage fragment);


}
