package com.agoodwater.system.moudle.moudle;

import com.agoodwater.system.activity.EmployeeManagActivity;
import com.agoodwater.system.network.NetWorkUtils;
import com.agoodwater.system.network.NetworkApi;
import com.agoodwater.system.presenter.Employeepresenter;
import com.agoodwater.system.view.EmployeeView;
import com.agoodwater.system.view.LoginView;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:23
 */
@Module
public class EmplyeeActivityModule {

//    private final EmployeeView mView;
//
//    public EmplyeeActivityModule(EmployeeView view) {
//        mView = view;
//    }

//    @Provides
//    EmployeeView provideEmployeeView() {
//        return mView;
//    }



    private EmployeeManagActivity activity ;

    public EmplyeeActivityModule(EmployeeManagActivity activity) {
        this.activity = activity;
    }

    @Provides
    public EmployeeManagActivity provideActivity(){
        return activity;
    }


    @Provides
    public Employeepresenter provideEmployeepresenter(){

        return new Employeepresenter(activity);

    }



}
