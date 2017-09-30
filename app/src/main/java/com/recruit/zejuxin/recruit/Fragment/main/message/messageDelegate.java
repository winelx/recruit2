package com.recruit.zejuxin.recruit.Fragment.main.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.recruit.zejuxin.recruit.Adapter.MyPager;
import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomItemDalegate;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class messageDelegate extends BottomItemDalegate {
    @BindView(R2.id.message_tab)
    TabLayout mes_tab;
    @BindView(R2.id.message_pager)
    ViewPager mes_pager;
    private View view1, view2, view3;
    private List<View> viewList;//把需要滑动的页卡添加到这个list中
    private List<String> titleList;//viewpager的标题



    @Override
    public Object setLayout() {
        return R.layout.delagete_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        intieData();
    }

    private void intieData() {
        LayoutInflater lf = LayoutInflater.from(latte.geteApplication());
        view1 = lf.inflate(R.layout.delegate_message_record, null);//聊天记录4
        view2 = lf.inflate(R.layout.delegate_message_check, null);//查看我
        view3 = lf.inflate(R.layout.delegate_message_newpalce, null);//最新职位
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("聊天");
        titleList.add("谁看过我");
        titleList.add("最新职位");
        mes_tab.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        MyPager mAdapter = new MyPager(latte.geteApplication(), viewList, titleList);
        mes_pager.setAdapter(mAdapter);//给ViewPager设置适配器
        mes_tab.setupWithViewPager(mes_pager);//将TabLayout和ViewPager关联起来。
        mes_tab.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }
}
