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
import com.agoodwater.system.bean.CompanyBean;
import com.agoodwater.system.utils.SpannableStringUtils;
import com.agoodwater.system.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-13.
 */

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<CompanyBean.CompanyAccountListBean> mList = new ArrayList<>();
    private CompanyBean.CompanyAccountListBean item;
    private StringBuffer sb;

    public CompanyAdapter(Context context, List<CompanyBean.CompanyAccountListBean> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        mList.clear();
        mList.addAll(list);

        System.out.println("我的长度是:" + mList.size() + "---" + list.size());
    }

    public void setAdapter(List<CompanyBean.CompanyAccountListBean> goodList) {

        mList.clear();
        mList.addAll(goodList);

    }


    /**
     * add onItemClick begin
     */
    public interface OnRecyclerViewItemClickListener {
        void onClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder1, final int position) {

//        System.out.println("我的位置是:``````" + position);
        item = mList.get(position);

        if (item.getTranType() == 1) {

            holder1.llSurplusTicket.setVisibility(View.VISIBLE);
            holder1.llCompanyUnliquidated.setVisibility(View.GONE);
            holder1.btSettlementRecord.setVisibility(View.GONE);

            //剩余水票
            int surplusNum = item.getPiaoNum() - Integer.parseInt(item.getSongNum());
            holder1.tvSurplus.setText("");
            holder1.tvSurplus.append(item.getPiaoName());
            holder1.tvSurplus.append(SpannableStringUtils.addForeColorSpan(surplusNum));
            holder1.tvSurplus.append("张");


        } else {

            holder1.llSurplusTicket.setVisibility(View.GONE);
            holder1.llCompanyUnliquidated.setVisibility(View.VISIBLE);
            holder1.btSettlementRecord.setVisibility(View.VISIBLE);

            holder1.tvNotKnot.setText(item.getPiaoName() + (Integer.parseInt(item.getSongNum()) - item.getWaterTong()) + "张");

            holder1.tvNotMoney.setText((Integer.parseInt(item.getSongNum()) * item.getWaterPrice() - item.getWaterTong() * item.getWaterPrice()) + ".0");
            holder1.tvBalanceTime.setText(TimeUtils.ms2Time(item.getEndDate()));

        }


        holder1.btSettlementRecord.setTag(position);
        holder1.btSettlementRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("我被点击了");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v);
                }
            }
        });

        holder1.tvCompanyName.setText(item.getCompanyName());
        holder1.tvUserName.setText(item.getName());
        holder1.tvUserPhone.setText(item.getCompanyPhone());
        holder1.tvUserAddress.setText(item.getCompanyAddress());
        holder1.tvHasUser.setText(item.getPiaoName() + item.getSongNum() + "张");
        holder1.tvTime.setText(TimeUtils.ms2Time(item.getCreateTime()));

        holder1.btWaterRecord.setTag(position);
        holder1.btWaterRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("我被点击了");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v);
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }




    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_company)
        ImageView ivCompany;
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_phone)
        TextView tvUserPhone;
        @BindView(R.id.tv_user_address)
        TextView tvUserAddress;
        @BindView(R.id.tv_surplus)
        TextView tvSurplus;
        @BindView(R.id.ll_surplus_ticket)
        LinearLayout llSurplusTicket;
        @BindView(R.id.tv_has_user)
        TextView tvHasUser;
        @BindView(R.id.tv_not_knot)
        TextView tvNotKnot;
        @BindView(R.id.tv_not_money)
        TextView tvNotMoney;
        @BindView(R.id.tv_balance_time)
        TextView tvBalanceTime;
        @BindView(R.id.ll_company_unliquidated)
        LinearLayout llCompanyUnliquidated;
        @BindView(R.id.bt_settlement_record)
        Button btSettlementRecord;
        @BindView(R.id.bt_water_record)
        Button btWaterRecord;
        @BindView(R.id.cardview)
        CardView cardview;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}