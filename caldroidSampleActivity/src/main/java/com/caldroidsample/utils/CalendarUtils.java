package com.caldroidsample.utils;

import android.content.Context;

import com.caldroidsample.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUtils {

    public static Date firstDateInList(List<Date> list) {
        Date lastDate = null;

        Calendar date = Calendar.getInstance();

        for (Date dt : list) {
            date.setTime(dt);

            if (lastDate == null) {
                lastDate = date.getTime();
                continue;
            } else {

                Calendar lastCal = Calendar.getInstance();
                lastCal.setTime(lastDate);

                if (date.before(lastCal)) {
                    lastDate = date.getTime();
                }
            }

        }
        return lastDate;
    }

    public static Calendar transformStringToCalendar(String data, String pattern) {
        Calendar calendarFromString = Calendar.getInstance();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            calendarFromString.setTime(simpleDateFormat.parse(data));

        } catch (ParseException e) {

        }
        return calendarFromString;
    }

    public static String getMonthOfDate(Calendar cal, Context context) {


        switch (cal.get(Calendar.MONTH) + 1) {
            case 1:
                return context.getString(R.string.janShort);
            case 2:
                return context.getString(R.string.febShort);
            case 3:
                return context.getString(R.string.marShort);
            case 4:
                return context.getString(R.string.aprShort);
            case 5:
                return context.getString(R.string.mayShort);
            case 6:
                return context.getString(R.string.junShort);
            case 7:
                return context.getString(R.string.julShort);
            case 8:
                return context.getString(R.string.augShort);
            case 9:
                return context.getString(R.string.sepShort);
            case 10:
                return context.getString(R.string.octShort);
            case 11:
                return context.getString(R.string.novShort);
            case 12:
                return context.getString(R.string.decShort);
            default:
                return "";
        }


    }

    public static void clear(Calendar calendar) {
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
    }

    public static String getDayAndMonthOfDateFullInverse(String date, Context context) {

        String dt = "";

        if (date != null && !"null".equals(date)) {
            String[] arrayMes = date.split("-");
            dt = arrayMes[2].toString() + "/" + arrayMes[1].toString();
        }
        return dt;
    }

    public static String getMonthOfDateFull(String date, Context context) {

        String[] arrayMes = date.split("-");

        if (arrayMes != null && arrayMes.length > 1) {
            switch (Integer.parseInt(arrayMes[1].toString())) {
                case 1:
                    return context.getString(R.string.jan);
                case 2:
                    return context.getString(R.string.feb);
                case 3:
                    return context.getString(R.string.mar);
                case 4:
                    return context.getString(R.string.apr);
                case 5:
                    return context.getString(R.string.may);
                case 6:
                    return context.getString(R.string.jun);
                case 7:
                    return context.getString(R.string.jul);
                case 8:
                    return context.getString(R.string.aug);
                case 9:
                    return context.getString(R.string.sep);
                case 10:
                    return context.getString(R.string.oct);
                case 11:
                    return context.getString(R.string.nov);
                case 12:
                    return context.getString(R.string.dec);
                default:
                    return "";
            }
        }
        return "";
    }
}
