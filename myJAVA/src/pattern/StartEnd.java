package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartEnd {
    public static void main(String[] args) {
        var regStr = "Java is very easy!";
        Matcher m = Pattern.compile("\\w+").matcher(regStr);
        while (m.find()){
            System.out.println(m.group()+"子串的起始位置是："+m.start()+"结束位置是:"+m.end());
        }
    }
}
