package com.example.lipengku.myapplication.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lipengku.myapplication.R;

import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListViewAdapter(Context context,List<Map<String,Object>> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应xml中的控件
     */

    public final class Zujian{
        public ImageView image1;
        public ImageView image2;
        public TextView title1;
        public TextView title2;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    /**
     *获得某一位置数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian = null;
        if(convertView==null){
            zujian = new Zujian();
            //获得组件，实例化组件
            convertView = layoutInflater.inflate(R.layout.item_fragment_lv,null);
            zujian.image1 = convertView.findViewById(R.id.image_view1);
            zujian.title1 = convertView.findViewById(R.id.title_text1);
            zujian.image2 = convertView.findViewById(R.id.image_view2);
            zujian.title2 = convertView.findViewById(R.id.title_text2);
            convertView.setTag(zujian);
        }else {
            zujian = (Zujian) convertView.getTag();
        }

        //绑定数据
        zujian.image1.setBackgroundResource((int) data.get(position).get("image"));
        zujian.title1.setText((String) data.get(position).get("text"));
        zujian.image2.setBackgroundResource((int) data.get(position).get("image1"));
        zujian.title2.setText((String) data.get(position).get("text1"));
        return convertView;
    }
}
