package com.recruit.zejuxin.recruit.Fragment.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Code.net.RestClient;
import com.recruit.zejuxin.recruit.Code.net.callBack.IError;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;
import com.recruit.zejuxin.recruit.Code.util.Request;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 10942 on 2017/9/26 026.
 * 注册
 */

public class logUpdelegate extends LatteDelegate {
    private String logphone, logpass, logcodes;
    //手机号
    @BindView(R2.id.logup_phone)
    AppCompatEditText phone;
    //密码
    @BindView(R2.id.logup_password)
    AppCompatEditText password;
    //验证码
    @BindView(R2.id.logup_user_code)
    AppCompatEditText code;

    //获取验证码
    @OnClick(R2.id.logup_user_shuzi)
    void verify() {
        if (logphone.isEmpty() || logpass.isEmpty() || logcodes.isEmpty()) {
            Toast.makeText(getActivity(), "手机号不能为空  ", Toast.LENGTH_SHORT).show();
        }
        if (!isMobileNO(logphone)) {
            Toast.makeText(getActivity(), "手机号格式不对", Toast.LENGTH_SHORT).show();
        } else {
            RestClient.Builder()
                    .url(Request.VERIFY)
                    .params("phone", logphone)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String msg) {

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void OnError(int code, String msg) {
                        }
                    })
                    .build()
                    .get();
        }
    }

    //重复密码
    @BindView(R2.id.logup_passwords)
    AppCompatEditText passwords;

    //注册
    @OnClick(R2.id.btn_sign_up)
    void result() {
        if (logpass.length() >= 6) {
            Toast.makeText(getActivity(), "密码长度必须大于6位", Toast.LENGTH_SHORT).show();
        }
        if (!isRightPwd(logpass)) {
            Toast.makeText(getActivity(), "密码必须包含字母和数字", Toast.LENGTH_SHORT).show();
        }
        if (logcodes.length() == 4) {
            Toast.makeText(getActivity(), "验证码不对", Toast.LENGTH_SHORT).show();
        }

        data();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        logphone = phone.getText().toString();
        logpass = password.getText().toString();
        logcodes = code.getText().toString();
    }


    private void data() {
        RestClient.Builder()
                .url("")
                .params("", "")
                .params("", "")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String msg) {

                    }
                })
                .error(new IError() {
                    @Override
                    public void OnError(int code, String msg) {

                    }
                })
                .build()
                .post();

    }


    //�验证手机号是否正确ֻ
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //�验证密码
    public static boolean isRightPwd(String pwd) {
        Pattern p = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$)[0-9a-zA-Z]{8,16}$");
        Matcher m = p.matcher(pwd);
        return m.matches();
    }
}
