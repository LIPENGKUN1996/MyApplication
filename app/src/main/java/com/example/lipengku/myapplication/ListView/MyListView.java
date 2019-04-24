package com.example.lipengku.myapplication.ListView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView implements NestedScrollingChild {
    private final NestedScrollingChildHelper mScrollingChildHelper;

    public MyListView(Context context, AttributeSet attr) {
        super(context,attr);
        mScrollingChildHelper = new NestedScrollingChildHelper(this);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            setNestedScrollingEnabled(true);
        }
    }

    @Override
    public void setNestedScrollingEnabled(boolean b) {
        mScrollingChildHelper.setNestedScrollingEnabled(b);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int i) {
        return mScrollingChildHelper.startNestedScroll(i);
    }

    @Override
    public void stopNestedScroll() {
        mScrollingChildHelper.stopNestedScroll();

    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int i, int i1, int i2, int i3, @Nullable int[] ints) {
        return mScrollingChildHelper.dispatchNestedScroll(i,i1,i2,i3,ints);
    }

    @Override
    public boolean dispatchNestedPreScroll(int i, int i1, @Nullable int[] ints, @Nullable int[] ints1) {
        return mScrollingChildHelper.dispatchNestedPreScroll(i,i1,ints,ints1);
    }

    @Override
    public boolean dispatchNestedFling(float v, float v1, boolean b) {
        return mScrollingChildHelper.dispatchNestedFling(v,v1,b);
    }

    @Override
    public boolean dispatchNestedPreFling(float v, float v1) {
        return mScrollingChildHelper.dispatchNestedPreFling(v,v1);
    }
}
