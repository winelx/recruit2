package com.recruit.zejuxin.recruit.Fragment.Luncher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Code.ui.scanner.LauncherHolderCreator;
import com.recruit.zejuxin.recruit.Code.ui.scanner.ScrollLauncherTag;
import com.recruit.zejuxin.recruit.Code.util.Loader.ILauncherListener;
import com.recruit.zejuxin.recruit.Code.util.storage.LattePreference;
import com.recruit.zejuxin.recruit.Fragment.main.ExampleDelegate;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 10942 on 2017/9/25 0025.
 */

public class LauncherScrollDelegate extends LatteDelegate {
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;
    @BindView(R2.id.banner)
    ConvenientBanner<Integer> mIntegerConvenientBanner = null;

    @OnClick(R2.id.scroo_new)
    void scroo() {
        startWithPop(new ExampleDelegate());
    }

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
                .setCanLoop(false);
        LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcherscroll;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        INTEGERS.clear();
        initBanner();
    }
}
