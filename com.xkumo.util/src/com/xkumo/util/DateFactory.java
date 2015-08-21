package com.xkumo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFactory {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/***
	 * yyyy-MM-dd 字符串转换为Data型
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date ConvertToData(String dateString) throws ParseException
	{
		if(dateString == null || dateString.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			return sdf.parse(dateString);
		}
	}
	
	public static String ConvertToString(Date date)
	{
		if(date == null)
		{
			return "";
		}
		else
		{
			return sdf.format(date);
		}
	}
	
	public static String ConvertToDatetimeString(Date date)
	{
		if(date == null)
		{
			return "";
		}
		else
		{
			return sdtf.format(date);
		}
	}
}
