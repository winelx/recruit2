package com.recruit.zejuxin.recruit.Fragment.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Fragment.main.mine.userDelegate;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import butterknife.OnClick;

/**
 * Created by 10942 on 2017/9/26 026.
 */

public class logUpdelegate extends LatteDelegate {
    @OnClick(R2.id.btn_sign_up)
    void result() {
        start(new userDelegate());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
