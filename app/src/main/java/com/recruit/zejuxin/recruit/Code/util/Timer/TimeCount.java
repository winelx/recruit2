package com.recruit.zejuxin.recruit.Code.util.Timer;

import android.os.CountDownTimer;
import android.widget.Button;

import com.recruit.zejuxin.recruit.R;

/**
 * Created by 10942 on 2017/10/9 0009.
 */

public class TimeCount extends CountDownTimer {
    private Button btn_count;

    public TimeCount(long millisInFuture, long countDownInterval,Button btn_count) {
        super(millisInFuture, countDownInterval);
        this.btn_count = btn_count;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btn_count.setEnabled(false);
        btn_count.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
        btn_count.setEnabled(true);
        btn_count.setText(R.string.get_verification_code);

    }

}
