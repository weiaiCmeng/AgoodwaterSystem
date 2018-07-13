package com.agoodwater.system.view;

import com.agoodwater.system.bean.EmployeeAddBean;
import com.agoodwater.system.bean.EmployeeBean;
import com.agoodwater.system.bean.EmployeeDeleteBean;

/**
 * Created by shiqiang on 2017/3/24.
 */

public interface EmployeeView {

    String getUserName();

    String getSongNum();

    String getAddSongName();

    String getAddSongPhone();

    void initSuccess(EmployeeBean employeeBean);

    void error(String error);

    void deleteSuccess(EmployeeDeleteBean employeeDeleteBean);


    void addSuccess(EmployeeAddBean employeeAddBean);




}
