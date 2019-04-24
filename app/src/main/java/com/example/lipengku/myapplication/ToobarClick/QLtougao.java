package com.example.lipengku.myapplication.ToobarClick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lipengku.myapplication.Adapter.GridDropDownMenuAdapter;
import com.example.lipengku.myapplication.Adapter.ListDropDownMenuAdapter;
import com.example.lipengku.myapplication.Adapter.ShengaoAdapter;
import com.example.lipengku.myapplication.MainActivity;
import com.example.lipengku.myapplication.Menu.DropDownMenu;
import com.example.lipengku.myapplication.R;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QLtougao extends AppCompatActivity implements
        AppBarLayout.OnOffsetChangedListener,
        AdapterView.OnItemClickListener {

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

    //下拉菜单适配器
    private GridDropDownMenuAdapter leibieAdapter;
    private ListDropDownMenuAdapter shouluAdapter, xinxiAdapter;
    private ShengaoAdapter shengaoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qikanmulu);
        //下拉菜单
        dropDownMenu = findViewById(R.id.dropdownMenu);
        initViews();

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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

    }

    /**
     * 下拉菜单点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case 0://类别
                leibieAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : leibie[position]);
                dropDownMenu.closeMenu();
                Toast.makeText(this, "pos" + position, Toast.LENGTH_SHORT).show();
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
                Fragment fragment = Fragment.instantiate(QLtougao.this,
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
                Fragment fragment = Fragment.instantiate(QLtougao.this,
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
        Fragment fragment = Fragment.instantiate(QLtougao.this,
                fragmentName);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNowAllowingStateLoss();
    }
}
