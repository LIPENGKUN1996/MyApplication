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

public class MyFragmentSl4JG extends Fragment {
    private ListView listView;
    /**
     * 图存储
     */
    private int[] ids = new int[]{
            R.drawable.gya,
            R.drawable.gyb,
            R.drawable.gyc,
            R.drawable.gyd,
            R.drawable.gye,
            R.drawable.gyf,
            R.drawable.gyg,
            R.drawable.gyh,
            R.drawable.gyi,
            R.drawable.gya,
    };
    private String name[] = {"Journal of Mechanical Engineering Research",
            "机械管理开发", "中国新技术新产品", "工程技术研究", "工业", "新型工业化",
            "电子元器件与信息技术","中国金属通报","机电信息",
            "Journal of Mechanical Engineering Research"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragmen_sl_jingguan,container,
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
