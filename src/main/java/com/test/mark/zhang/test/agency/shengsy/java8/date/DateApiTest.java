package com.test.mark.zhang.test.agency.shengsy.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author by mark
 * @Classname DateApiTest
 * @Description TODO
 * @Date 2021/12/14 9:27 上午
 */
public class DateApiTest {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        MonthDay monthDay = MonthDay.of(now.getMonth(), now.getDayOfMonth());

        MonthDay from = MonthDay.from(LocalDate.of(2022, 12, 14));
        //YearMonth 只关心年和月
        if (from.equals(monthDay)) {
            // 这里只会比较月和日,不会比较年,特别适合用于生日等不关心具体年份的场合
            System.out.println("equals");
        } else {
            System.out.println("not equals");
        }

        System.out.println(now.minusDays(6));
        LocalDateTime startTime = now.plusDays(-6).minusWeeks(2).atStartOfDay();
        System.out.println(startTime);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(startTime));

        LocalDate last2Hours = now.plus(2, ChronoUnit.HOURS);
    }
}
