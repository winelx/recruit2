package com.recruit.zejuxin.recruit;


import com.recruit.zejuxin.recruit.Code.activity.ProxyActivity;
import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Fragment.Luncher.LauncherDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegare() {
        return new LauncherDelegate();
    }
}
