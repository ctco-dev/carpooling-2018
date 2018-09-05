package lv.ctco.javaschool.app.control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeCoverter {
    private static final DateTimeFormatter DATE_FOR_DTO = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FOR_DTO = DateTimeFormatter.ofPattern("kk:mm");

    public static LocalDateTime covertToDateTime(String date, String time) {
        LocalDate parsedDate = LocalDate.parse(date, DATE_FOR_DTO);
        LocalTime parseTime = LocalTime.parse(time, TIME_FOR_DTO);
        return LocalDateTime.of(parsedDate, parseTime);
    }

    public static String covertToDate(LocalDateTime dateTime) {
        return dateTime.format(DATE_FOR_DTO);
    }

    public static String covertToTime(LocalDateTime dateTime) {
        return dateTime.format(TIME_FOR_DTO);
    }
}
