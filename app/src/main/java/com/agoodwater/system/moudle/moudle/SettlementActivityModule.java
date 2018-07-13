package com.agoodwater.system.moudle.moudle;

import com.agoodwater.system.contract.CompanyActivityContract;
import com.agoodwater.system.contract.SettlementActivityContract;

import dagger.Module;
import dagger.Provides;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:23
 */
@Module
public class SettlementActivityModule {

    private  SettlementActivityContract.View mView;

    public SettlementActivityModule(SettlementActivityContract.View view) {
        mView = view;
    }

    @Provides
    SettlementActivityContract.View provideSettlementView() {
        return mView;
    }









}
