package com.agoodwater.system.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.CheckEmployeeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-13.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<CheckEmployeeBean.DataBean.DisListBean> mList = new ArrayList<>();
    private CheckEmployeeBean.DataBean.DisListBean item;

    public NewsAdapter(Context context, List<CheckEmployeeBean.DataBean.DisListBean> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        mList.clear();
        mList.addAll(list);

        System.out.println("我的长度是:" + mList.size() + "---" + list.size());
    }

    public void setAdapter(List<CheckEmployeeBean.DataBean.DisListBean> goodList) {

        mList.clear();
        mList.addAll(goodList);

    }


    /**
     * add onItemClick begin
     */
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        System.out.println("我的位置是:``````" + position);
        item = mList.get(position);

        holder.tvName.setText(item.getUser_name());
        holder.tvOrderNum.setText(item.getDanNum() + "桶");
        holder.tvBucketNum.setText(item.getTongNum() + "桶");
//        holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.colorRed));
//        holder.tvMoney.setText(item.getDeposit() + "元");


        //给点击事件绑定点击事件区别
        holder.cardview.setTag(position);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {


        System.out.println("我的getItem + :" + mList.size());

        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.cardview)
        CardView cardview;
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
    }
}