package com.recruit.zejuxin.recruit.Code.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.recruit.zejuxin.recruit.Code.app.latte;

/**
 * Created by 10942 on 2017/9/26 0026.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = latte.geteApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = latte.geteApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
