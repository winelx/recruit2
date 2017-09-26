package com.recruit.zejuxin.recruit;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.recruit.zejuxin.recruit.Code.app.latte;


/**
 * @class name：com.example.a10942.commerce
 * @anthor winelx
 * @time 2017/8/21 0021 10:59
 */
public class CommerceApplicaton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://127.0.0.17")
                .configure();

    }
}
