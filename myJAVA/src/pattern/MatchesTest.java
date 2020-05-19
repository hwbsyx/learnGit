package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchesTest {
    public static void main(String[] args) {
        String[] mails = {"jsadjs@aas163.com","jusuwnw@jkwje.qq.com","hwbstx@jwehj126.com","hshees@shcher.org","wawa@abc.xx"};
        var mailRegEx = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
        var pattern = Pattern.compile(mailRegEx);
        Matcher m = null;
        for (var mail : mails){
            if (m == null){

                m = pattern.matcher(mail);
            }else{
                m.reset(mail);
            }
            String result = mail + (m.matches() ? "是":"不是") + "一个有效的邮箱地址";
            System.out.println(result);
        }
    }
}
