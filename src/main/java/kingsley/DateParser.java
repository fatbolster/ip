package kingsley;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static LocalDateTime processDateAndTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    public static String processDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return dateTime.format(formatter);
    }

    public static String processDateTimeToStorageString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return dateTime.format(formatter);
    }


}
