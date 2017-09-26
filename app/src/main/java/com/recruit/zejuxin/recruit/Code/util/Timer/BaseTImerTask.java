package com.recruit.zejuxin.recruit.Code.util.Timer;

import java.util.TimerTask;

/**
 * Created by 10942 on 2017/9/4 0004.
 */

public class BaseTImerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTImerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }


    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
