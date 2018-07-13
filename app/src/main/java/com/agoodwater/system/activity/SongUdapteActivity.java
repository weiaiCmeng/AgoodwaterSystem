package com.agoodwater.system.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agoodwater.system.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shiqiang on 2017/4/13.
 */

public class SongUdapteActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_comp_quit)
    LinearLayout llCompQuit;
    @BindView(R.id.tv_song_warn)
    TextView tvSongWarn;
    @BindView(R.id.iv_song_update)
    ImageView ivSongUpdate;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_song_update;
    }

    @Override
    protected void initView() {

        tvTitleName.setText("送水工下载");
        tvRight.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
