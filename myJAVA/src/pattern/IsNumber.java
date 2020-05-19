package pattern;

import java.util.regex.Pattern;

public class IsNumber {
    //待检测的字符串
    public static void main(String[] args) {

        String str = new String("1211291.22");
        String value = str.substring(1, 1);
        System.out.println(value);
        //包含小数的纯数字
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        //整数纯数字
//        Pattern pattern = Pattern.compile("^-?[0-9]+$");
        boolean flag = pattern.matcher(str).matches();
        System.out.println((flag ? "是" : "不是" )+ "纯数字");
    }
}
