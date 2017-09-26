package com.recruit.zejuxin.recruit.Code.delegate.Bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.R;


/**
 * Created by 10942 on 2017/9/25 0025.
 * 处理首页fragment的返回键
 */

public abstract class BottomItemDalegate extends LatteDelegate implements View.OnKeyListener {
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    /**
     * 重写返回键
     *
     * @param v
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }


}
