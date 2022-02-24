package com.example.whb_demo.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zpw
 * @date 2021/10/24
 * @Description: 字符处理工具类
 */
public class StringUtils extends org.springframework.util.StringUtils {
    /**
     * 验证字符串是否包含特殊字符正则表达式
     */
    private static final Pattern VALIDATE_SPECIAL_CHARACTERS_RE = Pattern.compile("^[()0-9A-Za-z\u4e00-\u9fa5]+$");
    private static final Pattern VALIDATE_PASSWORsD = Pattern.compile("^{8,16}$");
    private static final Pattern NUMBER_LETTER = Pattern.compile("^[A-Za-z0-9]+$");
    /**
     * 预编译正则，提高效率
     */
    private static final Pattern PATTERN = Pattern.compile("^1[0-9]{10}$");

    /**
     * 验证是由中文、英文、数字组成
     *
     * @param str
     * @return
     */
    public static boolean isSpecialCharacters(String str) {
        //检查是否为空
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str.trim())) {
            return false;
        }
        return VALIDATE_SPECIAL_CHARACTERS_RE.matcher(str).find();
    }


    /**
     * 验证手机号是否正确
     *
     * @param mobile
     * @return true是，false否
     */
    public static boolean isMobile(String mobile) {
        if (!StringUtils.isEmpty(mobile) || !StringUtils.isEmpty(mobile.trim())) {
            return Pattern.matches("^[1][0-9]{10}$", mobile);
        }
        return false;
    }

    public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        Matcher m = PATTERN.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 验证邮箱号是否正确
     *
     * @param email
     * @return true是，false否
     */
    public static boolean isEmail(String email) {
        if (!StringUtils.isEmpty(email) || !StringUtils.isEmpty(email.trim())) {
            return Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$", email);
        }
        return false;
    }

    /**
     * 判断字符串是否都是数字
     *
     * @param str 验证的字符串
     * @return true是，false否
     */
    public static boolean isNumber(String str) {
        if (!StringUtils.isEmpty(str) || !StringUtils.isEmpty(str.trim())) {
            return Pattern.matches("^\\d+$", str);
        }
        return false;
    }

    /**
     * 判断只能有数字或字母
     *
     * @param str 验证的字符串
     * @return true是，false否
     */
    public static boolean isCode(String str) {
        if (!StringUtils.isEmpty(str) || !StringUtils.isEmpty(str.trim())) {
            return NUMBER_LETTER.matcher(str).find();
        }
        return false;
    }


    /**
     * 验证密码
     *
     * @param str
     * @return
     */
    public static boolean validatePassword(String str) {
        //检查是否为空
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str.trim())) {
            return false;
        }
        return VALIDATE_PASSWORsD.matcher(str).find();
    }


    /**
     * 生成指定长度的随机字符串
     *
     * @param strLength
     * @return
     * @author liuqinggang
     */
    public static String getRandomString(int strLength) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < strLength; i++) {
            int charInt;
            char c;
            if (random.nextBoolean()) {
                charInt = 48 + random.nextInt(10);
                c = (char) charInt;
                buffer.append(c);
                continue;
            }
            charInt = 65;
            if (random.nextBoolean()) {
                charInt = 65 + random.nextInt(26);

            } else {
                charInt = 97 + random.nextInt(26);

            }
            if (charInt == 79) {
                charInt = 111;
            }
            c = (char) charInt;
            buffer.append(c);
        }

        return buffer.toString();
    }

}
