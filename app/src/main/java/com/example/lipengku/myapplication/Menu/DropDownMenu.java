package com.example.lipengku.myapplication.Menu;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lipengku.myapplication.R;

import java.io.Serializable;
import java.util.List;



public class DropDownMenu extends LinearLayout {
    //顶部菜单布局
    private LinearLayout tabMenuView;
    //底部容器布局  包含内容区域 底部遮罩区域 菜单弹出区域
    private FrameLayout containerView;
    //内容区域
    private View contentView;
    //底部遮罩区域
    private View maskView;
    //菜单弹出区域
    private FrameLayout popupMenuViews;

    //分割线颜色
    private int dividerColor = 0xffcccccc;
    //文本选中颜色
    private int textSelectColor = 0xff890c85;
    //文本未选中颜色
    private int textUnSelectColor = 0xff111111;
    //遮罩颜色
    private int maskColor = 0x88888888;
    //菜单背景颜色
    private int menuBackgroundColor = 0xffffffff;
    //水平分割线颜色
    private int underlineColor = 0xffcccccc;

    //字体大小
    private int menuTextSize = 50;
    //tab选中图标
    private int menuSelectIcon;
    //tab未选中图标
    private int menuUnSelectIcon;

    //菜单项被选中位置  初始菜单开始时没有被选中 记为-1
    private int currrentTabPostation = -1;


    public DropDownMenu(Context context) {
        super(context, null);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dropDownMenu);

        underlineColor = a.getColor(R.styleable.dropDownMenu_underlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.dropDownMenu_dividerColor, dividerColor);
        textSelectColor = a.getColor(R.styleable.dropDownMenu_textSelectColor, textSelectColor);
        textUnSelectColor = a.getColor(R.styleable.dropDownMenu_textUnSelectColor, textUnSelectColor);
        menuBackgroundColor = a.getColor(R.styleable.dropDownMenu_menuBackgroundColor, menuBackgroundColor);
        maskColor = a.getColor(R.styleable.dropDownMenu_maskColor, maskColor);
        menuTextSize = a.getDimensionPixelSize(R.styleable.dropDownMenu_menuTextSize, menuTextSize);
        menuSelectIcon = a.getResourceId(R.styleable.dropDownMenu_menuSelectIcon, menuSelectIcon);
        menuUnSelectIcon = a.getResourceId(R.styleable.dropDownMenu_menuUnSelectIcon, menuUnSelectIcon);
        a.recycle();

        initViews(context);
    }

    public void initViews(Context context) {
        //创建顶部菜单
        tabMenuView = new LinearLayout(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabMenuView.setOrientation(HORIZONTAL);
        tabMenuView.setLayoutParams(lp);
        addView(tabMenuView, 0);

        //创建下划线
        View underlineView = new View(context);
        underlineView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2Px(1.0f)));
        underlineView.setBackgroundColor(underlineColor);
        addView(underlineView, 1);

        //初始化containerView
        containerView = new FrameLayout(context);
        containerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(containerView, 2);
    }

    private int dp2Px(float value) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, displayMetrics);
    }

    /**
     * 初始化DropDownMenu来显示具体的内容
     */
    public void setDropDownMenu(List<String> tabTexs, List<View> popuViews, View contentView) {
        this.contentView = contentView;
        if (tabTexs.size() != popuViews.size()) {
            throw new IllegalArgumentException("数量应该一样");
        }
        for (int i = 0; i < tabTexs.size(); i++) {

            addTab(tabTexs, i);
        }

        //添加顶部菜单
        containerView.addView(contentView, 0);

        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                720));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                closeMenu();
            }


        });
        maskView.setVisibility(View.GONE);//不可见
        //添加遮罩区域
        containerView.addView(maskView, 1);

        popupMenuViews = new FrameLayout(getContext());
        popupMenuViews.setVisibility(View.GONE);//不可见
        for (int i = 0; i < popuViews.size(); i++) {
            popuViews.get(i).setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));
            popupMenuViews.addView(popuViews.get(i), i);
            //popupMenuViews.requestLayout();
        }
        //添加菜单下拉显示区域
        containerView.addView(popupMenuViews, 2);
    }


    public void addTab(List<String> tabTexs, int index) {
        final TextView tab = new TextView(getContext());
        tab.setSingleLine();//单行显示
        tab.setEllipsize(TextUtils.TruncateAt.END);
        tab.setGravity(Gravity.CENTER);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        tab.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        tab.setTextColor(textUnSelectColor);
        tab.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(menuUnSelectIcon), null);
        tab.setText(tabTexs.get(index));
        tab.setPadding(dp2Px(5), dp2Px(12), dp2Px(5), dp2Px(12));

        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switchMune(tab);
            }
        });

        tabMenuView.addView(tab);

        //添加分割线
        if (index < tabTexs.size() - 1) {
            //创建下划线
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(dp2Px(0.5f), ViewGroup.LayoutParams.MATCH_PARENT));
            view.setBackgroundColor(dividerColor);
            tabMenuView.addView(view);
        }
    }

    /**
     * 切换菜单
     */
    public void switchMune(View texgetView) {
        for (int i = 0; i < tabMenuView.getChildCount(); i += 2) {
            if (texgetView == tabMenuView.getChildAt(i)) {
                if (currrentTabPostation == i) {//关闭菜单
                    closeMenu();
                } else {//弹出菜单
                    if (currrentTabPostation == -1) {//初始状态
                        popupMenuViews.setVisibility(View.VISIBLE);
                        popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.a));
                        maskView.setVisibility(View.VISIBLE);
                        maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.mask_in));
                        popupMenuViews.getChildAt(i/2).setVisibility(VISIBLE);
                    } else {
                        popupMenuViews.getChildAt(i/2).setVisibility(VISIBLE);
                    }
                    currrentTabPostation = i;
                    ((TextView) tabMenuView.getChildAt(i)).setTextColor(textSelectColor);
                    ((TextView) tabMenuView.getChildAt(i)).
                            setCompoundDrawablesWithIntrinsicBounds
                                    (null, null, getResources().getDrawable(menuSelectIcon), null);
                }
            }else {
                ((TextView) tabMenuView.getChildAt(i)).setTextColor(textUnSelectColor);
                ((TextView) tabMenuView.getChildAt(i)).
                        setCompoundDrawablesWithIntrinsicBounds
                                (null, null, getResources().getDrawable(menuUnSelectIcon), null);
                popupMenuViews.getChildAt(i/2).setVisibility(GONE);
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (currrentTabPostation != -1){
            ((TextView) tabMenuView.getChildAt(currrentTabPostation)).setTextColor(textUnSelectColor);
            ((TextView) tabMenuView.getChildAt(currrentTabPostation)).
                    setCompoundDrawablesWithIntrinsicBounds
                            (null, null, getResources().getDrawable(menuUnSelectIcon),
                                    null);
            popupMenuViews.setVisibility(GONE);
            popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.out));
            maskView.setVisibility(GONE);
            maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.mask_out));
            currrentTabPostation = -1;
        }
    }


    public void setTabText(String s) {
        if(this.currrentTabPostation != -1) {

            ((TextView)this.tabMenuView.getChildAt(this.currrentTabPostation)).setText(s);
        }
    }



}
