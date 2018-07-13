package com.agoodwater.system.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.activity.CheckDetailsAcitivity;
import com.agoodwater.system.bean.CheckDetailsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shiqiang on 2017/3/22.
 */
public class CheckDetailsListView extends BaseAdapter {

    private CheckDetailsAcitivity mainActivity;
    private List<CheckDetailsBean.DataBean.SalesDetileListBean> goodList;
    private CheckDetailsBean.DataBean.SalesDetileListBean item;

    public CheckDetailsListView(CheckDetailsAcitivity mainActivity, List<CheckDetailsBean.DataBean.SalesDetileListBean> goodList) {

        this.mainActivity = mainActivity;

        this.goodList = goodList;

    }

    public void setAdapter(List<CheckDetailsBean.DataBean.SalesDetileListBean> newGoodList) {

        goodList = newGoodList ;

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

            convertView = View.inflate(mainActivity, R.layout.list_water, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);


        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        item = goodList.get(position);

        holder.tvName.setText(item.getGoodsName());
        holder.tvAllOrder.setText(item.getGoodsNum() + "桶");
        holder.tvAllBucket.setText(item.getTotal() + "元");




        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_all_order)
        TextView tvAllOrder;
        @BindView(R.id.tv_all_bucket)
        TextView tvAllBucket;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
