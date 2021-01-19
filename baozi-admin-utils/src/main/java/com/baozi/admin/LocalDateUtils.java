package com.baozi.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName LocalDateUtils
 * @Description 对LocalDate & LocalDateTime的一些封装
 * @Author LiZuoYang
 * @Date 2020/12/1 14:16
 **/
public class LocalDateUtils {

    public static String dateToStr(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(dateTime);
    }

    public static String dateToStr(LocalDate localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return df.format(localDate);
    }
}
