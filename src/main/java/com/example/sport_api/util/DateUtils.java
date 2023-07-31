package com.example.sport_api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
