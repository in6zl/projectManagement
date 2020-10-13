package com.nuist.projectManagement.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author zmj
 * @description
 * @date 2020/4/9 0009
 */
@Component
@Slf4j
public class DateMapperUtil {

    String pattern = "yyyy-MM-dd HH:mm:ss";
    String pattern2 = "yyyy-MM-dd HH:mm";
    String pattern3 = "yyyy-MM-dd";


    public String localDateTimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    public LocalDateTime stringToLocalDateTime(String date) {
       if (StringUtils.isBlank(date)) {
           return null;
       }
        try {
            return LocalDateTime.parse(date,DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(date,DateTimeFormatter.ofPattern(pattern2));
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public String localDateToString(LocalDate localDate){
        return DateTimeFormatter.ofPattern(pattern3).format(localDate);
    }

    public LocalDate stringToLocalDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return LocalDate.parse(date,DateTimeFormatter.ofPattern(pattern3));
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
