package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random ranDom=new Random();
		int ranDomNumber=ranDom.nextInt(5000);
		return ranDomNumber;
		
	}
	/*
	 * this will give the current date
	 */
	public String getSystemDateYYYYDDMM() {
		Date dateobj=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateobj);
		return date;
	}
	/*
	 * if you want to capture requried date and pass the argument
	 * next day pass +interger number
	 * perivios date pass -interger number
	 */
	public String getRequriedDateYYYYDDMM(int days) {
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); // Get the current date
		cal.add(Calendar.DAY_OF_MONTH,days); // Add days
		String reqDate = sim.format(cal.getTime()); // Format the date
		return reqDate;
		
//		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal=sim.getCalendar();
//		cal.add(Calendar.DAY_OF_MONTH, days);
//		String reqDate = sim.format(cal.getTime());
//		return reqDate;
	}
}
