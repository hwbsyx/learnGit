package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceTest {
    public static void main(String[] args) {
        String[] msgs = {"Java has reular expressions in 1.4","regular expressions now expressing in Java","Java represses oracular expressions"};
        var p = Pattern.compile("re\\w*");
        Matcher m = null;
        for (var msg : msgs){
            m = p.matcher(msg);


            System.out.println(m.replaceAll("哈哈"));
        }

    }
}
