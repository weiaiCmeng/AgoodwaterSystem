package com.agoodwater.system.network;

import com.agoodwater.system.bean.BillingBean;
import com.agoodwater.system.bean.BucketCompletedBean;
import com.agoodwater.system.bean.CheckDetailsBean;
import com.agoodwater.system.bean.CheckEmployeeBean;
import com.agoodwater.system.bean.CompanyBean;
import com.agoodwater.system.bean.CompletedBucketBean;
import com.agoodwater.system.bean.CompletedWaterBean;
import com.agoodwater.system.bean.ConfirmOrderBean;
import com.agoodwater.system.bean.EmployeeAddBean;
import com.agoodwater.system.bean.EmployeeBean;
import com.agoodwater.system.bean.EmployeeDeleteBean;
import com.agoodwater.system.bean.LoginBean;
import com.agoodwater.system.bean.LoginHttpsBean;
import com.agoodwater.system.bean.OutstandingBean;
import com.agoodwater.system.bean.QueryDetailsBean;
import com.agoodwater.system.bean.ServionBean;
import com.agoodwater.system.bean.SettlementBean;
import com.agoodwater.system.bean.StoreBean;
import com.agoodwater.system.bean.WaterCompletedBean;

import java.util.Map;

import rx.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 一桶水用户端api联网文档
 * 1.导入retrofit的依赖库
 */
public interface NetworkApi {


    /**
     * 登录操作的请求
     */
    @FormUrlEncoded
    @GET("login/login")
    Observable<LoginHttpsBean> loginHttpsCall(@FieldMap Map<String, String> map);

    /**
     * 登录操作的请求
     */
    @GET("appWater/userLogin.action")
    Observable<LoginBean> loginCall(@QueryMap Map<String, String> map);

    /**
     * 更新版本的网络请求
     */
    @GET("appWater/checkServion.action")
    Observable<ServionBean> updateServerCall(@QueryMap Map<String, String> map);


    /**
     * 未完成订单
     */
    @GET("appWater/unOrderList.action")
    Observable<OutstandingBean> outstandingCall(@QueryMap Map<String, String> map);


    /**
     * 已完成送水订单
     */
    @GET("appWater/userLogin.action")
    Observable<CompletedWaterBean> completedWaterCall(@QueryMap Map<String, String> map);

    /**
     * 已完成退桶订单
     */
    @GET("appWater/userLogin.action")
    Observable<CompletedBucketBean> completedBucketCall(@QueryMap Map<String, String> map);

    /**
     * 门店管理订单
     */
    @GET("appWater/waterAdm.action")
    Observable<StoreBean> storeCall(@QueryMap Map<String, String> map);

    /**
     * 门店管理中查看详情
     */
    @GET("appWater/queryDesc.action")
    Observable<CheckDetailsBean> CheckDetailsCall(@QueryMap Map<String, String> map);

    /**
     * 门店管理中员工统计
     */
    @GET("appWater/songCount.action")
    Observable<CheckEmployeeBean> CheckEmployeeCall(@QueryMap Map<String, String> map);

    /**
     * 已完成送水订单
     */
    @GET("appWater/orderList.action")
    Observable<WaterCompletedBean> waterCompletedCall(@QueryMap Map<String, String> map);

    /**
     * 公司订单
     */
    @GET("companyAccountController/waterCompany.action")
    Observable<CompanyBean> companyCall(@QueryMap Map<String, String> map);

    /**
     * 公司订单记录
     */
    @GET("companyAccountController/waterEndListDetile.action")
    Observable<SettlementBean> settlementCall(@QueryMap Map<String, String> map);

    /**
     * 已完成退桶订单
     */
    @GET("appWater/endTList.action")
    Observable<BucketCompletedBean> bucketCompletedCall(@QueryMap Map<String, String> map);

    /**
     * 分配送水工确认订单
     */
    @GET("appWater/disOrder.action")
    Observable<ConfirmOrderBean> confirmOrderCall(@QueryMap Map<String, String> map);

    /**
     * 员工管理进入页面
     */
    @GET("appWater/songAdm.action")
    Observable<EmployeeBean> employeeCall(@QueryMap Map<String, String> map);

    /**
     * 员工删除功能
     */
    @GET("appWater/songDel.action")
    Observable<EmployeeDeleteBean> employeeDeleteCall(@QueryMap Map<String, String> map);

    /**
     * 添加员工功能
     */
    @GET("appWater/songAdd.action")
    Observable<EmployeeAddBean> employeeAddCall(@QueryMap Map<String, String> map);

    /**
     * 账单结算界面
     */
    @GET("appWater/accountClose.action")
    Observable<BillingBean> billingCall(@QueryMap Map<String, String> map);

    /**
     * 查看订单详情
     */
    @GET("appWater/orderChe.action")
    Observable<QueryDetailsBean> queryDetailsCall(@QueryMap Map<String, String> map);

}
