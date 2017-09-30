package com.recruit.zejuxin.recruit.Fragment.main.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.recruit.zejuxin.recruit.Bean.HomeBean;

import java.util.ArrayList;

/**
 * Created by 10942 on 2017/9/29 0029.
 */

public class listAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<HomeBean> mHomeBeen;

    public listAdapter(Context context, ArrayList<HomeBean> homeBeen) {
        mContext = context;
        mHomeBeen = homeBeen;
    }

    @Override
    public int getCount() {
        return mHomeBeen.size();
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

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
