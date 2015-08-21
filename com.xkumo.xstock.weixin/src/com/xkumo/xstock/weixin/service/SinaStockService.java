package com.xkumo.xstock.weixin.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xstock.weixin.util.DBFactory;

public class SinaStockService {

	public String getStockDataReport() throws Exception {

		String result = "";
		
		StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM stock.tasklog where taskid = '00003' order by starttime desc  LIMIT 0,2;");

          
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
	        	result += "处理名称:" + resultSet.getString("TaskDescription") + "\r\n";
	        	result += "开始时间:" + resultSet.getString("StartTime") + "\r\n";
	        	result += "结束时间:" + resultSet.getString("EndTime") + "\r\n";
	        	result += "处理结果:" + resultSet.getString("Message") + resultSet.getString("Description") + "\r\n";
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
