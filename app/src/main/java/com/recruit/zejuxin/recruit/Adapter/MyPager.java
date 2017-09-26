package com.recruit.zejuxin.recruit.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class MyPager extends PagerAdapter {
    private List<View> mViewList;//把需要滑动的页卡添加到这个list中
    private List<String> titleList;//viewpager的标题
    private Context mContext;

    public MyPager(Context context, List<View> views, List<String> titleList) {
        this.mContext = context;
        this.mViewList = views;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return mViewList.size();//页卡数
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方推荐写法
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));//添加页卡
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));//删除页卡
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);//页卡标题
    }
}



