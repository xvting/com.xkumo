package com.xkumo.xstock.weixin.util;

public class ReturnCodeUtil {

	public static String getMessage(String code)
	{
		if("48001".equalsIgnoreCase(code))
		{
			return "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限"; 
		}
		else
		{
			return code;
		}
	}
}
