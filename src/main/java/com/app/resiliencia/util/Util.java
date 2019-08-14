package com.app.resiliencia.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
//	public static void main(String k[]) {
//		Date date =convertStringtoDate("2018-05-05");
//		System.out.println("2018-05-26T05:00:00.000Z".substring(0, 10));
//		System.out.println("DIa: "+convertDateToString(date));
//	}
	public static String convertDateToString(Date dia) {
		DateFormat df = new SimpleDateFormat("YYYY-MM-DD");

		return  df.format(dia);
	}
	public static Date convertStringtoDate(String dia) {
		Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        try {
			 date = formatter.parse(dia);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return date;

	}
	public static String  formatter(String c) {
		 
		 
		return c.substring(0, 10);
	}
}
