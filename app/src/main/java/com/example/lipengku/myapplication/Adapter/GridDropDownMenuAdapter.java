package com.example.lipengku.myapplication.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lipengku.myapplication.R;

import java.util.List;

public class GridDropDownMenuAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int checkItemPosition = -1;

    public void setCheckItem(int Position){
        checkItemPosition = Position;
        notifyDataSetChanged();
    }

    public GridDropDownMenuAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_drop_down,null);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }
        fillValue(position,viewHolder);
        return convertView;
    }

    public void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.tv.setText(list.get(position));
        if (checkItemPosition != -1){
            if (checkItemPosition == position){
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.ic_launcher_background));
                viewHolder.tv.setCompoundDrawables(null,null,
                        context.getResources().getDrawable(R.drawable.dot_select),null);
            }else {
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                viewHolder.tv.setCompoundDrawables(null,null,
                        null,null);
            }
        }
    }

    class ViewHolder{
        TextView tv;
    }
}
