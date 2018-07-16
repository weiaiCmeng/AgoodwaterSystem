package com.agoodwater.system.moudle.moudle

import com.agoodwater.system.activity.LoginActivity
import com.agoodwater.system.contract.LoginContract
import com.agoodwater.system.model.LoginModel
import com.agoodwater.system.presenter.LoginPresenter
import com.agoodwater.system.view.LoginView

import javax.inject.Named

import dagger.Module
import dagger.Provides

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 17:13
 */
@Module
class LoginActivityModule(private val mView: LoginContract.View) {

    @Provides
    internal fun provideLoginContractView(): LoginContract.View {
        return mView
    }

    @Provides
    fun provideModel(): LoginContract.Model {
        return LoginModel()
    }

    @Named("presenter")
    @Provides
    fun providePresenter(model: LoginContract.Model): LoginPresenter {
        return LoginPresenter(this.mView, model)
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
