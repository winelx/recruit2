package com.recruit.zejuxin.recruit.Fragment.main.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.delegate.Bottom.BottomItemDalegate;
import com.recruit.zejuxin.recruit.Fragment.sign.Logindelegate;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class userDelegate extends BottomItemDalegate {
    private Context context = latte.geteApplication();
    @BindView(R2.id.img)
    CircleImageView mImageView;
    private int REQUWST = 1;
    private Bundle mArgs = null;
    public static final String ORDER_TYPE = "ORDER_TYPE";
    final Logindelegate delegate = new Logindelegate();

    @OnClick(R2.id.img)
    void Login() {
        getParentDegate().getSupportDelegate().start(delegate);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_mine;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initData();
    }

    private void initData() {
        Toast.makeText(getActivity(), "Data", Toast.LENGTH_SHORT).show();
        Log.i("data", "onBind:");
    }
}
