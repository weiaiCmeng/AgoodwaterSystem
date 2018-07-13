package com.agoodwater.system.moudle.moudle;

import com.agoodwater.system.activity.CompanyActivity;
import com.agoodwater.system.activity.EmployeeManagActivity;
import com.agoodwater.system.contract.CompanyActivityContract;
import com.agoodwater.system.presenter.Employeepresenter;
import com.agoodwater.system.view.EmployeeView;

import dagger.Module;
import dagger.Provides;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:23
 */
@Module
public class CompanyActivityModule {

    private  CompanyActivityContract.View mView;

    public CompanyActivityModule(CompanyActivityContract.View view) {
        mView = view;
    }

    @Provides
    CompanyActivityContract.View provideCompanyView() {
        return mView;
    }









}
