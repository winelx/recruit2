package com.recruit.zejuxin.recruit.Fragment.main.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomItemDalegate;
import com.recruit.zejuxin.recruit.R;


/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class homedelegate extends BottomItemDalegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        Context context = latte.geteApplication();
        init(context);
    }

    private void init(Context context) {

    }
}
