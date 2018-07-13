package com.agoodwater.system.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.CheckEmployeeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class CheckEmployeeListView extends BaseAdapter {

    private Activity mainActivity;
    private List<CheckEmployeeBean.DataBean.DisListBean> goodList;
    private CheckEmployeeBean.DataBean.DisListBean item;

    public CheckEmployeeListView(Activity mainActivity, List<CheckEmployeeBean.DataBean.DisListBean> goodList) {

        this.mainActivity = mainActivity;

        this.goodList = goodList;

    }

    public void setAdapter(List<CheckEmployeeBean.DataBean.DisListBean> newGoodList) {

        goodList = newGoodList;

    }


    @Override
    public int getCount() {
        return goodList.size();
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

            convertView = View.inflate(mainActivity, R.layout.list_employee, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);


        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        item = goodList.get(position);

        holder.tvName.setText(item.getUser_name());
        holder.tvOrderNum.setText(item.getDanNum() + "桶");
        holder.tvBucketNum.setText(item.getTongNum() + "桶");
        holder.tvMoney.setTextColor(mainActivity.getResources().getColor(R.color.colorRed));
        holder.tvMoney.setText(item.getDeposit() + "元");



        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_order_num)
        TextView tvOrderNum;
        @BindView(R.id.tv_bucket_num)
        TextView tvBucketNum;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
