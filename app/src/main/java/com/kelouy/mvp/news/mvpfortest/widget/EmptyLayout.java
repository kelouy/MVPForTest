package com.kelouy.mvp.news.mvpfortest.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.kelouy.mvp.news.mvpfortest.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyLayout extends FrameLayout {
    public static final int STATU_LOADING = 1;
    public static final int STATU_NONET = 2;
    public static final int STATU_HIDE = 3;
    private int mBgColor;
    private int mEmptyStatus = STATU_LOADING;
    private OnRetryListener onRetryListener;
    @BindView(R.id.empty_loading)
    SpinKitView spinKitView;
    @BindView(R.id.empty_layout_notices)
    View mContainer;
    private Context mContext;
    @BindView(R.id.empty_layout)
    FrameLayout mFrameLayout;
    public EmptyLayout(@NonNull Context context) {
        this(context,null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }
    public void init(AttributeSet attrs){
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
        try {
            mBgColor = a.getColor(R.styleable.EmptyLayout_background_color, Color.WHITE);
        } finally {
            a.recycle();
        }
        View.inflate(mContext, R.layout.layout_empty_loading, this);
        ButterKnife.bind(this);
        mFrameLayout.setBackgroundColor(mBgColor);
        switchEmptyView();
    }
    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @OnClick(R.id.empty_text_refresh)
    public void onClick(){
        onRetryListener.onRetry();
    }

    public void seEmptyStatus(@EmptyStatus int emptystatus){
        mEmptyStatus = emptystatus;
    }

    public void setRetryListener(OnRetryListener onRetryListener){
        this.onRetryListener = onRetryListener;
        switchEmptyView();
    }

    private void switchEmptyView(){
        switch (mEmptyStatus){
            case STATU_LOADING:
                setVisibility(View.VISIBLE);
                mContainer.setVisibility(View.GONE);
                spinKitView.setVisibility(View.VISIBLE);
                break;
            case STATU_HIDE:
                setVisibility(View.GONE);
                break;
            case STATU_NONET:
                setVisibility(View.VISIBLE);
                mContainer.setVisibility(View.VISIBLE);
                spinKitView.setVisibility(View.GONE);
                break;
        }

    }
    /**
     * 点击重试监听器
     */
    public interface OnRetryListener {
        void onRetry();
    }
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATU_LOADING,STATU_NONET,STATU_HIDE})
    public @interface EmptyStatus{}
}
