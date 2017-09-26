package com.recruit.zejuxin.recruit.Fragment.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.recruit.zejuxin.recruit.Adapter.MyPager;
import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class Logindelegate extends LatteDelegate {
    @BindView(R2.id.login_tablayou)
    TabLayout tabLayout;
    @BindView(R2.id.login_viewpager)
    ViewPager viewPager;
    private List<View> viewList;//把需要滑动的页卡添加到这个list中
    private View view1, view2;//需要滑动的页卡
    private List<String> titleList;//viewpager的标题

    @Override
    public Object setLayout() {
        return R.layout.deletage_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initdata();
    }


    private void initdata() {
        LayoutInflater lf = LayoutInflater.from(latte.geteApplication());
        view1 = lf.inflate(R.layout.deletage_login_user, null);
        view2 = lf.inflate(R.layout.delegate_login_phone, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("账号登陆");
        titleList.add("手机登陆");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        MyPager mAdapter = new MyPager(latte.geteApplication(), viewList, titleList);
        viewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }
}
