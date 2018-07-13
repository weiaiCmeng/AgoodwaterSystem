package com.agoodwater.system.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.adapter.EmployeeAdapter;
import com.agoodwater.system.bean.EmployeeAddBean;
import com.agoodwater.system.bean.EmployeeBean;
import com.agoodwater.system.bean.EmployeeDeleteBean;
import com.agoodwater.system.dialog.CommonDialog;
import com.agoodwater.system.dialog.CommonEditViewDialog;
import com.agoodwater.system.moudle.moudle.ActivityModule;
import com.agoodwater.system.moudle.moudle.DaggerEmployeeActivityComponent;
import com.agoodwater.system.moudle.moudle.EmplyeeActivityModule;
import com.agoodwater.system.presenter.Employeepresenter;
import com.agoodwater.system.receiver.OrderEvent;
import com.agoodwater.system.utils.MyToast;
import com.agoodwater.system.utils.SpUtils;
import com.agoodwater.system.view.EmployeeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import name.gudong.statebackground.OneDrawable;

import static com.agoodwater.system.utils.MyToast.show;

/**
 * Created by shiqiang on 2017/3/23.
 */

public class EmployeeManagActivity extends BaseActivity implements EmployeeView  ,EmployeeAdapter.Callback{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(R.id.lv_user_address)
    ListView lvUserAddress;
    @BindView(R.id.ptr_fresh)
    PtrClassicFrameLayout ptrFresh;
    @BindView(R.id.rl_not_address)
    RelativeLayout rlNotAddress;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
//    private EmployeeInfo employeepresenter;
    private List<EmployeeBean.DataBean> waterList;
    private EmployeeAdapter adapter;
    private String songNum;
    private String userName;
    private TextView tvSongName;
    private Dialog confirmDialog;
    private Dialog addDialog;
    private String addUserName;
    private String addUserPhone;
    private CommonEditViewDialog.Builder builder;
    private CommonDialog.Builder confirmbuilder;


    @Inject
    Employeepresenter employeepresenter ;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_employ_manager;
    }

    @Override
    protected void initView() {
        tvTitleName.setText("员工管理");
        tvRight.setVisibility(View.GONE);
        Drawable drawable = OneDrawable.createBgDrawable(this,R.drawable.bule_button_normal);
        btnAddAddress.setBackgroundDrawable(drawable);


    }

    @Override
    protected void initListener() {

        ptrFresh.setLastUpdateTimeRelateObject(this);
        ptrFresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                boolean flag = true;
                if (Build.VERSION.SDK_INT < 14) {
                    flag = lvUserAddress.getChildCount() > 0 && (lvUserAddress.getFirstVisiblePosition() > 0 || lvUserAddress.getChildAt(0).getTop() < lvUserAddress.getPaddingTop());
                } else {
                    flag = lvUserAddress.canScrollVertically(-1);
                }
                return !flag;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFresh.refreshComplete();
                        if (employeepresenter!=null){

                            employeepresenter.loginNet();

                        }
                    }
                }, 1000);

            }
        });



    }

    @Override
    protected void initData() {

        DaggerEmployeeActivityComponent.builder()
                .appComponent(getAppComponent())
                .emplyeeActivityModule(new EmplyeeActivityModule(this))
                .activityModule(new ActivityModule(this)).build()
                .inject(this);

        employeepresenter.loginNet();

    }





    @OnClick({R.id.iv_back, R.id.btn_add_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add_address:

                addEmployeeDialog();
                break;
        }
    }

    private void addEmployeeDialog() {

        builder = new CommonEditViewDialog.Builder(EmployeeManagActivity.this);
        builder.setTitle("添加您的店铺送水工")
                .setTitleColor(R.color.color3dceff)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //联网操作

                        addUserName = builder.getAddUserName();
                        addUserPhone = builder.getAddUserPhone();
                        employeepresenter.addNet();

                    }
                })
                .create().show();



    }


    @Override
    public String getUserName() {

        return SpUtils.getString(this,"userName","");

    }

    @Override
    public String getSongNum() {
        return songNum;
    }

    @Override
    public String getAddSongName() {
        return addUserName;
    }

    @Override
    public String getAddSongPhone() {
        return addUserPhone;
    }

    @Override
    public void initSuccess(EmployeeBean employeeBean) {

        waterList = employeeBean.getData();
        System.out.println(waterList.size() + " 我是你大爷的~");

        if (waterList.size() != 0){

            //发送请求给订单更新员工统计用的
            EventBus.getDefault().post(new OrderEvent());

            if (adapter == null){


            adapter = new EmployeeAdapter(EmployeeManagActivity.this, employeeBean , this);


            lvUserAddress.setAdapter(adapter);

            }else{

                adapter.setListwaterOrder(employeeBean);
                adapter.notifyDataSetChanged();


            }

        }


    }

    @Override
    public void error(String error) {

        show(this,error);
    }

    @Override
    public void deleteSuccess(EmployeeDeleteBean employeeDeleteBean) {

        confirmbuilder.hidden();
        show(this, "删除送水工成功!");
        employeepresenter.loginNet();


    }

    @Override
    public void addSuccess(EmployeeAddBean employeeAddBean) {

        builder.hintDialog();

        MyToast.show(this, "添加送水工成功!");
        employeepresenter.loginNet();


    }


    @Override
    public void click(View v) {


        int tag = (int) v.getTag();



        switch (v.getId()) {


            case R.id.iv_delete:

                System.out.println("删除第" + tag + "条");

                songNum = waterList.get(tag).getUser_num() + "";

                userName = waterList.get(tag).getUser_name();

                deleteDialog();




                break;
        }

    }

    private void deleteDialog() {


        confirmbuilder = new CommonDialog.Builder(this);
        confirmbuilder.setTitle("请确认你的选择")
                .setTitleColor(R.color.color5d5d5d)
                .setMessage("您将删除送水工:" + userName + "的信息!")
                .setMessageColor(R.color.colorRed)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //联网操作
                        employeepresenter.deleteNet();

                    }
                })
                .create().show();

    }
}
