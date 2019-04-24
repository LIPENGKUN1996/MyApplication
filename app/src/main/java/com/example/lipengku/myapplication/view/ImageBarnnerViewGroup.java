package com.example.lipengku.myapplication.view;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Lipengku on 2019/1/24.
 * 该类为实现图片轮播的核心类
 */

public class ImageBarnnerViewGroup extends ViewGroup {

    private int children;//VG子视图总个数
    private int childwidth, childheight;//子视图的宽 高

    private int x;//第一次按下的位置横坐标、每一次移动  移动之前的横坐标
    private int index = 0;//每张图片的索引

    private Scroller scroller;

    /**
     * 实现图片的点击事件的获取   利用一个变量开关进行判断
     * 在用户离开屏幕的一瞬间判断是滑动还是点击
     */
    private boolean isClick;//true为点击 false为滑动

    private ImageBarnnerLister lister;

    public ImageBarnnerLister getLister() {
        return lister;
    }

    public void setLister(ImageBarnnerLister lister) {
        this.lister = lister;
    }

    private ImageBarnnerViewGroupLiSnner barnnerViewGroupLiSnner;

    public ImageBarnnerViewGroupLiSnner getBarnnerViewGroupLiSnner() {
        return barnnerViewGroupLiSnner;
    }

    public void setBarnnerViewGroupLiSnner(ImageBarnnerViewGroupLiSnner barnnerViewGroupLiSnner) {
        this.barnnerViewGroupLiSnner = barnnerViewGroupLiSnner;
    }

    /**
     * 图片的点击事件
     * 定义一个接口
     */
    public interface ImageBarnnerLister {
       void clickImageIndex(int pos);//pos = 当前图片的具体索引值
    }
    /**
     * 图片轮播底部圆点及底部圆点切换功能步骤
     * 1、自定义一个继承自FrameLayout的布局  利用Frame Layout的特性  实现底部圆点布局
     * 2、准备底部圆点素材   利用drawable功能  实现圆点功能
     * 3、继承FrameLayout  来自定义一个类  加载我们自定义的ImageBarnnerViewGroup的核心类
     * 和我们需要实现的底部圆点的布局linearLayout来实现
     */

    /**
     * 自动轮播
     * 采用Timer TimerTask Handler三者结合的方式
     * 抽取出两个方法来控制是否启动自动轮播  startAuto  stopAuto
     * 需要一个变量作为是否开启轮播的开关  isAuto   true代表开启
     */
    private boolean isAuto = true;//默认开启自动轮播
    private Timer timer = new Timer();
    private TimerTask task;
    private android.os.Handler autoHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://此时需要图片的自动轮播
                    if (++index >= children) {//此时如果是最后一张图片  则从第一张图片开始
                        index = 0;
                    }
                    scrollTo(childwidth * index, 0);
                    barnnerViewGroupLiSnner.selectImage(index);
                    break;
            }
        }
    };

    private void startAuto() {
        isAuto = true;
    }

    private void stopAuto() {
        isAuto = false;
    }

    public ImageBarnnerViewGroup(Context context) {
        super(context);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObj();
    }

    private void initObj() {
        scroller = new Scroller(getContext());

        task = new TimerTask() {
            @Override
            public void run() {
                if (isAuto) {
                    autoHandler.sendEmptyMessage(0);
                }
            }
        };
        timer.schedule(task, 100, 2000);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    /**
     * 我们在自定义VG中必须要实现的方法有
     * 测量 onMeasure
     * 布局
     * 绘制 调用系统自带绘制即可
     */
    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 由于我们要实现的是一个VG容器
         * 我们必须知道该容器中所有子视图
         * 我们想要测量VG的高度和宽度，就必须知道子视图的宽度和高度
         */
        //* 1、求出子视图的个数
        children = getChildCount();
        if (children == 0) {
            setMeasuredDimension(0, 0);
        } else {
            // * 2、测量子视图的高 宽
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            //以第一个子视图为基准  VG的宽  高就是第一个子视图的宽*子视图的个数  高
            View view = getChildAt(0);//第一个子视图绝对存在
            childwidth = view.getMeasuredWidth();
            //* 3、根据子视图的宽 高，求出VG的宽 高
            childheight = view.getMeasuredHeight();
            int width = view.getMeasuredWidth() * children;//所有子视图宽度的总和
            setMeasuredDimension(width, childheight);
        }
    }

    /**
     * 事件传递过程中的调用方法：
     * 1、容器的拦截方法 onInterceptTouchEvent
     * 返回值为true  自定义VG处理此次拦截事件
     * 如果返回真  真正处理该事件的方法是onTouchEvent方法
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * 用两种方式来实现轮播图的手动轮播
     * 利用ScrollTo scrollBy完成轮播图的手动轮播
     * 利用Scroller 对象 完成轮播图的手动轮播
     * 1、滑动图片过程中就是自定义VG的子视图移动过程
     * 只需要知道滑动之前的横坐标和滑动之后的横坐标
     * 可求出 此次过程中移动的距离  利用ScrollBy方法实现图片的滑动
     * 2、第一次按下一瞬间  此时移动之间和移动之后的值是相等的   按下一瞬间那个点的横坐标
     * 3、在不断地滑动过程中  不断地调用ACTION_MOVE方法  将移动之前的值 和移动之后的值进行保存
     * 以便算出滑动距离
     * 4、抬起一瞬间 需要计算出需要滑动到那张图片上
     * <p>
     * 需要求出将要滑动到图片的索引值 = （当前VG滑动位置 + 每一张图片的/2）/每一张图片的宽度值
     * 此时利用ScrollTo方法 滑动到该图片的位置上
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://用户按下一瞬间
                stopAuto();
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                isClick = true;
                x = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE://按下之后   在屏幕上移动的过程
                int moveX = (int) event.getX();
                int distance = moveX - x;
                scrollBy(-distance, 0);
                x = moveX;
                isClick = false;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL://抬起的一瞬间

                int scrollX = getScrollX();
                index = (scrollX + childwidth / 2) / childwidth;
                if (index < 0) {//已经滑动到最左边第一张图片
                    index = 0;
                } else if (index > children - 1) {//已经滑动到最右边第一张图片
                    index = children - 1;
                }

                if (isClick) {//点击事件
                     lister.clickImageIndex(index);
                } else {//非点击事件
                    int dx = index * childwidth - scrollX;

                    scroller.startScroll(scrollX, 0, dx, 0);
                    postInvalidate();
                    barnnerViewGroupLiSnner.selectImage(index);
                }
                startAuto();
                //scrollTo(index * childwidth,0);
                break;
            default:
                break;
        }
        return true;//VG容器的父view已经处理好了该事件
    }

    /**
     * 继承VG必须实现布局onLayout方法
     * boolean b 当VG布局位置发生改变为true 未发生改变为false
     * i i1 i2 i3
     * 参数i表示相对于父view的Left位置；
     * 参数i1表示相对于父view的Top位置；
     * 参数i2表示相对于父view的Right位置；
     * 参数i3表示相对于父view的Bottom位置。
     */
    //布局
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (b) {
            int leftMargin = 0;
            for (int j = 0; j < children; j++) {
                View view = getChildAt(j);
                view.layout(leftMargin, 0, leftMargin + childwidth, childheight);
                leftMargin += childwidth;
            }
        }

    }

    public interface ImageBarnnerViewGroupLiSnner {
        void selectImage(int index);
    }
}

