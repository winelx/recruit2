package com.recruit.zejuxin.recruit.Fragment.Luncher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Code.ui.scanner.ScrollLauncherTag;
import com.recruit.zejuxin.recruit.Code.util.Timer.BaseTImerTask;
import com.recruit.zejuxin.recruit.Code.util.Timer.ITimerListener;
import com.recruit.zejuxin.recruit.Code.util.storage.LattePreference;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 10942 on 2017/9/4 0004.
 * tv_launcher_timer
 * 启动页
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;


    @OnClick(R2.id.tv_launcher_timer)
    void onClicKTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            chaecLauncher();
        }

    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTImerTask task = new BaseTImerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    public void chaecLauncher() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户费否登陆
        }

    }

    @Override
    public void onTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            chaecLauncher();
                        }
                    }
                }
            }
        });
    }


}
