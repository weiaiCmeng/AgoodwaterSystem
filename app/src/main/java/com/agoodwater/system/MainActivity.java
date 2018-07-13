package com.agoodwater.system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.agoodwater.system.fragment.CompletedPage;
import com.agoodwater.system.fragment.OutstandingOrderPage;
import com.agoodwater.system.fragment.StoreManagementPage;
import com.agoodwater.system.fragment.ViewPagerFragment;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.NetUtils;
import com.agoodwater.system.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

import static com.agoodwater.system.utils.MyToast.show;

public class MainActivity extends SupportActivity {

    public List<ViewPagerFragment> pagerList = new ArrayList<>();
    @BindView(R.id.vp_home_content)
    NoScrollViewPager vpHomeContent;
    @BindView(R.id.rb_outstanding)
    RadioButton rbOutstanding;
    @BindView(R.id.rb_completed)
    RadioButton rbCompleted;
    @BindView(R.id.rb_store)
    RadioButton rbStore;
    @BindView(R.id.rg_new_group)
    RadioGroup rgNewGroup;
    @BindView(R.id.rl_radioGroup)
    RelativeLayout rlRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ButterKnife.bind(this);

//        Beta.checkUpgrade();//检查版本号

        //1:未完成订单
        pagerList.add(new OutstandingOrderPage());
        //2:已完成订单
        pagerList.add(new CompletedPage());
        //3:门店管理
        pagerList.add(new StoreManagementPage());
        vpHomeContent.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager()));

        //初始化第一个布局文件
        vpHomeContent.setCurrentItem(0, false);
        rbOutstanding.toggle();
        /**
         * 设置rediobutton点击相应viewpager的页码
         */
        rgNewGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_outstanding:



                        if (vpHomeContent !=null){

                            vpHomeContent.setCurrentItem(0, false);
                        }


                        if (!NetUtils.isNetworkAvailable(MainActivity.this)){
                            show(MainActivity.this,"暂无网络信息,请检查网络!");
                        }

                        break;
                    case R.id.rb_completed:
//                        EventBus.getDefault().post(new CompletedEventBus());
                        vpHomeContent.setCurrentItem(1, false);

                        if (!NetUtils.isNetworkAvailable(MainActivity.this)){
                            show(MainActivity.this,"暂无网络信息,请检查网络!");
                        }

                        break;
                    case R.id.rb_store:
//                        EventBus.getDefault().post(new StoreManagementEvent());
                        vpHomeContent.setCurrentItem(2, false);

                        if (!NetUtils.isNetworkAvailable(MainActivity.this)){
                            MyToast.show(MainActivity.this,"暂无网络信息,请检查网络!");
                        }

                        break;
                }
            }
        });





    }





    /**
     * mainactivity的viewpager的适配器
     */
    class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
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

    /**
     * 菜单、返回键响应
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            return exitBy2Click();      //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private boolean exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();

            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            isExit = false;

        }

        return isExit;
    }



}
