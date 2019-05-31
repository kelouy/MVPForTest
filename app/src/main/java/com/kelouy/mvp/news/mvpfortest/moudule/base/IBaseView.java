package com.kelouy.mvp.news.mvpfortest.moudule.base;

import com.trello.rxlifecycle3.LifecycleTransformer;

public interface IBaseView {

    void showLoading();

    void hideLoading();

    void showNetError();

    <T> LifecycleTransformer<T> bindToLife();

    void finishRefresh();
}
