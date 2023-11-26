package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Used to convert the date entered as a string into a Date object.
*/
public class DateHelper {
    
    /**
     * The format of the date object (YYYY-MM-DD).
     */
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Converts a String of format YYYY-MM-DD to a Date object.
     * @param dateString The YYYY-MM-DD string.
     * @return the date object.
     */
    public static Date stringToDate(String dateString) {
        try {
            return FORMATTER.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error parsing date string: " + e.getMessage());
            return null;
        }
    }

    
    /**
     * Converts a Date object into a YYYY-MM-DD string.
     * @param date The date object.
     * @return the date in YYYY-MM-DD format.
     */
    public static String dateToString(Date date) {
        return FORMATTER.format(date);
    }
    
}
