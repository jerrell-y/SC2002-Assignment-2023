package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    // Convert YYYY-MM-DD string to a Date
    public static Date stringToDate(String dateString) {
        try {
            return FORMATTER.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error parsing date string: " + e.getMessage());
            return null;
        }
    }

    // Convert Date to a YYYY-MM-DD string
    public static String dateToString(Date date) {
        return FORMATTER.format(date);
    }
    
}
