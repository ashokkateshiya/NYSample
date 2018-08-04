package com.nytimesexample;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("WeakerAccess")
public final class DateFormatter {

    public final static String DATE_FORMAT_DD_MMM_YYYY          = "dd-MMM-yyyy";
    public final static String DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS = "dd-MMM-yyyy HH:mm:ss";
    public final static String DATE_FORMAT_DD_MMM_YYYY_HH_MM = "dd-MMM-yyyy HH:mm";
    public final static String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS  = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_YYYY_MM_DD_HH_MM  = "yyyy-MM-dd HH:mm";
    public final static String DATE_FORMAT_YYYY_MM_DD           = "yyyy-MM-dd";
    public final static String DATE_FORMAT_DD_MM_YYYY           = "dd-MM-yyyy";
    public final static String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS  = "dd-MM-yyyy HH:mm:ss";
    public final static String DATE_FORMAT_MMM_YYYY             = "MMM yyyy";
    public final static String DATE_FORMAT_MM_YY                = "MMyy";
    public final static String DATE_FORMAT_MM_YY_SLASH          = "MM/yy";
    public final static String DATE_FORMAT_HH_MM                = "HH:mm";
    public final static  String TIME_FORMAT = " HH:mm:ss";

    /**
     *
     * @param strConvertDate date string to convert
     * @param inputFormat input date format
     * @param outputFormat output date format
     *
     * @return formatted date
     */
    public static String convertDateFormat(String strConvertDate, String inputFormat, String outputFormat) {
        if (TextUtils.isEmpty(strConvertDate) || TextUtils.isEmpty(inputFormat) || TextUtils.isEmpty(outputFormat))
            return null;

        String outputDateStr = null;
        try {
            DateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
            DateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.getDefault());

            Date date = inputDateFormat.parse(strConvertDate);
            outputDateStr = outputDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputDateStr;
    }

    /**
     *
     * @param timeInMillis time in millis
     * @param outputDateFormat output date format
     * @return formatted date string
     */
    public static String getFormattedDate(long timeInMillis, String outputDateFormat) {
        Date date = new Date(timeInMillis);
        return getFormattedDate(date, outputDateFormat);
    }


    /**
     *
     * @param date date to format
     * @param outputDateFormat output date format
     * @return formatted date string
     */
    public static String getFormattedDate(Date date, String outputDateFormat) {
        if (date == null || TextUtils.isEmpty(outputDateFormat))
            return null;

        try {
            DateFormat sdf = new SimpleDateFormat(outputDateFormat, Locale.getDefault());
            return sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return date object based on date format from given date string.
     * @param dateString date string
     * @param inputFormat input date format
     * @return formatted date string
     */
    public static Date getDate(String dateString, String inputFormat) {
        if (TextUtils.isEmpty(dateString) || TextUtils.isEmpty(inputFormat))
            return null;

        try {
            DateFormat dateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
            return dateFormat.parse(dateString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getInvictusDateTimeFormat(String invictusOutPutDateFormat){
        String outPutDateTimeFormat = invictusOutPutDateFormat ;//PreferenceUtils.getString(PreferenceUtils.INVICTUS_DATE_FORMAT,"");
        if(!TextUtils.isEmpty(outPutDateTimeFormat)){
            outPutDateTimeFormat = outPutDateTimeFormat+" "+DateFormatter.TIME_FORMAT;
        }else{
            outPutDateTimeFormat = DateFormatter.DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS;
        }
        return outPutDateTimeFormat;
    }

    public static String getInvictusTimeFormat(String invictusOutPutDateFormat){
        String outPutDateTimeFormat = invictusOutPutDateFormat ;//PreferenceUtils.getString(PreferenceUtils.INVICTUS_DATE_FORMAT,"");
        if(!TextUtils.isEmpty(outPutDateTimeFormat)){
            outPutDateTimeFormat = outPutDateTimeFormat+" "+DateFormatter.DATE_FORMAT_HH_MM;
        }else{
            outPutDateTimeFormat = DateFormatter.DATE_FORMAT_DD_MMM_YYYY_HH_MM;
        }
        return outPutDateTimeFormat;
    }

}
