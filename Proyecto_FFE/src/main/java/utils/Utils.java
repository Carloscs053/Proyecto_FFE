package utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String formateaHora(LocalDateTime ldt) {
        String horas = "", minutos = "", segundos = "";

        if (LocalDateTime.now().getHour() < 10) horas = "0" + LocalDateTime.now().getHour();
        else horas = String.valueOf(LocalDateTime.now().getHour());

        if (LocalDateTime.now().getMinute() < 10) minutos = "0" + LocalDateTime.now().getMinute();
        else minutos = String.valueOf(LocalDateTime.now().getMinute());

        if (LocalDateTime.now().getSecond() < 10) segundos = "0" + LocalDateTime.now().getSecond();
        else segundos = String.valueOf(LocalDateTime.now().getSecond());

        return horas + ":" + minutos + ":" + segundos;
    }

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
}
