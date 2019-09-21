package com.space.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    /**
     * Convert day to 01 and month to 01, year will stay the same as given
     * @param date
     * @return
     */
    public static Date yearConvert(Date date){
        Date date0101=null;
        int year;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            Calendar newCal = Calendar.getInstance(/*TimeZone.getTimeZone("UTC")*/);
            newCal.set(year,Calendar.JANUARY,01,00,00,00);

            date0101 = newCal.getTime();

            //Europe/Kiev

        }
        return date0101;
    }

}
