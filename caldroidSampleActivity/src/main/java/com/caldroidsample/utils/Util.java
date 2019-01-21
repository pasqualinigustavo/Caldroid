package com.caldroidsample.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.text.InputType;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.caldroidsample.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Util {

    public static final String CLOSE = "CloseUnNecessaryActivities";

    public static final String TAG = "Voopter";

    public static final String KEY_ANALYTICS = "UA-54758479-1";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1100;


    public static boolean isInDebugMode = true; // habilita o log
    public static ArrayList<Class<?>> repaintItems = new ArrayList<Class<?>>();

    /*
     * Classe usada para efetuar o log na aplica��o
     */
    public static class Log {

        public static void e(String log) {
            if (isInDebugMode)
                splitAndLog_e(TAG, log);
        }

        public static void i(String log) {
            if (isInDebugMode)
                splitAndLog_i(TAG, log);
        }

        public static void i(String tag, String log) {
            if (isInDebugMode)
                splitAndLog_i(tag, log);
        }

        public static void d(String log) {
            if (isInDebugMode)
                splitAndLog_d(TAG, log);
        }
    }

    public static final String KEY_RESULT = "Result";

    /**
     * Divides a string into chunks of a given character size.
     *
     * @param text      String text to be sliced
     * @param sliceSize int Number of characters
     * @return ArrayList<String>   Chunks of strings
     */
    public static ArrayList<String> splitString(String text, int sliceSize) {
        ArrayList<String> textList = new ArrayList<String>();
        String aux;
        int left = -1, right = 0;
        int charsLeft = text.length();
        while (charsLeft != 0) {
            left = right;
            if (charsLeft >= sliceSize) {
                right += sliceSize;
                charsLeft -= sliceSize;
            } else {
                right = text.length();
                aux = text.substring(left, right);
                charsLeft = 0;
            }
            aux = text.substring(left, right);
            textList.add(aux);
        }
        return textList;
    }

    /**
     * Divides a string into chunks.
     *
     * @param text String text to be sliced
     * @return ArrayList<String>
     */
    public static ArrayList<String> splitString(String text) {
        return splitString(text, 600);
    }

    /**
     * Divides the string into chunks for displaying them
     * into the Eclipse's LogCat.
     *
     * @param text The text to be split and shown in LogCat
     * @param tag  The tag in which it will be shown.
     */
    public static void splitAndLog_i(String tag, String text) {
        ArrayList<String> messageList = splitString(text);
        for (String message : messageList) {
            android.util.Log.i(tag, message);
        }
    }


    /**
     * Divides the string into chunks for displaying them
     * into the Eclipse's LogCat.
     *
     * @param text The text to be split and shown in LogCat
     * @param tag  The tag in which it will be shown.
     */
    public static void splitAndLog_e(String tag, String text) {
        ArrayList<String> messageList = splitString(text);
        for (String message : messageList) {
            android.util.Log.e(tag, message);
        }
    }


    /**
     * Divides the string into chunks for displaying them
     * into the Eclipse's LogCat.
     *
     * @param text The text to be split and shown in LogCat
     * @param tag  The tag in which it will be shown.
     */
    public static void splitAndLog_d(String tag, String text) {
        ArrayList<String> messageList = splitString(text);
        for (String message : messageList) {
            android.util.Log.d(tag, message);
        }
    }

    public static String getStringFromArrayListString(ArrayList<String[]> list) {

        String str = "";
        for (String[] arr : list) {
            for (String s : arr) {
                str += s + "_";
            }
            str += "-";
        }

        return str;
    }


    public static String getDateTimeByPattern(String pattern, Date date) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
        return dateFormat.format(date);
    }

    /**
     * Return a Date object from a string format dd/MM/yyyy
     *
     * @param date
     * @return
     */
    public static Date getDateByString(String date) {

        Date lastConsult = null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        try {
            lastConsult = formatter.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return lastConsult;
    }

    /**
     * Retorna a data no padrao xx/xx/xxxx
     *
     * @param date
     * @return
     */
    public static String getFormatedDate(Date date) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return dateFormat.format(date);
    }

    /**
     * Retorna a data no padrao xx
     *
     * @param date
     * @return
     */
    public static String getFormatedDay(Date date) {
        if (date != null) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd", new Locale("pt", "BR"));
            if (dateFormat != null)
                return dateFormat.format(date);
            else
                return "";
        } else {
            return "";
        }
    }

    /**
     * Retorna a data no padrao xx
     *
     * @param date
     * @return
     */
    public static String getFormatedMonth(Date date) {
        if (date != null) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM", new Locale("pt", "BR"));
            if (dateFormat != null)
                return dateFormat.format(date);
            else
                return "";
        } else {
            return "";
        }
    }

    /**
     * Retorna um objeto Data a partir de uma String e padrão
     *
     * @return
     */
    public static Date getDateFromPattern(String str, String pattern) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            date = format.parse(str);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Retorna um objeto Data a partir de uma String no padrao dd-mm-yyyy
     *
     * @return
     */
    public static String getDateFromFacebook(String str) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = format.parse(str);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dt = "";

        if (date != null) dt = getFormatedDate(date);

        return dt;
    }

    /**
     * Retorna a data no padrao yyyy-mm-yy
     *
     * @param date
     * @return
     */
    public static String getFormatedSQLDateByString(String date) {

        String newDate = "";

        if (date != null) {
            String[] arr = date.split("/");

            if (arr != null && arr.length >= 2) {
                newDate = arr[2] + "-" + arr[1] + "-" + arr[0];
            }

        }
        return newDate;
    }

    /**
     * Retorna a data no padrao dd/mm/yyyy
     *
     * @param date yyyy-mm-dd
     * @return
     */
    public static String getFormatedBrDateByString(String date) {

        String newDate = "";

        if (date != null) {
            String[] arr = date.split("-");

            if (arr != null && arr.length >= 2) {
                newDate = arr[2] + "/" + arr[1] + "/" + arr[0];
            }

        }
        return newDate;
    }

    /**
     * Valida��o de CPF
     *
     * @param CPF
     * @return
     */
    public static boolean checkCPF(String CPF) {

        CPF = CPF.replaceAll("\\.", "");
        CPF = CPF.replaceAll("\\-", "");


        if (CPF.length() != 11 || CPF.contains("@")) {
            return false;
        }

        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666")
                || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {

                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (Exception erro) {
            return (false);
        }

    }

    /**
     * Get Circular image
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getCircleBitmapImage(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * Transforma o Bitmap em base 64
     *
     * @param image
     * @return
     */
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.i("Image Log: " + imageEncoded);
        return imageEncoded;
    }

    /**
     * Transforma a base 64 para bitmap
     *
     * @param input
     * @return
     */
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    //
    // /**
    // * Retorna a time no padrao hh:mm
    // * @param date
    // * @return
    // */
    // public static String getFormatedTime(String time) {
    // // SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm",
    // Locale.getDefault());
    // // return dateFormat.format(time);
    //
    //
    //
    //
    // return time.hour + ":" + time.minute;
    //
    // }

    /**
     * This method also assumes endDate >= startDate
     **/
    public static long daysBetween(Date startDate, Date endDate) {
        Calendar sDate = getDatePart(startDate);
        Calendar eDate = getDatePart(endDate);

        long daysBetween = 0;
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static Calendar getDatePart(Date date) {
        Calendar cal = Calendar.getInstance(); // get calendar instance
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0); // set hour to midnight
        cal.set(Calendar.MINUTE, 0); // set minute in hour
        cal.set(Calendar.SECOND, 0); // set second in minute
        cal.set(Calendar.MILLISECOND, 0); // set millisecond in second

        return cal; // return the date part
    }

    /**
     * Verifica se o dispositivo eh um tablet.
     */
    public static final int TABLET_SIZE = 6;

    public static boolean isTablet(Context context) {
        DisplayMetrics metrics;
        metrics = context.getResources().getDisplayMetrics();

        double inches = Math.sqrt((metrics.widthPixels * metrics.widthPixels) + (metrics.heightPixels * metrics.heightPixels)) / metrics.densityDpi;
        if (inches > TABLET_SIZE) {
            return true;
            // this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        return false;
    }

    /**
     * Show alert with ok button
     */
    public static void showGenericAlertOK(final Activity activity, final String title, final String msg) {
        try {
            if (activity != null && title != null && msg != null && !msg.equals("null")) {
                new Builder(activity).setTitle(title).setMessage(msg).setNeutralButton(activity.getString(R.string.label_ok), null).show();
            }
        } catch (Exception ex) {
            Log.i("Error open GenericAlert with msg: " + msg);
            Log.i(ex.getMessage());
        }
    }

    /**
     * Show alert with ok button
     */
    public static void showGenericAlertOK(final Activity activity, final String title, final String msg, final DialogInterface.OnClickListener clickListenner) {

        try {
            if (activity != null && title != null && msg != null && !msg.equals("null") && clickListenner != null) {
                Builder dialog = new Builder(activity);

                dialog.setTitle(title);
                dialog.setMessage(msg);

                dialog.setPositiveButton("OK", clickListenner);
                dialog.show();
            }
        } catch (Exception ex) {
            Log.i("Error open GenericAlert with msg: " + msg);
            Log.i(ex.getMessage());
        }

    }

    /**
     * Show alert with ok button
     */
    public static void showGenericAlertOK(final Activity activity, final String title, final String msg, boolean cancelable, final DialogInterface.OnClickListener clickListenner) {

        try {
            if (activity != null && title != null && msg != null && !msg.equals("null") && clickListenner != null) {
                Builder dialog = new Builder(activity);

                dialog.setTitle(title);
                dialog.setMessage(msg);
                dialog.setCancelable(cancelable);

                dialog.setPositiveButton("OK", clickListenner);
                dialog.show();
            }
        } catch (Exception ex) {
            Log.i("Error open GenericAlert with msg: " + msg);
            Log.i(ex.getMessage());
        }

    }

    /**
     * Show alert with ok button
     */
    public static void showGenericAlertOK(final Activity activity, final String title, final String msg, final DialogInterface.OnClickListener clickListenner, boolean cancelable,
                                          final DialogInterface.OnClickListener cancelListener) {
        try {
            if (activity != null && cancelListener != null && title != null && msg != null && !msg.equals("null") && clickListenner != null) {
//				new AlertDialog.Builder(activity).setOnCancelListener(cancelListener).setTitle(title).setMessage(msg).setCancelable(cancelable).setNeutralButton(activity.getString(R.string.ok), clickListenner).show();

                Builder adb = new Builder(activity);


//			    adb.setView(alertDialogView);


                adb.setTitle(title);
                adb.setMessage(msg);
                adb.setCancelable(cancelable);

//			    adb.setIcon(android.R.drawable.ic_dialog_alert);


                adb.setPositiveButton("OK", clickListenner);


                adb.setNegativeButton("Cancelar", cancelListener);
                adb.show();


            }
        } catch (Exception ex) {
            Log.i("Error open GenericAlert with msg: " + msg);
            Log.i(ex.getMessage());
        }
    }

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    final static Calendar cal = Calendar.getInstance();
    static int year = cal.get(Calendar.YEAR);
    static int month = cal.get(Calendar.MONTH);
    static int day = cal.get(Calendar.DAY_OF_MONTH);

    /**
     * Disable soft keyboard from appearing, use in conjunction with android:windowSoftInputMode="stateAlwaysHidden|adjustNothing"
     *
     * @param editText
     */
    @SuppressLint("NewApi")
    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }

    /**
     * Get the day and mounth String by date
     *
     * @param dt
     * @return
     */
    public static String getStringDateMounthByDate(Context context, Date dt) {

        int MONTH_YEAR_FLAG = DateUtils.FORMAT_SHOW_DATE;

        long millis;
        Time firstMonthTime = new Time();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);

        firstMonthTime.monthDay = calendar.get(Calendar.DAY_OF_MONTH);
        firstMonthTime.month = calendar.get(Calendar.MONTH);
        firstMonthTime.year = calendar.get(Calendar.YEAR);

        millis = firstMonthTime.toMillis(true);

        StringBuilder monthYearStringBuilder = new StringBuilder(50);
        Formatter monthYearFormatter = new Formatter(monthYearStringBuilder, new Locale("pt", "BR"));

        String monthTitle = DateUtils.formatDateRange(context, monthYearFormatter, millis, millis, MONTH_YEAR_FLAG).toString();

        return monthTitle;
    }


    public static String getStringDateISOByDateSecT(Date dt) {
        String asISO = "";
        try {

            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            df.setTimeZone(tz);
            asISO = df.format(dt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return asISO;
    }

    /*
     * This function allows to know if there is an application that responds to
     * the specified action.
     *
     * @param context
     * @param action  Action that requires an application.
     * @return
     */
    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * Returns TRUE if a specified hardware feature is present.
     * <p>
     * Use PackageManager.FEATURE_[feature] to specify the feature to query.
     *
     * @param context
     * @param hardwareFeature Use PackageManager.FEATURE_[feature] to specify the feature to
     *                        query.
     * @return
     */
    public static boolean isHardwareFeatureAvailable(Context context, String hardwareFeature) {
        return context.getPackageManager().hasSystemFeature(hardwareFeature);
    }

    /**
     * Remove a acentua��o de uma string
     *
     * @param str
     * @return
     */
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    /**
     * Verifica se um email � v�lido
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            Pattern p = Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(target);
            return m.matches();
//			return EMAIL_ADDRESS_PATTERN.matcher(target).matches();
        }
    }

    /**
     * Transforma uma data em tempo passado - escrito
     */
    public static String getTimePassed(Date dataPassada) {

        Date atual = new Date();
        long postTimeMM = atual.getTime() - dataPassada.getTime();

        long postTime = TimeUnit.MILLISECONDS.toSeconds(postTimeMM);


        String timeString = "";

        // Longer than a day before?
        if (postTime > 86400) {
            int timeStringCount = (int) (postTime / 86400);
            if (timeStringCount > 1) {
                timeString = "Há " + timeStringCount + " dias";
            } else {
                timeString = "Há " + timeStringCount + " dia";
            }
        } else if (postTime > 3600) {
            int timeStringCount = (int) (postTime / 3600);
            if (timeStringCount > 1) {
                timeString = "Há " + timeStringCount + " horas";
            } else {
                timeString = "Há " + timeStringCount + " hora";
            }
        } else {
            timeString = "Há " + "menos de uma hora";
        }

        return timeString;
    }


//    public static void eventRedirectSalesforce(Activity context, String json) {
//        try {
//            if (UserManager.getInstance().getUser() != null && !StringUtils.isNullOrEmpty(UserManager.getInstance().getUser().getEmail())) {
//                Log.i("eventRedirectSalesforce - Evento Salesforce OK!");
//                Log.i("eventRedirectSalesforce - json: " + json);
//                SalesEventRedirectCallback callback = new SalesEventRedirectCallback();
//                JSONObject call = new JSONObject(json);
//                Applic.getInstance().getWsRequests().requestGetWS(context, VoopterCommands.CMD_SALESFORCE_EVENT_REDIRECT.getValue(), callback, call, GetRequestUITask.HttpMethod.POST);
//            } else {
//                Log.i("Evento Salesforce não ok - usuario nao logado");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}