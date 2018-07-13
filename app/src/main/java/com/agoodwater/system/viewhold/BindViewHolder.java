package com.agoodwater.system.viewhold;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by shiqiang on 2017/4/26.
 */

public class BindViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {


    private T mBinding ;
    public BindViewHolder(T binding) {
        super(binding.getRoot());
        this.mBinding = binding ;
    }

    public T getBinding(){
        return mBinding ;
    }
}
