/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xkumo.xstock.report.client.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author xvting
 */
public class DBFactory {
public static Connection CreateConnection() throws ClassNotFoundException, SQLException
	{
		Connection connection = null;

		 Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection(
                        "jdbc:mysql://xkumo001.mysql.rds.aliyuncs.com:3306/stock?useUnicode=true&characterEncoding=UTF-8",
	        		//"jdbc:mysql://115.29.243.212:3306/stock?useUnicode=true",
	        		//"jdbc:mysql://115.29.243.212:3306/stock",
	        		"xvting",
	    		   	"Xkumo010801"
	        		);

		return connection;
	}

	public static java.sql.Date ConvertToSqlDate(java.util.Date  pDate)
	{
		if(pDate == null)
		{
			return null;
		}
		else
		{
			return new java.sql.Date(pDate.getTime());
		}
	}
}
