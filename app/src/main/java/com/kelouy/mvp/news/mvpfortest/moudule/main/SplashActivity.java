package com.kelouy.mvp.news.mvpfortest.moudule.main;

import android.widget.Toast;


import com.kelouy.mvp.news.mvpfortest.R;
import com.kelouy.mvp.news.mvpfortest.moudule.base.BaseActivity;
import com.kelouy.mvp.news.mvpfortest.moudule.base.RoundProgressBar;
import com.kelouy.mvp.news.mvpfortest.widget.EmptyLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.start_bar)
    RoundProgressBar bar;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    public void initData(){
        bar.setCircleColor(getResources().getColor(R.color.colorAccent));//设置圆环的颜色
        bar.setCircleProgressColor(getResources().getColor(R.color.colorAccent));//设置圆环进度的颜色
        bar.setTextColor(getResources().getColor(R.color.colorAccent));//设置中间进度百分比的字符串的颜色
        bar.setRoundWidth(4);//设置圆环的宽度
        bar.setTextSize(24);
    }

    @OnClick(R.id.start_bar)
    public void onClick(){
        Toast.makeText(this,"1111",Toast.LENGTH_SHORT).show();
        showLoading();
    }
}
