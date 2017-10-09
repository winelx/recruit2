package com.recruit.zejuxin.recruit.Fragment.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.recruit.zejuxin.recruit.Adapter.MyPager;
import com.recruit.zejuxin.recruit.Code.app.latte;
import com.recruit.zejuxin.recruit.Code.delegate.LatteDelegate;
import com.recruit.zejuxin.recruit.Code.net.RestClient;
import com.recruit.zejuxin.recruit.Code.net.callBack.IError;
import com.recruit.zejuxin.recruit.Code.net.callBack.ISuccess;
import com.recruit.zejuxin.recruit.Code.util.Request;
import com.recruit.zejuxin.recruit.Code.util.Timer.TimeCount;
import com.recruit.zejuxin.recruit.Fragment.main.ExampleDelegate;
import com.recruit.zejuxin.recruit.R;
import com.recruit.zejuxin.recruit.R2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 10942 on 2017/9/26
 */

public class Logindelegate extends LatteDelegate implements View.OnClickListener {
    @BindView(R2.id.login_tablayou)
    TabLayout tabLayout;
    @BindView(R2.id.login_viewpager)
    ViewPager viewPager;
    private JSONObject jsonObject = null;
    private List<View> viewList;//把需要滑动的页卡添加到这个list中
    private View view1, view2;//需要滑动的页卡
    private List<String> titleList;//viewpager的标题
    private EditText user, password;//用户名
    private CheckBox checkBox; //用户协议
    private CheckBox iconview;//密码显隐
    private AppCompatButton login;//登陆
    private AppCompatTextView forget, newuser;
    private Context mContext;
    //手机
    private EditText tle_phone, tle_password;
    private Button validation;
    private CheckBox phone_checkBox;
    private TextView xieyi;
    private AppCompatButton logup;
    final ExampleDelegate Examp = new ExampleDelegate();
    private TimeCount timeCount;

    @OnClick(R2.id.login_icon)
    void remove() {
        pop();
    }

    @Override
    public Object setLayout() {
        return R.layout.deletage_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initdata();//获取布局
        userdata();//用户名登陆
        phonedate();//手机号登陆
    }

    /**
     * 获取布局
     */
    private void initdata() {
        mContext = latte.geteApplication();

        LayoutInflater lf = LayoutInflater.from(latte.geteApplication());
        view1 = lf.inflate(R.layout.deletage_login_user, null);
        view2 = lf.inflate(R.layout.delegate_login_phone, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("账号登陆");
        titleList.add("手机登陆");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        MyPager mAdapter = new MyPager(latte.geteApplication(), viewList, titleList);
        viewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }

    /**
     * 用户名登陆
     */
    private void userdata() {
        //拿到布局ID；
        //用户名&邮箱
        user = (EditText) view1.findViewById(R.id.login_edit_user);
        //密码
        password = (EditText) view1.findViewById(R.id.login_edit_password);
        //显示隐藏
        iconview = (CheckBox) view1.findViewById(R.id.login_text_boolean);
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
        //登陆
        login = (AppCompatButton) view1.findViewById(R.id.login_button_login);
        login.setOnClickListener(this);
        //忘记密码
        forget = (AppCompatTextView) view1.findViewById(R.id.login_text_forget);
        forget.setOnClickListener(this);
        //注册
        newuser = (AppCompatTextView) view1.findViewById(R.id.login_text_new);
        newuser.setOnClickListener(this);
        //
        checkBox = (CheckBox) view1.findViewById(R.id.login_checkbox);
    }

    /**
     * 手机号登陆
     */
    private void phonedate() {

        //手机号
        tle_phone = (EditText) view2.findViewById(R.id.login_phone_edit_user);
        //密码
        tle_password = (EditText) view2.findViewById(R.id.login__phone_edit_password);
        //判断
        phone_checkBox = (CheckBox) view2.findViewById(R.id.login_phone_checkbox);
        //验证码
        validation = (Button) view2.findViewById(R.id.login_phone_text_boolean);
        validation.setOnClickListener(this);
        timeCount = new TimeCount(60000, 1000, validation);
        //协议
        xieyi = (TextView) view2.findViewById(R.id.login_phone_text);
        xieyi.setOnClickListener(this);
        //登陆
        logup = (AppCompatButton) view2.findViewById(R.id.login_phone_button_login);
        logup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final String name = user.getText().toString();
        final String passwords = password.getText().toString();
        final String tle_number = tle_phone.getText().toString();
        final String tle_pass = tle_password.getText().toString();
        switch (v.getId()) {
            case R.id.login_button_login://邮箱登陆
                if (name.isEmpty()) {
                    Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwords.isEmpty() || passwords.length() < 6) {
                        Toast.makeText(mContext, "请填写至少6位数密码", Toast.LENGTH_SHORT).show();
                    } else {
                        if (checkBox.isChecked() != true) {
                            Toast.makeText(mContext, "阅读并同意用户协议", Toast.LENGTH_SHORT).show();
                        } else {
                            //网络请求，地址url，参数params 成功回调success 失败回调error 请求方式 post
                            RestClient.Builder()
                                    .url(Request.userLogin)
                                    .params("iphone", tle_number)
                                    .params("passsword", tle_pass)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String msg) {
                                            Log.i("msg", msg);
                                            try {
                                                jsonObject = new JSONObject(msg);
                                                //获取到json数据中里的activity数组内容
                                                String resultCode = jsonObject.getString("messager");
                                                if (resultCode.equals("error")) {
                                                    Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    //有数据
                                                    start(Examp);
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    })
                                    .error(new IError() {
                                        @Override
                                        public void OnError(int code, String msg) {
                                            //Log.i("msg", msg);
                                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                    break;
                }
            case R.id.login_text_forget://忘记密码
                Toast.makeText(getActivity(), "ssss", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_text_new://邮箱注册
                final logUpdelegate delegate = new logUpdelegate();
                start(delegate);
                break;
            case R.id.login_phone_button_login://手机登陆
                if (tle_number.isEmpty()) {
                    Toast.makeText(mContext, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (tle_pass.isEmpty() || tle_pass.length() < 6) {
                        Toast.makeText(mContext, "验证码不对", Toast.LENGTH_SHORT).show();
                    } else {
                        if (phone_checkBox.isChecked() != true) {
                            Toast.makeText(mContext, "阅读并同意用户协议", Toast.LENGTH_SHORT).show();
                        } else {
                            RestClient.Builder()
                                    .url("")
                                    .params("iphone", tle_number)
                                    .params("passsword", tle_pass)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String msg) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(msg);
                                                //获取到json数据中里的activity数组内容
                                                String resultCode = jsonObject.getString("messager");

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
//
                                        }
                                    })
                                    .error(new IError() {
                                        @Override
                                        public void OnError(int code, String msg) {

                                        }
                                    })
                                    .build()
                                    .post();
                            Toast.makeText(mContext, "登陆成功", Toast.LENGTH_SHORT).show();


                        }
                    }
                }
                break;
            case R.id.login_phone_text_boolean://获取验证码
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    timeCount.start();
                }
                break;
            default:
                break;
        }
    }


    public static class Utils {
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        private static final int MIN_CLICK_DELAY_TIME = 60000;
        private static long lastClickTime;

        public static boolean isFastClick() {
            boolean flag = false;
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                flag = true;
            }
            lastClickTime = curClickTime;
            return flag;
        }
    }
}
