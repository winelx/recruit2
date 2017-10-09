package com.recruit.zejuxin.recruit.Code.util;

/**
 * Created by 10942 on 2017/10/9 0009.
 */

public class Request {
    private static final String networks = "http://192.168.2.151:8080/huajiayi/";
    //用户登陆
    public static final String userLogin = networks + "userLogin/plogin.do";
    //获取验证码
    public static final String VERIFY = networks + "user/ipone.do";
}
