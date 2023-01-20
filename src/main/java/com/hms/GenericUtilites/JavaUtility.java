package com.hms.GenericUtilites;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNo()
	{
		Random ran=new Random();
		int random = ran.nextInt(500);
		return random;
	}
	
	public String getSystemDate()
	{
		Date dt=new Date();
		String date = dt.toString();
		return date;
	}
	
	public String getSystemDateAndTimeInFormat()
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date systemDate=new Date();
		String getDateAndTime = dateFormat.format(systemDate);
		//System.out.println(getDateAndTime);
		return getDateAndTime.replaceAll(":", "-");
	}
}
