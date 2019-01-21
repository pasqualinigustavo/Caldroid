package com.caldroidsample.utils;

/**
 * Created by pasqualini on 27/01/15.
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateFunctions {

    public DateFunctions() {

    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date addMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    public static Date addYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    public String getStringDate(int dia, int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes - 1);
        Date date = c.getTime();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String reportDate = df.format(date);

        return reportDate;
    }

    public static String getStringDate(Date data) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(data);
    }

    public static Date getDateByString(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = format.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateByString(String dateString, String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateByString(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    public String getIntDate(int dia, int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes - 1);
        Date date = c.getTime();

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String reportDate = df.format(date);

        return reportDate;
    }

    public Date getDate(int dia, int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes - 1);
        return c.getTime();
    }

    public long getDateInMillis(String dateStart, String hour) {
        String[] hours = hour.split(":");

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dateStart);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.HOUR, Integer.parseInt(hours[0]));
            cal.set(Calendar.MINUTE, Integer.parseInt(hours[1]));
            return cal.getTime().getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private long getDaysBetween(Date actualDate, Date updateDate) {
        long diff = actualDate.getTime() - updateDate.getTime();
        if (diff > 0) {
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (60 * 60 * 1000 * 24);
            System.out.println("Time in seconds: " + diffSeconds + " seconds.");
            System.out.println("Time in minutes: " + diffMinutes + " minutes.");
            System.out.println("Time in hours: " + diffHours + " hours.");
            System.out.println("Time in hours: " + diffDays + " days.");
            return diffDays;
        }
        return 0;
    }

    public static String timeRemaining(long time) {
        if (time > 0) {
            long diffSeconds = time;
            long diffMinutes = time / (60);
            long diffHours = time / (60 * 60);
            long diffDays = time / (60 * 60 * 24);

            if (diffHours > 0)
                return String.format("%02d h %02d min %02d s", diffHours, diffMinutes, diffSeconds);
            else if (diffMinutes > 0)
                return String.format("%02d min", diffMinutes);
            else
                return String.format("%02d s", diffSeconds);
        }
        return "0 min";
    }

    static public boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    static public String getFormattedDate(String strDate) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(strDate);
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
            return fmtOut.format(date);
        } catch (ParseException e) {
            return strDate;
        }
    }

    public static Date getStartOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        return new Date(cal.getTimeInMillis());
    }

    public static Date getEndOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1); //last day of month
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return new Date(cal.getTimeInMillis());
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static String convertDecimalToHour(double time) {
        int hours = BigDecimal.valueOf((time * 24.0))
                .setScale(1, RoundingMode.HALF_UP)
                .intValue();

        hours = (hours == 24 ? 23 : hours);
        int minutes = BigDecimal.valueOf(((time * 24) % 1) * 60)
                .setScale(1, RoundingMode.HALF_UP)
                .intValue() == 60 ? 0 : BigDecimal.valueOf(((time * 24) % 1) * 60)
                .setScale(1, RoundingMode.HALF_UP)
                .intValue();
        return String.format("%02d:%02d", hours, minutes);
    }

    public static double convertHourToDecimal(String time) {
        double c1 = 0.041666667; //1.0/24.0
        double c2 = 0.000694444; //1.0/24.0/60.0

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date = sdf.parse(time);
            calendar.setTime(date);
            double H = calendar.get(Calendar.HOUR_OF_DAY);
            double M = calendar.get(Calendar.MINUTE);
            double h = (double) (c1 * H);
            double m = (double) (c2 * M);
            return h + m;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getHourOfDay(String time) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date = sdf.parse(time);
            calendar.setTime(date);
            return calendar.get(Calendar.HOUR_OF_DAY);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getMinuteOfDay(String time) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date = sdf.parse(time);
            calendar.setTime(date);
            return calendar.get(Calendar.MINUTE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Date getStartOfDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        return new Date(cal.getTimeInMillis());
    }

    public static String getUTCDateAsString() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        return utcTime;
    }
}