package com.ge.predix.util;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class DateUtil {

	public static long getTimeInMillisecond(Timestamp time) {
		//final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-M HH:mm:ss");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final String dateInString = time.toString();
		Date date;
		try {
			date = sdf.parse(dateInString);
			return date.getTime();
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String extractDate(Object o){
		final Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		//final Format formatter = new SimpleDateFormat("dd-M-yyyy");
		final String date = formatter.format(o);
		return date;
	}
	public static Timestamp getTimestamp(String date){
		final Timestamp ts=Timestamp.valueOf(date);
		return ts;
	}

	public static Timestamp getPreviousDate(String dateString, int number){
		try {
			//final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			date = new DateTime(sdf.parse(dateString)).minusDays(number).toDate();
			dateString = sdf.format(date);
			return getTimestamp(dateString);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Timestamp addHours(String dateString){
		try {
			Date date;
			final long hoursInMillis = 60L * 60L * 1000L;
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = new DateTime(sdf.parse(dateString)).toDate();
			date = new Date(date.getTime()+(2L * hoursInMillis));
			dateString = sdf.format(date);
			return getTimestamp(dateString);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Timestamp substractHours(String dateString){
		try {
			Date date;
			final long hoursInMillis = 60L * 60L * 1000L;
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = new DateTime(sdf.parse(dateString)).toDate();
			date = new Date(date.getTime()-(2L * hoursInMillis));
			dateString = sdf.format(date);
			return getTimestamp(dateString);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
