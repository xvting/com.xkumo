package com.xkumo.xstock.weixin.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xkumo.xstock.weixin.util.DBFactory;

public class SinaFutureService {

	public String getFutureAG1512DataReport() throws Exception {

		String result = "";
		
		StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM futuredatasinaminute ORDER BY sinaseq DESC  LIMIT 0,2;");

          
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
      
        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement();
	        
	        resultSet = sSta.executeQuery(sql.toString());
	        
	        while (resultSet.next())
	        {
	        	result += "期货名称:" + resultSet.getString("FutureName") + "\r\n";
	        	result += "当前价格:" + resultSet.getString("CurrentPrice") + "\r\n";
	        	result += "数据时间:" + resultSet.getString("DataDay") + " " + resultSet.getString("DataTime") + "\r\n";
	        	result += "读取时间:" + resultSet.getString("CreateTime") +  "\r\n";
	        	result += "\r\n";
	        }
	    }finally
	    {
	    	try
	        {
	            if (connection != null)
	            {
	            	connection.close();
	            }
	            if (sSta != null)
	            {
	            	sSta.close();
	            }
	           
	            if (resultSet != null)
	            {
	            	resultSet.close();
	            }
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
        
	    return result;
	}
}
