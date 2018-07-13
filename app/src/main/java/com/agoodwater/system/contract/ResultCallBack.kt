package com.agoodwater.system.contract

/**
 * created by shi on 2018/7/6/006
 *
 */
interface ResultCallBack<T> {
    fun onSuccess(t : T)
    fun onFailure(error : String)
}