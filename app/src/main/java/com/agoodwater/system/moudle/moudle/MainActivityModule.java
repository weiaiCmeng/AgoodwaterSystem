package com.agoodwater.system.moudle.moudle;

import com.agoodwater.system.MainActivity;
import com.agoodwater.system.activity.EmployeeManagActivity;
import com.agoodwater.system.presenter.Employeepresenter;
import com.agoodwater.system.view.OutstandingView;

import dagger.Module;
import dagger.Provides;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:23
 */
@Module
public class MainActivityModule {

    private MainActivity mView ;
    private OutstandingView outstandingView ;
    public MainActivityModule(MainActivity mView) {
        this.mView = mView;
    }
    @Provides
    MainActivity provideMainActivity(){
        return mView ;
    }

    public MainActivityModule(OutstandingView outstandingView) {
        this.outstandingView = outstandingView;
    }
    @Provides
    public OutstandingView provideOutstandingOrder(){
        return outstandingView;
    }


}
