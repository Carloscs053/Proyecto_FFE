package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utils {

    public static String formateaHora(LocalDateTime ldt) {
        String segundos = "";

        if (LocalDateTime.now().getSecond() < 10) segundos = "0" + LocalDateTime.now().getSecond();
        else segundos = String.valueOf(LocalDateTime.now().getSecond());

        return LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" +
                segundos;
    }
}
