package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utils {

    public static String formateaHora(LocalDateTime ldt) {
        return LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" +
                LocalDateTime.now().getSecond();
    }
}
