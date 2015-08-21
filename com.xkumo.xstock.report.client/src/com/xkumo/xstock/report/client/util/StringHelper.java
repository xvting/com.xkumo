/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.util;

/**
 *
 * @author xvting
 */
public class StringHelper {
 public static boolean isEmpty(String checkValue)
    {
        return null == checkValue || "".equals(checkValue);
    }

    public static boolean isNotEmpty(String checkValue)
    {
        return !isEmpty(checkValue);
    }

    public static boolean isEqual(String value1, String value2)
    {
    	if(value1 == null )
    	{
    		if(value2 == null)
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return value1.equals(value2);
    	}
    }

    /**
     * 是否是空格组成的字符串
     * @param checkValue
     * @return
     */
    public static boolean isWhitespace(String checkValue)
    {
    	if (checkValue == null || checkValue.equals(""))
    	{
    		return false;
    	}
    	else
    	{
    		String trimValue = checkValue.trim();

    		return trimValue.equals("");
    	}
    }
}
