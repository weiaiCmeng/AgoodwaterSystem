package com.agoodwater.system.components;

import com.agoodwater.system.activity.CompanyActivity;
import com.agoodwater.system.activity.SettlementRecordActivity;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.CompanyActivityModule;
import com.agoodwater.system.moudle.moudle.PerActivity;
import com.agoodwater.system.moudle.moudle.SettlementActivityModule;

import dagger.Component;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 17:16
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = {SettlementActivityModule.class , ActivityModule.class})
public interface SettlementActivityComponent extends BaseActivityComponent{

    void inject(SettlementRecordActivity activity);


}
