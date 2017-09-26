package com.recruit.zejuxin.recruit.Code.net.dowload;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.net.callBack.IRequest;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;
import com.recruit.zejuxin.recruit.Code.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by 10942 on 2017/9/1 0001.
 * 对下载请求做异步写入并安装
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUESE;
    public final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUESE, ISuccess SUCCESS) {
        this.REQUESE = REQUESE;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";

        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }

    }

    //安装apk触发条件
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUESE != null) {
            REQUESE.onRequestEnd();
        }
        autoInstallApk(file);
    }

    //安装APk
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {//拿到apk路径
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            latte.geteApplication().startActivity(intent);

        }
    }
}
