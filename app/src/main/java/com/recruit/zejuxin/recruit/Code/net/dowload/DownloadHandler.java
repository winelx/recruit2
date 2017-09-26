package com.recruit.zejuxin.recruit.Code.net.dowload;

import android.os.AsyncTask;

import com.recruit.zejuxin.recruit.Code.net.RestCreator;
import com.recruit.zejuxin.recruit.Code.net.callBack.IError;
import com.recruit.zejuxin.recruit.Code.net.callBack.IFailure;
import com.recruit.zejuxin.recruit.Code.net.callBack.IRequest;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 10942 on 2017/9/1 0001.
 * 下载文件请求
 */

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    //
    public DownloadHandler(String url,
                           IRequest request,
                           String downDir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    //进行网络请求
    public final void handleDownLOand() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //请求的数据的处理
        RestCreator.getRestService()
                .download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    //请求数据的回调
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();//请求体
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);//使用异步的方法将数据写入，拿到类的实例并使用
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, responseBody, NAME);
                            //哦按段文件是否下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestStart();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.OnError(response.code(), response.message());
                            }
                        }
                        RestCreator.getParams().clear();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onIFailure();
                            RestCreator.getParams().clear();
                        }
                    }
                });
    }


}
