package com.recruit.zejuxin.recruit.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by 10942 on 2017/9/29 0029.
 */

public class homdAdapter extends BaseAdapter {

    private ArrayList<String> Xbanner;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    public void setXbanner(ArrayList<String> list) {
        Xbanner = list;
        notifyDataSetChanged();

    }
}
