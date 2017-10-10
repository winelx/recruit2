package com.recruit.zejuxin.recruit.Fragment.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Code.net.RestClient;
import com.recruit.zejuxin.recruit.Code.net.callBack.IError;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;
import com.recruit.zejuxin.recruit.Code.util.Request;
import com.recruit.zejuxin.recruit.Code.util.Timer.CountDownTimerUtils;
import com.recruit.zejuxin.recruit.Code.util.Utils;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

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
    @BindView(R2.id.logup_user_shuzi)
    AppCompatButton shuzi;
    @BindView(R2.id.login_text_boolean)
    CheckBox iconview;
    @BindView(R2.id.login_checkbox)
    CheckBox mCheckBox;

    CountDownTimerUtils mCountDownTimerUtils;

    //获取验证码
    @OnClick(R2.id.logup_user_shuzi)
    void verify() {
        logphone = phone.getText().toString();
        if (logphone.isEmpty()) {
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!Utils.isMobileNO(logphone)) {
                Toast.makeText(getActivity(), "手机号格式不对", Toast.LENGTH_SHORT).show();
            } else {
                mCountDownTimerUtils = new CountDownTimerUtils(shuzi, 60000, 1000);
                mCountDownTimerUtils.start();
                Toast.makeText(getActivity(), logphone, Toast.LENGTH_SHORT).show();
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
    }


    //重复密码
    @BindView(R2.id.logup_passwords)
    AppCompatEditText passwords;

    //注册
    @OnClick(R2.id.btn_sign_up)
    void result() {
        logphone = phone.getText().toString();
        logpass = password.getText().toString();
        logcodes = code.getText().toString();
        String mima = passwords.getText().toString();
        if (logpass.isEmpty()) {
            Toast.makeText(getActivity(), "不能问为空", Toast.LENGTH_SHORT).show();
        } else {
            if (logpass.length() < 6) {
                Toast.makeText(getActivity(), "密码长度必须大于6位", Toast.LENGTH_SHORT).show();
            } else {
                if (!Utils.isRightPwd(logpass)) {
                    Toast.makeText(getActivity(), "密码必须包含字母和数字", Toast.LENGTH_SHORT).show();
                } else {
                    if (mima.equals(logpass)) {
                        if (logcodes.isEmpty() || logcodes.length() != 4) {
                            Toast.makeText(getActivity(), "验证码不对", Toast.LENGTH_SHORT).show();
                        } else {
                            if (mCheckBox.isChecked()) {
                                //请求数据
                                data();
                            } else {
                                Toast.makeText(getActivity(), "阅读并同意协议", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getActivity(), "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        }

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        //显示隐藏
        iconview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }

        });
    }


    private void data() {
        RestClient.Builder()
                .url(Request.LOGIN)
                .params("iphone", logphone)
                .params("Password", logpass)
                .params("yzm", logcodes)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String msg) {
                        Log.i("msg", msg);
                        switch (msg) {
                            case "0":
                                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                                break;
                            case "1":
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                                pop();
                                break;
                            case "102":
                                Toast.makeText(getActivity(), "手机号错误", Toast.LENGTH_SHORT).show();
                                break;
                            case "103":
                                Toast.makeText(getActivity(), "邮箱存在", Toast.LENGTH_SHORT).show();
                                break;
                            case "105":
                                Toast.makeText(getActivity(), "验证码错误", Toast.LENGTH_SHORT).show();
                                break;
                        }
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


}
