package com.agoodwater.system.presenter;


import android.support.v4.util.ArrayMap;

import com.agoodwater.system.bean.EmployeeAddBean;
import com.agoodwater.system.bean.EmployeeBean;
import com.agoodwater.system.bean.EmployeeDeleteBean;
import com.agoodwater.system.network.EmployeeUser;
import com.agoodwater.system.network.NetworkApi;
import com.agoodwater.system.network.ResponseSubscriber;
import com.agoodwater.system.update.UpdateFractory;
import com.agoodwater.system.view.EmployeeView;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by shiqiang on 2017/3/24.
 */

public class Employeepresenter implements EmployeeInfo {


    private EmployeeView mIview ;
    private String userName;
    private Map<String, String> map;
    private Map<String, String> deleteMap;
    private Map<String, String> addMap;

    public Employeepresenter(EmployeeView mIview ){
        this.mIview = mIview ;
    }

   /* @Inject
    Employeepresenter(EmployeeView mIview) {

        this.mIview = mIview ;
    }*/

    @Override
    public void loginNet() {


        userName = mIview.getUserName();

        map = new ArrayMap<>();
        map.put("userName" , userName);


//        new EmployeeUser(networkApi , map)

        UpdateFractory.getBuild()
                .name("employeeCall")
                .map(map)
                .build()
        .execute(new ResponseSubscriber<EmployeeBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(EmployeeBean employeeBean) {

                int infoCode = employeeBean.getInfoCode();

                System.out.println("我是Presenter:~~~" + employeeBean.getData().size());

                if (infoCode == 200){

                    mIview.initSuccess(employeeBean);
                }else{

                    mIview.error("服务器繁忙,请稍后重试!");
                }

            }
        });






    }

    @Override
    public void deleteNet() {


        String songNum = mIview.getSongNum();


        deleteMap = new ArrayMap<>();
        deleteMap.put("songNum" , songNum);

        UpdateFractory.getBuild()
                .name("employeeDeleteCall")
                .map(deleteMap)
                .build()

        .execute(new ResponseSubscriber<EmployeeDeleteBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(EmployeeDeleteBean employeeDeleteBean) {

                int infoCode = employeeDeleteBean.getInfoCode();

                if (infoCode == 200){

                    mIview.deleteSuccess(employeeDeleteBean);
                }else{

                    mIview.error("服务器繁忙,请稍后重试!");
                }

            }
        });






    }

    @Override
    public void addNet() {

        String userName = mIview.getUserName();
        String addSongName = mIview.getAddSongName();
        String addSongPhone = mIview.getAddSongPhone();


        addMap = new ArrayMap<>();
        addMap.put("userName" , userName);
        addMap.put("songName" , addSongName);
        addMap.put("phone" , addSongPhone);


        UpdateFractory.getBuild()
                .name("employeeAddCall")
                .map(addMap)
                .build()

        .execute(new ResponseSubscriber<EmployeeAddBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
            }

            @Override
            public void onSuccess(EmployeeAddBean employeeAddBean) {

                int infoCode = employeeAddBean.getInfoCode();

                if (infoCode == 200){

                    mIview.addSuccess(employeeAddBean);
                }else{

                    mIview.error("该用户已存在,请勿重复提交!");
                }

            }
        });







    }
}
