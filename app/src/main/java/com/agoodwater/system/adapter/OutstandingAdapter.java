package com.agoodwater.system.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.MainActivity;
import com.agoodwater.system.R;
import com.agoodwater.system.bean.OutstandingBean;
import com.agoodwater.system.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by shiqiang on 2016/9/28.
 */
public class OutstandingAdapter extends BaseAdapter implements View.OnClickListener {

    private MainActivity mActivity;
    private int position;
    private Callback mCallback;
    private List<OutstandingBean.DataBean.OrderListBean> orderList;
    private OutstandingBean.DataBean.OrderListBean orderListBean;


    public OutstandingAdapter(MainActivity mainActivity, OutstandingBean outstanding, Callback callback) {

        this.mActivity = mainActivity;

        this.mCallback = callback;

        //得到订单情况

        orderList = outstanding.getData().getOrderList();

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

            convertView = View.inflate(mActivity, R.layout.outstanding_order_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);


        } else {

            holder = (ViewHolder) convertView.getTag();


        }


        //查看是送水单还是退桶单

        orderListBean = orderList.get(position);


        //根据id判断是退桶还是送水订单,如果为null 退桶 否则送水
        if (TextUtils.isEmpty(orderListBean.getGoods_name())) {
            //退桶订单
            holder.tvOutsName.setText("退桶");
            holder.tvSendDiffer.setText("退桶订单(等待分配送水工)");
            holder.tvSendDiffer.setTextColor(mActivity.getResources().getColor(R.color.colorRed));

            holder.tvProductComplectedDate.setText(TimeUtils.ms2Time(orderListBean.getCreate_date()));
            holder.tvProductComplectedTime.setText("下单时间");

        } else {

            holder.tvSendDiffer.setTextColor(mActivity.getResources().getColor(R.color.colorBlue));
            holder.tvOutsName.setText(orderListBean.getGoods_name());
            holder.tvProductComplectedDate.setText(TimeUtils.strToTime(orderListBean.getSend_time()));
            holder.tvProductComplectedTime.setText(orderListBean.getSend_time_range());
            holder.tvSendDiffer.setText("送水订单(等待分配送水工)");

            holder.tvCustomerRemarks.setText(orderListBean.getRemarks());
            holder.tvManagerRemarks.setText(orderListBean.getYsjremark());

        }
        holder.tvUserName.setText(orderListBean.getShip_name());

        holder.tvOutsNumbers.setText("X" + mActivity.getResources().getString(R.string.out_bound_submit1) + orderListBean.getGoods_num());

        holder.tvUserPhoneNumber.setText(orderListBean.getShip_mobile_phone() );
        holder.tvUserAddress.setText(orderListBean.getShip_address());
        holder.ivPhoneCall.setOnClickListener(this);
        holder.ivPhoneCall.setTag(position);
        holder.btnConfirm.setOnClickListener(this);
        holder.btnConfirm.setTag(position);

        return convertView;
    }

    @Override
    public void onClick(View v) {

        mCallback.click(v);
    }

    public void setAdapter(OutstandingBean outstandingNetBean) {

//        if (orderList != null) {
//
//            orderList.clear();
//
//        }
        orderList = outstandingNetBean.getData().getOrderList();

        if (orderList != null) {
            position = orderList.size();
        } else {
            position = 0;
        }


    }


    public interface Callback {
        void click(View v);
    }


    static class ViewHolder {
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv_outs_name)
        TextView tvOutsName;
        @BindView(R.id.tv_outs_numbers)
        TextView tvOutsNumbers;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.iv_phone_call)
        ImageView ivPhoneCall;
        @BindView(R.id.tv_user_phone_number)
        TextView tvUserPhoneNumber;
        @BindView(R.id.iv_location)
        ImageView ivLocation;
        @BindView(R.id.tv_user_address)
        TextView tvUserAddress;
        @BindView(R.id.rl_user_address)
        RelativeLayout rlUserAddress;
        @BindView(R.id.tv_product_complected_date)
        TextView tvProductComplectedDate;
        @BindView(R.id.tv_product_complected_time)
        TextView tvProductComplectedTime;
        @BindView(R.id.rl_time)
        RelativeLayout rlTime;
        @BindView(R.id.tv_customer_remarks)
        TextView tvCustomerRemarks;
        @BindView(R.id.tv_manager_remarks)
        TextView tvManagerRemarks;
        @BindView(R.id.tv_send_differ)
        TextView tvSendDiffer;
        @BindView(R.id.btn_confirm)
        Button btnConfirm;
        @BindView(R.id.rl_order_confirm)
        RelativeLayout rlOrderConfirm;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
