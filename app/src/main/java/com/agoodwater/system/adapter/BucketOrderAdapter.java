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
import com.agoodwater.system.bean.BucketCompletedBean;
import com.agoodwater.system.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-13.
 */

public class BucketOrderAdapter extends RecyclerView.Adapter<BucketOrderAdapter.ViewHolder>  {

    private Context mActivity;
    private LayoutInflater inflater;
    private List<BucketCompletedBean.DataBean.EndTorderListBean> mList = new ArrayList<>();
    private BucketCompletedBean.DataBean.EndTorderListBean item;
    private StringBuffer sb;

    public BucketOrderAdapter(Context context, List<BucketCompletedBean.DataBean.EndTorderListBean> list) {
        this.mActivity = context;
        inflater = LayoutInflater.from(mActivity);
        mList.clear();
        mList.addAll(list);

        System.out.println("我的长度是:" + mList.size() + "---" + list.size());
    }

    public void setAdapter(List<BucketCompletedBean.DataBean.EndTorderListBean> goodList) {

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


        holder1.tvBucketClassify.setText("退桶");

        if (!TextUtils.isEmpty(item.getT_Number() + "")) {

            holder1.tvProductBucketNumbers.setText("X" + mActivity.getResources().getString(R.string.out_bound_submit1) + item.getT_Number());
        }


        holder1.tvUserName.setText(item.getUserName());

        holder1.tvUserPhoneNumber.setText(item.getUserTp());

        holder1.tvUserAddress.setText(item.getUserAddress());

//                String[] split = item.getSend_time().split(" ");
//


        holder1.tvProductComplectedDate.setText(TimeUtils.ms2Time(item.getOrderTime()));
        holder1.tvProductComplectedTime.setText("下单时间");
//        holder1.tvProductComplectedTime.setVisibility(View.GONE);


        //是否是送水已完成的确认 1为未完成2为未完成
        int isFinish = item.getStatus();

        if (isFinish == 1) {

            holder1.btnConfirm.setVisibility(View.VISIBLE);
            holder1.tvProductBucketNumbers.setTextColor(mActivity.getResources().getColor(R.color.colorRed));

            holder1.llIsBack.setVisibility(View.GONE);
            holder1.tvSendDate.setTextColor(mActivity.getResources().getColor(R.color.colorRed));
            holder1.tvSendTime.setTextColor(mActivity.getResources().getColor(R.color.colorRed));
            holder1.tvSendDate.setText(TimeUtils.ms2Time(System.currentTimeMillis()));
            holder1.tvSendTime.setText(TimeUtils.ms2DetailTime(System.currentTimeMillis()) + "正在退桶");
            holder1.btnConfirm.setEnabled(false);
            holder1.btnConfirm.setBackgroundResource(R.drawable.bule_button_clicked);
            holder1.btnConfirm.setText("正在退桶");
        } else {
            //已完成
            //送达时间的确定
            holder1.btnConfirm.setVisibility(View.GONE);
            holder1.tvProductBucketNumbers.setTextColor(mActivity.getResources().getColor(R.color.colorBlue));
            holder1.tvBackMoney.setTextColor(mActivity.getResources().getColor(R.color.colorBlue));
            holder1.tvBackBrand.setTextColor(mActivity.getResources().getColor(R.color.colorBlue));

            holder1.llIsBack.setVisibility(View.VISIBLE);
            holder1.tvSendDate.setTextColor(mActivity.getResources().getColor(R.color.colorBlue));
            holder1.tvSendTime.setTextColor(mActivity.getResources().getColor(R.color.colorBlue));
            holder1.tvSendDate.setText(TimeUtils.ms2Time(item.getGetTtime()));
            holder1.tvSendTime.setText(TimeUtils.ms2DetailTime(item.getGetTtime()) + "已退桶");


            holder1.tvBackMb.setText("退回押金为:");
            holder1.tvBackBb.setText("退桶为:");
            holder1.tvBackMoney.setText(item.getReT_Money() + "元");
//            tring reTgoods, String reTnum
            holder1.tvBackBrand.setText(getBrandNumber(item.getGoods(), item.getGetT_num()));


//            //初始化bt不可点击
//            holder1.btnConfirm.setEnabled(true);
//            holder1.btnConfirm.setBackgroundResource(R.drawable.bule_button_normal);
//            holder1.btnConfirm.setText("查看");
        }

        holder1.tvUserRemark.setText(item.getRemark());

        holder1.tvStoreRemarks.setText(item.getStoreName());


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
                sb.append(splitGoods[i] + "X" + splitNums[i] + mActivity.getResources().getString(R.string.out_bound_submit1));


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