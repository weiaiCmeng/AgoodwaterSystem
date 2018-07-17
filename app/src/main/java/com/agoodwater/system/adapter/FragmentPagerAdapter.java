package com.agoodwater.system.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.agoodwater.system.fragment.ViewPagerFragment;

import java.util.List;

/**
 * created by shi on 2018/7/17/017
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<ViewPagerFragment> pagerList ;
    public FragmentPagerAdapter(FragmentManager fm ,List<ViewPagerFragment> pagerList) {

        super(fm);

        this.pagerList = pagerList ;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerList.get(position);
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }


    //触发viewpager的刷新事件;
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
