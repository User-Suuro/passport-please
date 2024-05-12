package game.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static boolean isDateLarger(String dateString1, String dateString2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy"); // Adjust the format as needed
        LocalDate date1 = LocalDate.parse(dateString1, formatter);
        LocalDate date2 = LocalDate.parse(dateString2, formatter);
        return date1.isAfter(date2);
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return date.format(formatter);
    }
}
