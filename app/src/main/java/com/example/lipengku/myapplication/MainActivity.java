package com.example.lipengku.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lipengku.myapplication.Adapter.GridDropDownMenuAdapter;
import com.example.lipengku.myapplication.Adapter.ListDropDownMenuAdapter;
import com.example.lipengku.myapplication.Adapter.ShengaoAdapter;
import com.example.lipengku.myapplication.Menu.DropDownMenu;
import com.example.lipengku.myapplication.ToobarClick.FBliucheng;
import com.example.lipengku.myapplication.ToobarClick.Home;
import com.example.lipengku.myapplication.ToobarClick.QLtougao;
import com.example.lipengku.myapplication.fragment.MyFragmentLb1Unlimited;
import com.example.lipengku.myapplication.fragment.MyFragmentLb2Edu;
import com.example.lipengku.myapplication.fragment.MyFragmentLb3Medical;
import com.example.lipengku.myapplication.fragment.MyFragmentLb4JG;
import com.example.lipengku.myapplication.fragment.MyFragmentLb5GY;
import com.example.lipengku.myapplication.fragment.MyFragmentSl1Unlimited;
import com.example.lipengku.myapplication.fragment.MyFragmentSl2Edu;
import com.example.lipengku.myapplication.fragment.MyFragmentSl3Medical;
import com.example.lipengku.myapplication.fragment.MyFragmentSl4JG;
import com.example.lipengku.myapplication.fragment.MyFragmentSl5GY;
import com.example.lipengku.myapplication.view.C;
import com.example.lipengku.myapplication.view.ImageBarnnerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        ImageBarnnerFrameLayout.FrameLayoutLisenner,
        AppBarLayout.OnOffsetChangedListener,
        AdapterView.OnItemClickListener {

    private AppBarLayout appBar;

    /**
     * 大布局背景，遮罩层
     */
    private View bgContent;
    /**
     * 展开状态下toolbar显示的内容
     */
    private View toolbarOpen;
    /**
     * 展开状态下toolbar的遮罩层
     */
    private View bgToolbarOpen;
    /**
     * 收缩状态下toolbar显示的内容
     */
    private View toolbarClose;
    /**
     * 收缩状态下toolbar的遮罩层
     */

    private View bgToolbarClose;

    private LinearLayout open_home;
    private LinearLayout qktg;
    private LinearLayout fblc;

    private ImageBarnnerFrameLayout mGroup;

    /**
     * 轮播图存储
     */
    private int[] ids = new int[]{
            R.drawable.qikan1,
            R.drawable.qikan2,
            R.drawable.qikan3,
            //R.drawable.loadingpic44,
            //R.drawable.loadingpic48
    };

    //下拉菜单
    DropDownMenu dropDownMenu;
    private String headers[] = {"类别", "收录", "信息", "审稿"};
    private List<View> popuViews = new ArrayList<View>();

    private String leibie[] = {"不限", "教育", "医学", "经管", "工业", "农业"};
    private String shoulu[] = {"不限", "教育期刊", "医学期刊", "经管期刊", "工业期刊", "农业期刊"};
    private String xinxi[] = {"不限", "1", "2", "3", "4", "5"};
    private String shengao[] = {"不限", "1个月内", "1-3个月", "3-6个月", "6-9个月", "9-12个月"};
    //private int[] imaglds = {R.drawable.yaodao, R.drawable.guiqie, R.drawable.deng, R.drawable.loadingpic44, R.drawable.loadingpic48};

    private List<String> leibieFragments = new ArrayList<>();
    private List<String> shouluFragments = new ArrayList<>();
    private List<String> xinxiFragments = new ArrayList<>();

    private int allPosition = 0;

    private GridDropDownMenuAdapter leibieAdapter;
    private ListDropDownMenuAdapter shouluAdapter, xinxiAdapter;
    private ShengaoAdapter shengaoAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfirst);
        //轮播图
        mGroup = findViewById(R.id.image_group);
        mGroup.setLisenner(this);
        ImageBarnnerClinListener();
        //首页头部伸缩
        appBar = findViewById(R.id.app_bar);
        bgContent = findViewById(R.id.bg_content);
        toolbarOpen = findViewById(R.id.include_toolbar_open);
        bgToolbarOpen = findViewById(R.id.bg_toolbar_open);
        toolbarClose = findViewById(R.id.include_toolbar_close);
        bgToolbarClose = findViewById(R.id.bg_toolbar_close);

        open_home = findViewById(R.id.open_home);
        qktg = findViewById(R.id.qikanmulu);
        fblc = findViewById(R.id.fabiao);

        appBar.addOnOffsetChangedListener(this);

        //下拉菜单
        dropDownMenu = findViewById(R.id.dropdownMenu);
        initViews();

        //我的主页点击事件
        open_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                //finish();
            }
        });

        //期刊投稿点击事件
        qktg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QLtougao.class);
                startActivity(intent);
            }
        });

        //发表流程点击事件
        fblc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FBliucheng.class);
                startActivity(intent);
            }
        });


       /* for (int i = 0;i < ids.length;i++){
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
            iv.setImageResource(ids[i]);

            mGroup.addView(iv);
        }
        mGroup.setLister(this);*/

        leibieFragments.add(MyFragmentLb1Unlimited.class.getName());
        leibieFragments.add(MyFragmentLb2Edu.class.getName());
        leibieFragments.add(MyFragmentLb4JG.class.getName());
        leibieFragments.add(MyFragmentLb5GY.class.getName());
        leibieFragments.add(MyFragmentLb3Medical.class.getName());
        shouluFragments.add(MyFragmentSl1Unlimited.class.getName());
        shouluFragments.add(MyFragmentSl2Edu.class.getName());
        shouluFragments.add(MyFragmentSl3Medical.class.getName());
        shouluFragments.add(MyFragmentSl4JG.class.getName());
        shouluFragments.add(MyFragmentSl5GY.class.getName());

        //listview默认打开
        openListview();

    }



    /**
     * 下拉菜单方法
     */
    private void initViews() {
        int i = 0, j = 1, k = 2;


        ListView lvleibie = new ListView(this);
        lvleibie.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        leibieAdapter = new GridDropDownMenuAdapter(this, Arrays.asList(leibie));
        lvleibie.setDividerHeight(0);
        lvleibie.setId(i);
        lvleibie.setAdapter(leibieAdapter);

        ListView lvshoulu = new ListView(this);
        shouluAdapter = new ListDropDownMenuAdapter(this, Arrays.asList(shoulu));
        lvshoulu.setDividerHeight(0);
        lvshoulu.setId(j);
        lvshoulu.setAdapter(shouluAdapter);

        ListView lvxinxi = new ListView(this);
        xinxiAdapter = new ListDropDownMenuAdapter(this, Arrays.asList(xinxi));
        lvxinxi.setId(k);
        lvxinxi.setDividerHeight(0);
        lvxinxi.setAdapter(xinxiAdapter);

        View constellationView = getLayoutInflater().inflate(R.layout.item_layout_constellation, null);
        GridView gView = constellationView.findViewById(R.id.constellation);
        TextView tView = constellationView.findViewById(R.id.ok);
        shengaoAdapter = new ShengaoAdapter(this, Arrays.asList(shengao));
        gView.setAdapter(shengaoAdapter);

        lvleibie.setOnItemClickListener(this);
        lvshoulu.setOnItemClickListener(this);
        lvxinxi.setOnItemClickListener(this);
        tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        popuViews.add(lvleibie);
        popuViews.add(lvshoulu);
        popuViews.add(lvxinxi);
        popuViews.add(constellationView);

        ImageView contentViews = new ImageView(this);
        contentViews.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        contentViews.setScaleType(ImageView.ScaleType.CENTER_CROP);

        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popuViews, contentViews);

    }

    private void ImageBarnnerClinListener() {
        /**
         * 我们需要计算出当前手机的宽度
         */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        C.WITTH = dm.widthPixels;

        List<Bitmap> list = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ids[i]);
            list.add(bitmap);
        }

        mGroup.addBitmaps(list);
    }

    /**
     * 轮播图的点击事件
     */
    @Override
    public void clickImageIndex(int pos) {
        Toast.makeText(this, "pos" + pos, Toast.LENGTH_SHORT).show();
    }

    /**
     * 下面两个方法实现拟淘宝首页头部伸缩效果的方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        appBar.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //垂直方向偏移量
        int offset = Math.abs(verticalOffset);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange();
        if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
            toolbarOpen.setVisibility(View.VISIBLE);
            toolbarClose.setVisibility(View.GONE);
            //根据偏移百分比 计算透明值
            float scale2 = (float) offset / (scrollRange / 2);
            int alpha2 = (int) (255 * scale2);
            bgToolbarOpen.setBackgroundColor(Color.argb(alpha2, 25, 131, 209));
        } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
            toolbarClose.setVisibility(View.VISIBLE);
            toolbarOpen.setVisibility(View.GONE);
            float scale3 = (float) (scrollRange - offset) / (scrollRange / 2);
            int alpha3 = (int) (255 * scale3);
            bgToolbarClose.setBackgroundColor(Color.argb(alpha3, 25, 131, 209));

        }
        //根据偏移百分比计算扫一扫布局的透明度值
        float scale = (float) offset / scrollRange;
        int alpha = (int) (255 * scale);
        bgContent.setBackgroundColor(Color.argb(alpha, 25, 131, 209));
    }

    /**
     *下拉菜单点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case 0://类别
                leibieAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : leibie[position]);
                dropDownMenu.closeMenu();
                showFragmentLB(position);
                break;
            case 1://收录
                shouluAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : shoulu[position]);
                dropDownMenu.closeMenu();
                showFragmentSL(position);
                break;
            case 2://信息

                break;
        }
    }

    private void showFragmentLB(int position) {
        if (position < leibieFragments.size()) {
            String fragmentName = leibieFragments.get(position);
            if (fragmentName != null && !fragmentName.isEmpty()) {
                Fragment fragment = Fragment.instantiate(MainActivity.this,
                        fragmentName);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commitNowAllowingStateLoss();
            }
        }
    }

    private void showFragmentSL(int position) {
        if (position < shouluFragments.size()) {
            String fragmentName = shouluFragments.get(position);
            if (fragmentName != null && !fragmentName.isEmpty()) {
                Fragment fragment = Fragment.instantiate(MainActivity.this,
                        fragmentName);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commitNowAllowingStateLoss();
            }
        }
    }
    private void openListview() {
        String fragmentName = leibieFragments.get(0);
        Fragment fragment = Fragment.instantiate(MainActivity.this,
                fragmentName);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNowAllowingStateLoss();
    }
}
