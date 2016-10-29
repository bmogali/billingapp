package com.billingapp.rest.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static String DATETIMEFORMAT = "MM-dd-yyyy HH:mm:ss";
	
	private static String DATEFORMAT = "MM-dd-yyyy";
	
	public static Date stringToDateTime(String dateString) {
		return getFormattedDate(dateString, DATETIMEFORMAT);
	}
	
	public static Date stringToDate(String dateString) {
		return getFormattedDate(dateString, DATEFORMAT);
	}

	public static String dateTimeToString(Date date) {
		return getFormattedDateString(date, DATETIMEFORMAT);
	}
	
	public static String dateToString(Date date) {
		return getFormattedDateString(date, DATEFORMAT);
	}

	private static String getFormattedDateString(Date date, String format) {
		String dateString = null;
		if (null != date) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			try {
				dateString = formatter.format(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dateString;
	}
	
	private static Date getFormattedDate(String dateString, String format) {
		Date date = null;
		if (null != dateString && !"".equalsIgnoreCase(dateString)) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			try {
				date = formatter.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	
}
