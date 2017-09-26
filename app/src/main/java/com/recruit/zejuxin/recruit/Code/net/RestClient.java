package com.recruit.zejuxin.recruit.Code.net;

import android.content.Context;

import com.recruit.zejuxin.recruit.Code.net.callBack.IError;
import com.recruit.zejuxin.recruit.Code.net.callBack.IFailure;
import com.recruit.zejuxin.recruit.Code.net.callBack.IRequest;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;
import com.recruit.zejuxin.recruit.Code.net.callBack.RequestCallbacks;
import com.recruit.zejuxin.recruit.Code.net.dowload.DownloadHandler;
import com.recruit.zejuxin.recruit.Code.ui.LattetLoader;
import com.recruit.zejuxin.recruit.Code.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by 10942 on 2017/8/28 0028.
 */

public class RestClient {
    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure IFAILURE;
    private final IError ERROR;
    private final File FILE;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    public final Context mContext;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    public RestClient(String url,
                      Map<String, Object> parmas,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      File file,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(parmas);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.IFAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.mContext = context;
        this.FILE = file;
    }

    // builder 作为所有的请求的初始接口
    public static RestClientBuilder Builder() {
        return new RestClientBuilder();
    }

    public void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LattetLoader.showLoading(mContext, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAM:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAM:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.detale(URL, PARAMS);
                break;
            case UOLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new
                RequestCallbacks(
                REQUEST,
                SUCCESS, IFAILURE, ERROR, LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAM);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAM);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    //文件下载的网络请求
    public final void download() {
        //将参数传入DownloadHandler实例中，在DownloadHandler中进行网络请求的详细处理
        new DownloadHandler(URL, REQUEST, mDownloadDir, mExtension, mName,
                SUCCESS, IFAILURE, ERROR).handleDownLOand();
    }
}
