package org.zxl.testdemo.jdk8test.dateApi;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Lambda9 {
    /**
     * Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数。某一个特定的时间点也可以使用Instant类来表示，Instant类也可以用来创建老的java.util.Date对象。
     *
     */
    @Test
    public void clockTest(){
        Clock clock = Clock.systemDefaultZone();
        System.out.println("clock.millis():"+clock.millis());

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }

    /** Timezones 时区
     * 在新API中时区使用ZoneId来表示。时区可以很方便的使用静态方法of来获取到。
     * 时区定义了到UTS时间的时间差，
     * 在Instant时间点对象到本地日期对象之间转换的时候是极其重要的。
     */
    @Test
    public void TimezonesTest(){
        System.out.println(ZoneId.getAvailableZoneIds());

        System.out.println(ZoneId.systemDefault().getId());
        System.out.println(ZoneId.systemDefault().getRules());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }

    /**
     * LocalTime 定义了一个没有时区信息的时间，例如 晚上10点，或者 17:30:15。
     * 下面的例子使用前面代码创建的时区创建了两个本地时间。之后比较时间并以小时
     * 和分钟为单位计算两个时间的时间差：
     */
    @Test
    public void LocalTimeTest(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239


        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37
    }

    /**
     * LocalDate 本地日期
     */
    @Test
    public void LocalDateTest(){
        //LocalDate 表示了一个确切的日期，比如 2014-03-11。该对象值是不可变的，用起来和LocalTime基本一致。下面的例子展示了如何给Date对象加减天/月/年。另外要注意的是这些对象是不可变的，操作返回的总是一个新实例。

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();

        System.out.println(dayOfWeek);    // FRIDAY

        //从字符串解析一个LocalDate类型和解析LocalTime一样简单：
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24
    }

    /**
     * LocalDateTime 本地日期时间
     */
    @Test
    public void LocalDateTimeTest(){
        //LocalDateTime 同时表示了时间和日期，相当于前两节内容合并到一个对象上了。
        // LocalDateTime和LocalTime还有LocalDate一样，都是不可变的。
        // LocalDateTime提供了一些能访问具体字段的方法。

        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

        //只要附加上时区信息，就可以将其转换为一个时间点Instant对象，
        // Instant时间点对象可以很容易的转换为老式的java.util.Date。
        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

        //格式化LocalDateTime和格式化时间和日期一样的，
        // 除了使用预定义好的格式外，我们也可以自己定义格式：
        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13
    }


}
