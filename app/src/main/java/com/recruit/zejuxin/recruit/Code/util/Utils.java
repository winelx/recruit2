package com.recruit.zejuxin.recruit.Code.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 10942 on 2017/10/10 0010.
 */

public class Utils {

    //�验证手机号是否正确ֻ
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(1[3-8][0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //�验证密码
    public static boolean isRightPwd(String pwd) {
        Pattern p = Pattern.compile(".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
        Matcher m = p.matcher(pwd);
        return m.matches();
    }
}
