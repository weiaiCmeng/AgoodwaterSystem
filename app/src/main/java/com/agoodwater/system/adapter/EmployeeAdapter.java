package com.agoodwater.system.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.EmployeeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shiqiang on 2016/9/28.
 */
public class EmployeeAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity mActivity;
    private int position;
    private Callback mCallback;
    private EmployeeBean.DataBean orderListBean;
    private List<EmployeeBean.DataBean> orderList;


    public EmployeeAdapter(Activity mainActivity, EmployeeBean employeeBean, Callback callback) {

        this.mActivity = mainActivity;

        this.mCallback = callback;

        //得到订单情况

        orderList = employeeBean.getData();

        if (orderList != null) {
            position = orderList.size();
        } else {
            position = 0;
        }

    }


    public void setListwaterOrder(EmployeeBean employeeBean){
        //得到订单情况

        orderList = employeeBean.getData();

        if (orderList != null) {
            position = orderList.size();
        } else {
            position = 0;
        }


    }

    @Override
    public int getCount() {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            convertView = View.inflate(mActivity, R.layout.employee_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);


        } else {

            holder = (ViewHolder) convertView.getTag();


        }


        //查看是送水单还是退桶单

        orderListBean = orderList.get(position);

        holder.tvName.setText(orderListBean.getUser_name() + "(" + orderListBean.getUser_mobile_phone() + ")");

        holder.tvPassword.setText(orderListBean.getPassWord());
        holder.ivDelete.setOnClickListener(this);
        holder.ivDelete.setTag(position);

        return convertView;
    }

    @Override
    public void onClick(View v) {

        mCallback.click(v);
    }


    public interface Callback {
        void click(View v);
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_password)
        TextView tvPassword;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
