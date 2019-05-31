package com.kelouy.mvp.news.mvpfortest.moudule.base;


import android.os.Bundle;

import com.kelouy.mvp.news.mvpfortest.R;
import com.kelouy.mvp.news.mvpfortest.widget.EmptyLayout;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView,EmptyLayout.OnRetryListener {
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initInjector();
        initViews();
        initSwipeRefresh();
        updateViews(false);
    }
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews(boolean isRefresh);
    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {

    }
    @Override
    public void showLoading() {
        if(mEmptyLayout != null){
            mEmptyLayout.seEmptyStatus(EmptyLayout.STATU_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if(mEmptyLayout != null){
            mEmptyLayout.seEmptyStatus(EmptyLayout.STATU_HIDE);
        }
    }

    @Override
    public void showNetError() {
        if(mEmptyLayout != null){
            mEmptyLayout.seEmptyStatus(EmptyLayout.STATU_NONET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void onRetry() {
        updateViews(false);
    }
}
