package com.agoodwater.system.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shiqiang on 2017/4/19.
 */

public class CheckPhone {


    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern= Pattern.compile("^1[0-9]{10}$");
        Matcher matcher=pattern.matcher(phoneNumber);
        return matcher.matches();

    }
}
