package com.agoodwater.system.network;

import java.util.Map;

import rx.Observable;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/10 15:45
 */
public class EmployeeUser extends UseCase {


    private NetworkApi networkApi ;

    private Map<String ,String> map;

    public EmployeeUser(NetworkApi networkApi, Map<String, String> map) {
        this.networkApi = networkApi;
        this.map = map;
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return networkApi.employeeCall(map);
    }
}
