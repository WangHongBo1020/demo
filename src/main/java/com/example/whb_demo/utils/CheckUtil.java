package com.example.whb_demo.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 * author kangyanqing
 * create by 20180910
 */
public class CheckUtil {

    public static final String reg = "^[([一-龥])([a-zA-Z])([0-9])(\\s)([><=*!_'?:;.\"(),-/%\\[\\]{}$·！@#¥&（）【】|、，。？：；\\“\\”《》])(\\+)]*$";

    /**
     * 15位数字正则校验
     */
    private static final String regexFor15Num = "\\d{15}";

    /**
     * 17位数字 + X 正则校验
     */
    private static final String regexFor17NumAndX = "\\d{17}X";

    /**
     * 18位数字正则校验
     */
    private static final String regexFor18Num = "\\d{18}";

    /**
     * VIN码正则校验
     */
    private static final String regexForVIN = "[A-HJ-NPR-Z\\d]{17}";

    /**
     * VIN码校验码
     */
    private static Map<Character, Integer> vinCheckCode = new HashMap<Character, Integer>();

    /**
     * VIN码权重值
     */
    private static Map<Integer, Integer> vinMapWeighting = new HashMap<Integer, Integer>();

    static {
        vinMapWeighting.put(1, 8);
        vinMapWeighting.put(2, 7);
        vinMapWeighting.put(3, 6);
        vinMapWeighting.put(4, 5);
        vinMapWeighting.put(5, 4);
        vinMapWeighting.put(6, 3);
        vinMapWeighting.put(7, 2);
        vinMapWeighting.put(8, 10);
        vinMapWeighting.put(9, 0);
        vinMapWeighting.put(10, 9);
        vinMapWeighting.put(11, 8);
        vinMapWeighting.put(12, 7);
        vinMapWeighting.put(13, 6);
        vinMapWeighting.put(14, 5);
        vinMapWeighting.put(15, 4);
        vinMapWeighting.put(16, 3);
        vinMapWeighting.put(17, 2);

        vinCheckCode.put('0', 0);
        vinCheckCode.put('1', 1);
        vinCheckCode.put('2', 2);
        vinCheckCode.put('3', 3);
        vinCheckCode.put('4', 4);
        vinCheckCode.put('5', 5);
        vinCheckCode.put('6', 6);
        vinCheckCode.put('7', 7);
        vinCheckCode.put('8', 8);
        vinCheckCode.put('9', 9);
        vinCheckCode.put('A', 1);
        vinCheckCode.put('B', 2);
        vinCheckCode.put('C', 3);
        vinCheckCode.put('D', 4);
        vinCheckCode.put('E', 5);
        vinCheckCode.put('F', 6);
        vinCheckCode.put('G', 7);
        vinCheckCode.put('H', 8);
        vinCheckCode.put('J', 1);
        vinCheckCode.put('K', 2);
        vinCheckCode.put('M', 4);
        vinCheckCode.put('L', 3);
        vinCheckCode.put('N', 5);
        vinCheckCode.put('P', 7);
        vinCheckCode.put('R', 9);
        vinCheckCode.put('S', 2);
        vinCheckCode.put('T', 3);
        vinCheckCode.put('U', 4);
        vinCheckCode.put('V', 5);
        vinCheckCode.put('W', 6);
        vinCheckCode.put('X', 7);
        vinCheckCode.put('Y', 8);
        vinCheckCode.put('Z', 9);
    }

    /**
     * 校验字符串是否含有特殊字符
     * @param str 校验字符串
     * @author KangYanQing
     * @date 下午7:50 2018/9/10
     */
    public static boolean validSpecialStr(String str){
        if(str == null){
            return true;
        }
        return str.matches(reg);
    }

    /**
     * 身份证号校验(中保信平台)
     *
     * @param idNumber 需要校验的身份证号
     * @return true:校验通过,false:不通过
     */
   /* public static boolean idNumberCheck(String idNumber) {
        //不为空不为null
        if (StringUtils.isNullOrEmpty(idNumber)) {
            return false;
        }

        //1)长度必须为15位或者18位。
        int length = idNumber.length();
        if (length != 15 && length != 18) {
            return false;
        }

        //2)当长度为15位时，仅允许上传数字。
        if (length == 15) {
            return idNumber.matches(regexFor15Num);
        }

        //3)当长度为18位时，前17位仅允许上传数字，第18位允许数字或“X”
        if (!idNumber.matches(regexFor17NumAndX) && !idNumber.matches(regexFor18Num)) {
            return false;
        }

        //a)对前17位数字本体码加权求和
        //      公式为：S = Sum(Ai * Wi), i = 0, ... , 16
        //      其中Ai表示第i位置上的身份证号码数字值，Wi表示第i位置上的加权因子，
        //      其各位对应的值依次为：
        //          位数 1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17
        //          权	 7	9	10	5	8	4	2	1	6	3	7	9	10	5	8	4	2
        int s = 0;
        String substring = idNumber.substring(0, 17);
        for (int i = 0; i < substring.length(); i++) {
            s += (Integer.parseInt(substring.substring(i, i + 1)) * getIdNumberQuan(i + 1));
        }

        //b)以11对计算结果取模 Y = mod(S, 11)
        int y = s % 11;
        //c)根据模的值得到对应的校验码，对应关系为：
        //      Y   值	0	1	2	3	4	5	6	7	8	9	10
        //      校验码 	1	0	X	9	8	7	6	5	4	3	2
        String checkCode = getIdNumberCheckCode(y);
        //判断身份证号最后一位和校验码是否一致
        return idNumber.substring(17).equals(checkCode);
    }*/

    private static int getIdNumberQuan(int weiShu) {
        if (weiShu < 1 || weiShu > 17) {
            return 0;
        }

        Integer[] arr = new Integer[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        return arr[weiShu - 1];
    }

    private static String getIdNumberCheckCode(int y) {
        String[] arr = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        return arr[y];
    }

    /**
     * VIN码校验(中保信平台)
     *
     * @param vin 需要校验的VIN码
     * @return true:校验通过,false:不通过
     */
    public static boolean vinCheck(String vin) {

        if (StringUtils.isEmpty(vin)) {
            return false;
        }

        String upperVIN = vin.toUpperCase();
        //排除字母 I、O、Q
        if (upperVIN.contains("I") || upperVIN.contains("O") || upperVIN.contains("Q")) {
            return false;
        }

        if (vin.length() != 17) {
            return false;
        }

        //校验是否是纯数字
        Pattern pattern = Pattern.compile("[0-9]*");
        if(pattern.matcher(vin).matches()){
            return false;
        }

        if (!upperVIN.matches(regexForVIN)) {
            return false;
        }

        return true;

//        char[] vinArr = upperVIN.toCharArray();
//
//        HashSet<String> set = new HashSet<String>();
//        for (char c : vinArr) {
//            set.add(String.valueOf(c));
//        }
//        if (set.size() == 1) {
//            return false;
//        }
//
//        int amount = 0;
//        for (int i = 0; i < vinArr.length; i++) {
//            //VIN码从从第一位开始，码数字的对应值×该位的加权值，计算全部17位的乘积值相加
//            amount += vinCheckCode.get(vinArr[i]) * vinMapWeighting.get(i + 1);
//        }
//        //乘积值相加除以11、若余数为10，即为字母Ｘ
//        if (amount % 11 == 10) {
//            return vinArr[8] == 'X';
//        }
//
//        //VIN码从从第一位开始，码数字的对应值×该位的加权值，//计算全部17位的乘积值相加除以11，所得的余数，即为第九位校验值
//        return amount % 11 == vinCheckCode.get(vinArr[8]);
    }

}
