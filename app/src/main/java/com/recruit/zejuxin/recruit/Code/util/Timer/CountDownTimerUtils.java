package com.recruit.zejuxin.recruit.Code.util.Timer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.recruit.zejuxin.recruit.R;

/**
 * Created by 10942 on 2017/10/10 0010.
 */

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(AppCompatButton textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "s后重新发送");  //设置倒计时时间
        mTextView.setBackgroundResource(R.drawable.bg_identify_code_press); //设置按钮为灰
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.bg_identify_code_normal);
    }
}