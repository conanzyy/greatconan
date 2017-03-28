package com.greatconan.conan.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 验证<br>
 * 常用的正则表达式验证
 *
 * @author 17021690
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ValidateUtils {

    /**
     * 
     * 通用验证: <br>
     * 〈功能详细描述〉
     *
     * @param regex 正则表达式
     * @param 要验证的字符串
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean validate(String regex, String source) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(source);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 
     * 功能描述: <br>
     * 验证邮箱
     *
     * @param email
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean validateEmail(String email) {
        return validate("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email);
    }

    /**
     * 
     * 功能描述: <br>
     * 验证手机号码
     *
     * @param phone
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean validatePhone(String phone) {
        return validate("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", phone);
    }

    /**
     * 
     * 功能描述: <br>
     * 非零的正整数
     *
     * @param num
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean validateNum(String num) {
        return validate("^[1-9]\\d*$", num);
    }

    /**
     * 
     * 功能描述: <br>
     * 包括0的正整数
     *
     * @param num
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean validateNumAndZero(String num) {
        return validate("^[0-9]\\d*$", num);
    }
    
    /**
     * 
     * 功能描述: <br>
     * 验证指定长度的纯数字
     *
     * @param num
     * @param len
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean validateNumLength(String num,int length) {
        String regex=String.format("^[0-9]{%d}$", length);
        return validate(regex, num);
    }
    
     

}
