package com.agoodwater.system.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.activity.CheckTimeActivity;
import com.agoodwater.system.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shiqiang on 2017/2/21.
 */
public class CompletedPage extends ViewPagerFragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(R.id.rb_send_water)
    RadioButton rbSendWater;
    @BindView(R.id.rb_return_bucket)
    RadioButton rbReturnBucket;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.vp_content)
    NoScrollViewPager vpOrderContent;
    private WaterFragment waterFragment;
    private BucketFragment bucketFragment;
    private List<BasePagerFragment> orderList;

    private String[] content = {"全部"};
//    private String[] content = {"全部", "待支付"};

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_completed_order;
    }


    @Override
    protected void onVisible() {
        initData();
    }

    private void initData() {

        tvRight.setText("查询");


        System.out.println("我是已完成initData");
        ivBack.setVisibility(View.GONE);
        tvTitleName.setText("完成订单");

//        if (waterFragment == null || bucketFragment == null){
//            waterFragment = new WaterFragment(mainActivity);
//            bucketFragment = new BucketFragment(mainActivity);
//        }


        if (waterFragment == null ){
            waterFragment = new WaterFragment(mainActivity);

        }

        updateNet();


    }


    private void updateNet() {

        if (orderList == null){

            orderList = new ArrayList<>();
            orderList.add(waterFragment);
//            orderList.add(bucketFragment);
        }
        vpOrderContent.setAdapter(new MyViewpagerAdapter());

        vpOrderContent.setCurrentItem(0);
        rgGroup.check(R.id.rb_send_water);
        vpOrderContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {

                    case 0:
                        rgGroup.check(R.id.rb_send_water);
                        break;
                    case 1:
                        rgGroup.check(R.id.rb_return_bucket);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_send_water:
                        vpOrderContent.setCurrentItem(0, false);
                        break;
                    case R.id.rb_return_bucket:
                        vpOrderContent.setCurrentItem(1, false);
                        break;
                }
            }
        });


    }


    @OnClick(R.id.tv_right)
    public void onViewClicked() {

        startActivity(CheckTimeActivity.class);
    }


    class MyViewpagerAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return content[position];
        }

        @Override
        public int getCount() {
            return content.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePagerFragment pager = orderList.get(position);
            View view = pager.mRootView;
            container.addView(view);

            //调用pager的方法即可
            pager.initData();
            return view;
        }

        //        public MyViewpagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return content[position];
//        }
//
//        @Override
//        public int getCount() {
//            return content.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//
//        @Override
//        public BasePagerFragment getItem(int position) {
//
//            return orderList.get(position);
//        }
//
//        @Override
//        public int getItemPosition(Object object) {
//
//            return super.getItemPosition(object);
//        }
    }


}
