package com.agoodwater.system.modeladapter;

/**
 * Created by shiqiang on 2017/3/30.
 */

public class AdapterFractory {


    private AdapterInfo adapterInfo;

   public void   getAdapter(String name) {


       if (name.equals("Android")){

           adapterInfo = new AndroidAdapter();
           adapterInfo.setSAdapter();

       }else{

           adapterInfo = new JavaAdapter();
           adapterInfo.setSAdapter();


       }

    }


}
