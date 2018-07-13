package com.agoodwater.system.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.SettlementBean;
import com.agoodwater.system.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-13.
 */

public class SettlementAdapter extends RecyclerView.Adapter<SettlementAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<SettlementBean.CompanyendListBean> mList = new ArrayList<>();
    private SettlementBean.CompanyendListBean item;
    private StringBuffer sb;

    public SettlementAdapter(Context context, List<SettlementBean.CompanyendListBean> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        mList.clear();
        mList.addAll(list);

        System.out.println("我的长度是:" + mList.size() + "---" + list.size());
    }

    public void setAdapter(List<SettlementBean.CompanyendListBean> goodList) {

        mList.clear();
        mList.addAll(goodList);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_settlement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder1, final int position) {

//        System.out.println("我的位置是:``````" + position);
        item = mList.get(position);

        holder1.tvNotKnot.setText(TimeUtils.ms2Time(item.getCreateDate()));

        holder1.tvSettlementTicket.setText(item.getGoodsName() + item.getTongNum() + "张");
        holder1.tvNotMoney.setText(item.getPrice() + ".0");

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_not_knot)
        TextView tvNotKnot;
        @BindView(R.id.tv_settlement_ticket)
        TextView tvSettlementTicket;
        @BindView(R.id.tv_not_money)
        TextView tvNotMoney;
        @BindView(R.id.ll_company_unliquidated)
        LinearLayout llCompanyUnliquidated;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}