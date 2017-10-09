package com.recruit.zejuxin.recruit.Code.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.ContentFrameLayout;

import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.R;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * Created by 10942 on 2017/9/25 0025.
 */

public abstract class ProxyActivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegare();

    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);

    }

    private void initContainer(@Nullable Bundle saveInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (saveInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegare());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    public FragmentManager getFm() {
        return fm = getSupportFragmentManager();
    }
}
