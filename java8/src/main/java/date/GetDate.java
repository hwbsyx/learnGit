package date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class GetDate {
    //获取当前月第一天和最后一天
    @Test
    public void getDate() {
        LocalDate today = LocalDate.now();
        //本月第一天
        LocalDate first = LocalDate.of(today.getYear(), today.getMonth(), 1);
        //本月最后一天
        LocalDate last = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月第一天为" + first);
        System.out.println("本月最后一天天为" + last);

    }

    @Test
    public void oldDateFormatter() {
        //jdk8以前修改日期格式
        SimpleDateFormat oldDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(oldDateFormatter.format(date));

        DateTimeFormatter newDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(newDateFormatter.format(localDate));
    }

    @Test
    public void yesterdayCurrent() {
        //获取昨天的当前时刻
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        System.out.println(calendar.getTime());

        //jdk8以后
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday = localDate.minusDays(1);
        System.out.println(yesterday);
    }
}
