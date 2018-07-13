package com.agoodwater.system.update;

import com.agoodwater.system.network.RetrofitServiceFactory;
import com.agoodwater.system.network.UseCase;

import java.util.Map;

import rx.Observable;

/**
 * Created by shiqiang on 2017/2/23.
 */
public class EmployeeDeleteUpdate extends UseCase {
    public Map<String, String> map;

    public EmployeeDeleteUpdate(Map<String,String> map){
        super();
        this.map = map;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return RetrofitServiceFactory.getAppService().employeeDeleteCall(map);
    }
}
