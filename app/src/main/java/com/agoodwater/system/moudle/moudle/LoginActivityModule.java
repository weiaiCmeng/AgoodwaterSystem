package com.agoodwater.system.moudle.moudle;

import com.agoodwater.system.activity.LoginActivity;
import com.agoodwater.system.presenter.LoginPresenter;
import com.agoodwater.system.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 17:13
 */
@Module
public class LoginActivityModule {


    private final LoginView mView;

    public LoginActivityModule(LoginView view) {
        mView = view;
    }

    @Provides
    LoginView provideLoginView() {
        return mView;
    }
/*

    private LoginActivity activity ;

    public LoginActivityModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    public LoginActivity provideActivity(){
        return activity ;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(){

        return new LoginPresenter(activity);

    }

*/


}
