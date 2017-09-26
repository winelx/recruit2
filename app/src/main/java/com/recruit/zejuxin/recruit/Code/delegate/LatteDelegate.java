package com.recruit.zejuxin.recruit.Code.delegate;

/**
 * Created by 10942 on 2017/9/25 0025.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {
    public <T extends LatteDelegate>
    T getParentDegate() {
        return (T) getParentFragment();
    }
}
