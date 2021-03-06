package com.agoodwater.system.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.BucketCompletedBean;
import com.agoodwater.system.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shiqiang on 2016/9/28.
 */
public class CompletedBucketAdapter extends BaseAdapter implements View.OnClickListener {


    private int position1;
    private Context mActivity;

    private List<BucketCompletedBean.DataBean.EndTorderListBean> waterOrder;
    private BucketCompletedBean.DataBean.EndTorderListBean item;

    private Callback mCallback;
    private StringBuffer sb;

    public CompletedBucketAdapter(Context mainActivity, List<BucketCompletedBean.DataBean.EndTorderListBean> waterOrderbean, Callback callback) {

        this.mActivity = mainActivity;
        this.mCallback = callback;
//        this.waterOrder.clear();
        waterOrder = waterOrderbean;


        if (waterOrder == null) {

            position1 = 0;

        } else {

            position1 = waterOrder.size();

        }


    }


    public void setListwaterOrder(List<BucketCompletedBean.DataBean.EndTorderListBean> listwaterOrder) {

        waterOrder = listwaterOrder;

        if (waterOrder == null) {

            position1 = 0;

        } else {

            position1 = waterOrder.size();

        }

    }

    @Override
    public int getCount() {
        return position1;
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


        ViewHolder holder1 = null;
        int type = getItemViewType(position);

        if (convertView == null) {

            convertView = View.inflate(mActivity, R.layout.order_list_item, null);
            holder1 = new ViewHolder(convertView);
            convertView.setTag(holder1);

        } else {
            holder1 = (ViewHolder) convertView.getTag();

        }

        item = waterOrder.get(position);


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

        holder1.btnConfirm.setOnClickListener(this);
        holder1.btnConfirm.setTag(position);


        holder1.ivPhoneCall.setOnClickListener(this);
        holder1.ivPhoneCall.setTag(position);


        return convertView;
    }


    @Override
    public void onClick(View v) {

        mCallback.click(v);
    }


    public interface Callback {
        public void click(View v);
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


    static class ViewHolder {
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
            ButterKnife.bind(this, view);
        }
    }
}
