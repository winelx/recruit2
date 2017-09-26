package com.recruit.zejuxin.recruit.Fragment.Luncher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Code.ui.scanner.LauncherHolderCreator;
import com.recruit.zejuxin.recruit.Code.ui.scanner.ScrollLauncherTag;
import com.recruit.zejuxin.recruit.Code.util.Loader.ILauncherListener;
import com.recruit.zejuxin.recruit.Code.util.storage.LattePreference;
import com.recruit.zejuxin.recruit.R;

import java.util.ArrayList;

/**
 * Created by 10942 on 2017/9/25 0025.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mIntegerConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    public void initBanner() {
        //启动页的轮播图片
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_05);
        //设置Banner数据
        mIntegerConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mIntegerConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mIntegerConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }


    @Override
    public void onItemClick(int position) {
        //如果点击最后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查是否登陆
        }
    }
}
