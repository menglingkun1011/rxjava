package com.example.huibin.androidtest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huibin.androidtest.bean.MenuItemBean;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private Context context;
    private List<MenuItemBean> data;

    public MenuAdapter(List<MenuItemBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = null;
        if(convertView == null){
            tv = new TextView(context);
            tv.setPadding(10,10,10,10);
            tv.setGravity(Gravity.CENTER);
            convertView = tv;
            convertView.setTag(tv);
        }else{
            tv = (TextView) convertView.getTag();
        }
        MenuItemBean itemBean = data.get(position);
        tv.setText(itemBean.getTitle());

        if(itemBean.isSelect()){
            tv.setTextColor(Color.parseColor("#13d9c9"));
        }else{
            tv.setTextColor(Color.parseColor("#000000"));
        }

        return convertView;
    }
}
