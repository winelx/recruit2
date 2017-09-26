package com.recruit.zejuxin.recruit.Code.ui.scanner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by 10942 on 2017/9/4 0004.
 */

public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mAppCompatImageView = null;

    @Override
    public View createView(Context context) {
        mAppCompatImageView = new AppCompatImageView(context);
        return mAppCompatImageView;
    }

    //滑动更新UI
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mAppCompatImageView.setBackgroundResource(data);//更新没次的图片
    }
}
