package com.agoodwater.system.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.WaterCompletedBean;
import com.agoodwater.system.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-13.
 */

public class WaterDetailsAdapter extends RecyclerView.Adapter<WaterDetailsAdapter.ViewHolder>  {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WaterCompletedBean.DataBean.OrderListBean> mList = new ArrayList<>();
    private WaterCompletedBean.DataBean.OrderListBean item;
    private StringBuffer sb;

    public WaterDetailsAdapter(Context context, List<WaterCompletedBean.DataBean.OrderListBean> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        mList.clear();
        mList.addAll(list);

        System.out.println("我的长度是:" + mList.size() + "---" + list.size());
    }

    public void setAdapter(List<WaterCompletedBean.DataBean.OrderListBean> goodList) {

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
        View view = inflater.inflate(R.layout.order_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder1, final int position) {

//        System.out.println("我的位置是:``````" + position);
        item = mList.get(position);




        holder1.tvBucketClassify.setText(item.getGoods_name());

        holder1.tvProductBucketNumbers.setText("X" + mContext.getResources().getString(R.string.out_bound_submit1) + item.getGoods_num());


        holder1.tvUserName.setText(item.getShip_name());

        holder1.tvUserPhoneNumber.setText(item.getShip_mobile_phone());

        holder1.tvUserAddress.setText(item.getShip_address());

        String substring = item.getSend_time().substring(0, 6);
        System.out.println(substring);
        holder1.tvProductComplectedDate.setText(TimeUtils.strToTime(substring));
        holder1.tvProductComplectedTime.setText(item.getSend_time_range());


        //设置是否配送


        //是否是送水已完成的确认 2 为已完成
        int isFinish = item.getIsFinish();

        //未完成订单
        if (isFinish == 1) {
            holder1.btnConfirm.setVisibility(View.VISIBLE);
            holder1.btnConfirm.setText("重新分配");
            holder1.tvProductBucketNumbers.setTextColor(mContext.getResources().getColor(R.color.colorRed));
            holder1.llIsBack.setVisibility(View.GONE);
            holder1.tvSendDate.setTextColor(mContext.getResources().getColor(R.color.colorRed));
            holder1.tvSendTime.setTextColor(mContext.getResources().getColor(R.color.colorRed));
            holder1.tvSendDate.setText(TimeUtils.ms2Time(System.currentTimeMillis()));
            holder1.tvSendTime.setText(TimeUtils.ms2DetailTime(System.currentTimeMillis()) + "(" + item.getSongName() + ")" + "正在配送");


        } else {


            holder1.btnConfirm.setVisibility(View.GONE);
            holder1.tvProductBucketNumbers.setTextColor(mContext.getResources().getColor(R.color.colorBlue));
            holder1.tvBackMoney.setTextColor(mContext.getResources().getColor(R.color.colorBlue));
            holder1.tvBackBrand.setTextColor(mContext.getResources().getColor(R.color.colorBlue));
            holder1.llIsBack.setVisibility(View.VISIBLE);
            holder1.tvSendDate.setTextColor(mContext.getResources().getColor(R.color.colorBlue));
            holder1.tvSendTime.setTextColor(mContext.getResources().getColor(R.color.colorBlue));
            holder1.tvSendDate.setText(TimeUtils.ms2Time(item.getSongSendTime()));
            holder1.tvSendTime.setText(TimeUtils.ms2DetailTime(item.getSongSendTime()) + "(" + item.getSongName() + ")" + "已送达");

            holder1.tvBackMb.setText("押金为:");
            holder1.tvBackBb.setText("回桶为:");
            holder1.tvBackMoney.setText(item.getDeposit() + "元");
//            tring reTgoods, String reTnum
            holder1.tvBackBrand.setText(getBrandNumber(item.getReTgoods(), item.getReTnum()));

        }

        holder1.tvUserRemark.setText(item.getRemarks());
        holder1.tvStoreRemarks.setText(item.getYsjremark());


        holder1.btnConfirm.setTag(position);

        holder1.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("我被点击了");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v);
                }
            }
        });

        holder1.ivPhoneCall.setTag(position);


        holder1.ivPhoneCall.setOnClickListener(new View.OnClickListener() {
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

     class ViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv_bucket_classify)
        TextView tvBucketClassify;
        @BindView(R.id.tv_product_bucket_numbers)
        TextView tvProductBucketNumbers;
        @BindView(R.id.iv_phone_call)
        ImageView ivPhoneCall;
        @BindView(R.id.tv_user_phone_number)
        TextView tvUserPhoneNumber;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.iv_location)
        ImageView ivLocation;
        @BindView(R.id.tv_user_address)
        TextView tvUserAddress;
        @BindView(R.id.iv_i)
        ImageView ivI;
        @BindView(R.id.rl_user_address)
        RelativeLayout rlUserAddress;
        @BindView(R.id.tv_product_complected_date)
        TextView tvProductComplectedDate;
        @BindView(R.id.tv_product_complected_time)
        TextView tvProductComplectedTime;
        @BindView(R.id.tv_user_remark)
        TextView tvUserRemark;
        @BindView(R.id.rl_user_suggestion)
        RelativeLayout rlUserSuggestion;
        @BindView(R.id.tv_store_remarks)
        TextView tvStoreRemarks;
        @BindView(R.id.rl_remarks)
        RelativeLayout rlRemarks;
        @BindView(R.id.tv_send_date)
        TextView tvSendDate;
        @BindView(R.id.tv_send_time)
        TextView tvSendTime;
        @BindView(R.id.btn_confirm)
        Button btnConfirm;
        @BindView(R.id.rl_order_confirm)
        RelativeLayout rlOrderConfirm;
        @BindView(R.id.tv_back_mb)
        TextView tvBackMb;
        @BindView(R.id.tv_back_money)
        TextView tvBackMoney;
        @BindView(R.id.tv_back_bb)
        TextView tvBackBb;
        @BindView(R.id.tv_back_brand)
        TextView tvBackBrand;
        @BindView(R.id.ll_is_back)
        LinearLayout llIsBack;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public String getBrandNumber(String reTgoods, String reTnum) {


        sb = new StringBuffer();
        if (!TextUtils.isEmpty(reTgoods)) {
            String[] splitGoods = reTgoods.split(",");
            String[] splitNums = reTnum.split(",");
            for (int i = 0; i < splitGoods.length; i++) {

                System.out.println(splitGoods[i] + "----" + splitNums[i] + "\n");
                sb.append(splitGoods[i] + "X" + splitNums[i] + mContext.getResources().getString(R.string.out_bound_submit1));


            }

            System.out.println(sb.toString() + "我的品牌家数量");

        } else {
            sb.append("此单无回桶");
        }


        System.out.println(sb + "woshi");

        return sb.toString();

    }

   /* class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_order_num)
        TextView tvOrderNum;
        @BindView(R.id.tv_bucket_num)
        TextView tvBucketNum;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }*/
}