package com.recruit.zejuxin.recruit.Fragment.main.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomItemDalegate;
import com.recruit.zejuxin.recruit.R;

/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class userDelegate extends BottomItemDalegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_mine;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
