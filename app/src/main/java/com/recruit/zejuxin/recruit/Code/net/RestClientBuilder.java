package com.recruit.zejuxin.recruit.Code.net;

import android.content.Context;

import com.recruit.zejuxin.recruit.Code.net.callBack.IError;
import com.recruit.zejuxin.recruit.Code.net.callBack.IFailure;
import com.recruit.zejuxin.recruit.Code.net.callBack.IRequest;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;
import com.recruit.zejuxin.recruit.Code.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 10942 on 2017/8/29 0029.
 */

public class RestClientBuilder {

    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private File falie;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    private String mDownloadDir=null;
    private String mExtension=null;
    private String mName=null;
    RestClientBuilder() {
    }

    public final RestClientBuilder loaderl(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }
    public final RestClientBuilder Request(String key, Object object) {
        this.PARAMS.put(key, object);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    public final RestClientBuilder falie(File file) {
        this.falie = file;
        return this;
    }

    public final RestClientBuilder falie(String Path) {
        this.falie = new File(Path);
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, falie, mBody, mLoaderStyle, mContext);
    }


}
