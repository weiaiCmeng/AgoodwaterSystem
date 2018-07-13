package com.agoodwater.system.update;

import com.agoodwater.system.network.NetworkApi;
import com.agoodwater.system.network.RetrofitServiceFactory;
import com.agoodwater.system.network.UseCase;

import java.util.Map;

import rx.Observable;

/**
 * Created by shiqiang on 2017/4/26.
 */

public class UpdateFractory extends UseCase{

    public Map<String, String> map;
    private NetworkApi appService;

    private String name ;

    private static volatile UpdateFractory updateFractory ;

    private UpdateFractory(String name , Map<String,String> map){
        super();
        this.name = name ;
        this.map = map;
        appService = RetrofitServiceFactory.getAppService();

    }

    private static UpdateFractory getUpdateFractory(String name , Map<String,String> map){

        if (updateFractory == null){

            updateFractory = new UpdateFractory(name , map);

        }else{

            updateFractory.setMap(name , map);

        }

        return updateFractory ;

    }

    private void setMap(String name , Map<String,String> map) {

        this.name = name ;
        this.map = map ;
    }


    @Override
    protected Observable buildUseCaseObservable() {

        switch (this.name){
            case "loginHttpsCall":

                return appService.loginHttpsCall(map) ;

            case "billingcall":

                return appService.billingCall(map) ;

            case "bucketCompletedCall" :

                return appService.bucketCompletedCall(map);

            case "CheckDetailsCall" :

                return appService.CheckDetailsCall(map);

            case "CheckEmployeeCall" :

                return appService.CheckEmployeeCall(map);

            case "storeCall" :

                return appService.storeCall(map);

            case "confirmOrderCall" :

                return appService.confirmOrderCall(map);

            case "employeeAddCall" :

                return appService.employeeAddCall(map);

            case "employeeDeleteCall" :

                return appService.employeeDeleteCall(map);

            case "employeeCall" :


                return appService.employeeCall(map);

            case "loginCall" :

                return appService.loginCall(map);

            case "outstandingCall" :

                return appService.outstandingCall(map);

            case "queryDetailsCall" :

                return appService.queryDetailsCall(map);


            case "updateServerCall" :

                return appService.updateServerCall(map);

            case "waterCompletedCall" :

                return appService.waterCompletedCall(map);

            case "companyCall" :

                return appService.companyCall(map);

            case "settlementCall" :

                return appService.settlementCall(map);

        }


        return null ;
    }


    public static Build getBuild(){

        return Build.getBuild();
    }


    public static class Build{


        private Build(){}

        private static final Build single = new Build();

        public static Build getBuild(){

            return single ;

        }

        private Map<String, String> map;
        private String name ;

        public Build map (Map<String, String> map) {
            this.map = map ;
            return this;
        }


        public Build name(String name) {
            this.name = name;
            return this ;
        }

        public UseCase build(){

            return  UpdateFractory.getUpdateFractory(this.name ,this.map);
        }



    }



}
