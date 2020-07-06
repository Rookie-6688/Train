package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		DateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1=d.parse("2019-9-29 07:00:00");
		
		
		Calendar cld=Calendar.getInstance();
		cld.setTime(date1);
		cld.set(Calendar.SECOND, -1);
		Date date11= cld.getTime();
		String date2=d.format(date11);
		System.out.println(date2);
	}
}
