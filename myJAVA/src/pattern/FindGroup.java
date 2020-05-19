package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindGroup {
    public static void main(String[] args) {
//        从字符串中取出电话号码
        var str = "我想求购一本《疯狂Java讲义》，联系135000006666"+"交朋友，电话号码13611125565"+"出售二手电脑，电话158999003312";
//        创建一个pattern对象并用它简历一个matcher对象
        Matcher m = Pattern.compile("((13\\d)|(15\\d))\\d{8}").matcher(str);
        while (m.find()){
            System.out.println(m.group());
        }
    }
}
