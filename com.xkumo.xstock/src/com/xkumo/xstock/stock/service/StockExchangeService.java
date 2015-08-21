package com.xkumo.xstock.stock.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xkumo.xstock.stock.domain.StockExchangeDo;
import com.xkumo.xstock.system.DBFactory;




public class StockExchangeService {

	public List<StockExchangeDo> listStockExchange(String stockexchangecode,
			String stockexchangename) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from StockExchange t where 1=1");

        if(stockexchangecode == null || stockexchangecode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockexchangecode like '%" + stockexchangecode + "%'");
        }
        
        if(stockexchangename == null || stockexchangename.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockexchangename like '%" + stockexchangename + "%'");
        }
     
        sql.append(" order by t.createtime");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<StockExchangeDo> result = new ArrayList<StockExchangeDo>();

        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(sql.toString());
	        while (resultSet.next())
	        {
	        	StockExchangeDo  fms= new StockExchangeDo();
	        	fms.setStockexchangename(resultSet.getString("STOCKEXCHANGENAME"));
	        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
	        	fms.setStockexchangeaddress(resultSet.getString("STOCKEXCHANGEADDRESS"));
	        	fms.setStockexchangeremark(resultSet.getString("STOCKEXCHANGEREMARK"));
	        	
	            result.add(fms);
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
