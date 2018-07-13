package com.agoodwater.system.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.bean.CheckDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-13.
 */

public class CheCkDetailAdapter extends RecyclerView.Adapter<CheCkDetailAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<CheckDetailsBean.DataBean.SalesDetileListBean> mList = new ArrayList<>();
    private CheckDetailsBean.DataBean.SalesDetileListBean item;

    public CheCkDetailAdapter(Context context, List<CheckDetailsBean.DataBean.SalesDetileListBean> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        mList.clear();
        mList.addAll(list);

        System.out.println("我的长度是:" + mList.size() + "---" + list.size());
    }

    public void setAdapter(List<CheckDetailsBean.DataBean.SalesDetileListBean> goodList) {

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
        View view = inflater.inflate(R.layout.list_water, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        System.out.println("我的位置是:``````" + position);
        item = mList.get(position);

        holder.tvName.setText(item.getGoodsName());
        holder.tvAllOrder.setText(item.getGoodsNum() + "桶");
        holder.tvAllBucket.setText(item.getTotal() + "元");
        holder.tvAllBucket.setTextColor(mContext.getResources().getColor(R.color.colorRed));

       /* String title = mList.get(position).getTitle();
        String imgUrl = mList.get(position).getImgsrc();
        String source = mList.get(position).getSource() + "   " + mList.get(position).getPtime();

        holder.tvTitle.setText(title);
        holder.tvSource.setText(source);

        ImageLoader.getInstance().showImage(mContext, imgUrl, holder.ivImg);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {


        System.out.println("我的getItem + :" + mList.size());

        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_all_order)
        TextView tvAllOrder;
        @BindView(R.id.tv_all_bucket)
        TextView tvAllBucket;
        @BindView(R.id.cardview)
        CardView cardview;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /*class ViewHolder extends RecyclerView.ViewHolder{
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