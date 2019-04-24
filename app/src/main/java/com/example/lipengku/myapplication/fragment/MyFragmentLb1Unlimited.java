package com.example.lipengku.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lipengku.myapplication.Adapter.ListViewAdapter;
import com.example.lipengku.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFragmentLb1Unlimited extends Fragment {

    private ListView listView;

    /**
     * 图存储
     */
    private int[] ids = new int[]{
            R.drawable.gyi,
            R.drawable.jya,
            R.drawable.jyb,
            R.drawable.jza,
            R.drawable.jzb,
            R.drawable.nya,
            R.drawable.nyb,
            R.drawable.yxa,
            R.drawable.yxb,
            R.drawable.gyd,
    };
    private String name[] = {"机电信息",
            "科学导报", "语文课内外", "中国房地产业", "建筑与装饰", "农家参谋",
            "科技创新与应用","Journal of Oncology Research","Journal of Oncology Research",
            "工程技术研究"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragmen_sl_gongye,container,
                false);
        listView = view.findViewById(R.id.listview);
        List<Map<String,Object>> list = getData();
        listView.setAdapter(new ListViewAdapter(getActivity(),list));
        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for(int i = 0;i<10;i+=2){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("image",ids[i]);
            map.put("text",name[i]);
            map.put("image1",ids[i+1]);
            map.put("text1",name[i+1]);
            list.add(map);
        }
        return list;
    }


}
