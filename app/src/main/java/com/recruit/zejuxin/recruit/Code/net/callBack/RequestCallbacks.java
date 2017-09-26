package com.recruit.zejuxin.recruit.Code.net.callBack;

import android.os.Handler;

import com.recruit.zejuxin.recruit.Code.ui.LattetLoader;
import com.recruit.zejuxin.recruit.Code.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 10942 on 2017/8/30 0030.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure IFAILURE;
    private final IError ERROR;
    private final LoaderStyle mLoaderStyle;
    private static final Handler HANDLER = new Handler();
    private int a = 2000;

    public RequestCallbacks(IRequest REQUEST, ISuccess SUCCESS, IFailure IFAILURE, IError ERROR, LoaderStyle loaderStyle) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.IFAILURE = IFAILURE;
        this.ERROR = ERROR;
        this.mLoaderStyle = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> responsed) {
        if (responsed.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(responsed.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.OnError(responsed.code(), responsed.message());
            }
        }
        stopLoding();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null) {
            IFAILURE.onIFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoding();
    }

    private void stopLoding() {
        if (mLoaderStyle != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LattetLoader.stopLoading();
                }
            }, a);
        }
    }

}
