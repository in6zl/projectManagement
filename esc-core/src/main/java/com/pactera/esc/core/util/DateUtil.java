package com.pactera.esc.core.util;

import com.pactera.esc.core.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class DateUtil {
    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final  String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_DATA_INT = "yyyyMMdd";
    public static final String PATTERN_TIMESTAMP = "yyyyMMddHHmmss";
    private static final String[] patterns = {"yyyy/MM/dd","yyyy/M/d","yyyy/M/dd","yyyy/MM/d","yyyy-MM-dd","yyyy-MM-d","yyyy-M-dd","yyyy-M-d"};

    public static Date formatDateString(String format){
        for (String pattern : patterns) {
            try {
                LocalDate parse = LocalDate.parse(format, DateTimeFormatter.ofPattern(pattern));
                return Date.from(parse.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            } catch (Exception ignore) {}
        }
        throw new BaseException("-1","日期转换异常,传参为:"+format);
    }
    /**
     * 当前时间
     *
     * @param format
     * @return
     */
    public static String now(String format) {
        return DateTimeFormatter.ofPattern(format).format(LocalDateTime.now());
    }

    /**
     * date转string
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        // Instant时刻，互转LocalDateTime需要时区
        return DateTimeFormatter.ofPattern(format)
            .format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    /**
     * string转date
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
        LocalDate date = LocalDate.parse(str, DateTimeFormatter.ofPattern(format));
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * string转dateTime
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2DateTime(String str, String format) {
        LocalDateTime date = LocalDateTime.parse(str, DateTimeFormatter.ofPattern(format));
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime UDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    public static void addTimePredicate(Root<?> root, CriteriaBuilder cb, List<Predicate> list, Date requireTime,String param) {
        Calendar c = Calendar.getInstance();
        c.setTime(requireTime);
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.SECOND, -1);
        Predicate predicate = cb.between(root.get(param), UDateToLocalDateTime(requireTime), UDateToLocalDateTime(c.getTime()));
        list.add(predicate);
    }

    public static String dateInt() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_DATA_INT));
    }

    public static String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_TIMESTAMP));
    }


    public static void main(String[] args) {
        System.out.println(dateInt());
    }
}
