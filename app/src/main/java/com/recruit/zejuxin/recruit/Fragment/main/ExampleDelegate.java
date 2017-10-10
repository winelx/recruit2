package com.recruit.zejuxin.recruit.Fragment.main;

import android.graphics.Color;
import android.os.Bundle;

import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BaseBottomDelegate;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomItemDalegate;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomTabBean;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.ItemBuilder;
import com.recruit.zejuxin.recruit.Fragment.main.home.homedelegate;
import com.recruit.zejuxin.recruit.Fragment.main.message.messageDelegate;
import com.recruit.zejuxin.recruit.Fragment.main.mine.userDelegate;
import com.recruit.zejuxin.recruit.Fragment.main.video.videoDelegate;

import java.util.LinkedHashMap;

/**
 * Created by 10942 on 2017/9/25 0025.
 */

public class ExampleDelegate extends BaseBottomDelegate {


    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDalegate> setItem(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDalegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home 30sp}", "主页"), new homedelegate());
        items.put(new BottomTabBean("{fa-commenting }", "消息"), new messageDelegate());
        items.put(new BottomTabBean("{fa-film 23sp}", "视频"), new videoDelegate());
        items.put(new BottomTabBean("{fa-user }", "我的"), new userDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#01BAA6");
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);

    }
}
