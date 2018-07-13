package com.agoodwater.system.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.BillingBean;
import com.agoodwater.system.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shiqiang on 2016/9/28.
 */
public class BillingAdapter extends BaseAdapter {

    private Context mActivity;
    private int position;
    private List<BillingBean.DataBean.WaterAccountListBean> waterAccountList;
    private BillingBean.DataBean.WaterAccountListBean waterAccountListBean;


    public BillingAdapter(Context mainActivity, List<BillingBean.DataBean.WaterAccountListBean> waterList) {

        this.mActivity = mainActivity;

        //得到订单情况


        waterAccountList = waterList ;


        if (waterAccountList != null) {
            position = waterAccountList.size();
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

            convertView = View.inflate(mActivity, R.layout.billing_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        waterAccountListBean = waterAccountList.get(position);
        if (waterAccountListBean.getStatus() == 1){

            holder.tvMoney.setTextColor(mActivity.getResources().getColor(R.color.colorNumber));
            holder.tvDate.setTextColor(mActivity.getResources().getColor(R.color.colorNumber));

        }else{
            holder.tvMoney.setTextColor(mActivity.getResources().getColor(R.color.colorRed));
            holder.tvDate.setTextColor(mActivity.getResources().getColor(R.color.colorRed));
        }

        holder.tvDate.setText(TimeUtils.ms2Time(waterAccountListBean.getCreateTime()) + "(" + waterAccountListBean.getWeekDay() + ")");
        holder.tvMoney.setText(waterAccountListBean.getPrice() + "元");

        return convertView;
    }


    public void setListwaterOrder(List<BillingBean.DataBean.WaterAccountListBean> newWaterAccountList) {


        //将传递进来的集合设置给waterOrder
        waterAccountList = newWaterAccountList;

        if (waterAccountList == null) {

            position = 0;

        } else {

            position = waterAccountList.size();

        }



    }


    static class ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
