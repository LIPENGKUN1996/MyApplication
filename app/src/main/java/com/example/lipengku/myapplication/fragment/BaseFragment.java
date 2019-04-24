package com.example.lipengku.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentLayout(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    /**
     * 初始化页面view
     *
     * @param view rootView
     */
    protected abstract void initViews(View view);

    /**
     * fragment 布局id
     */
    protected abstract int getContentLayout();

    protected <T extends View> T getView(@IdRes int resId) {
        if (mRootView != null) {
            return mRootView.findViewById(resId);
        }
        return null;
    }
}
