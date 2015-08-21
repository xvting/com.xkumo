package com.xkumo.xstock.yahoo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xstock.application.App;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.xstock.system.SQLFactory;
import com.xkumo.xstock.yahoo.domain.YahooDailyStockDataDo;


public class StockDataYahooService {

	public void updateStockDataYahooByDay() {
		// TODO Auto-generated method stub
		
		App.getTaskManager().StartTaskByID(TaskEnum.UpdateStockDataYahoo.getID());
	}

	public int countStock(String stockcode, String stockname, String date) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int result = -1;
		
		StringBuilder sql = new StringBuilder();
        sql.append("select count(1) as count from StockDataYahoo t where 1=1 ");

        if(stockcode == null || stockcode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockcode like '%" + stockcode + "%'");
        }
        
        if(stockname == null || stockname.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockname like '%" + stockname + "%'");
        }
        
        if(date == null || date.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.date = '" + date + "'");
        }
        
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
      
        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(sql.toString());
	        while (resultSet.next())
	        {
	        	result = resultSet.getInt("count");
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

	public List<YahooDailyStockDataDo> listDate(String stockcode,
			String stockname, String date, int pageIndex, int limit) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from StockDataYahoo t where 1=1 ");

        if(stockcode == null || stockcode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockcode like '%" + stockcode + "%'");
        }
        
        if(stockname == null || stockname.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockname like '%" + stockname + "%'");
        }
        
        if(date == null || date.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.date = '" + date + "'");
        }
     
        //sql.append(" order by t.updatetime desc");
        
        String pageSQL = SQLFactory.getPageSql(sql.toString(), pageIndex, limit);
        
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<YahooDailyStockDataDo> result = new ArrayList<YahooDailyStockDataDo>();

        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(pageSQL);
	        while (resultSet.next())
	        {
	        	YahooDailyStockDataDo  fms= new YahooDailyStockDataDo();
	        	fms.setYahooseq(resultSet.getInt("YAHOOSEQ"));
	        	fms.setStockname(resultSet.getString("STOCKNAME"));
	        	fms.setStockcode(resultSet.getString("STOCKCODE"));
	        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
	        	
	        	fms.setDate(resultSet.getString("date"));
	        	fms.setOpen(resultSet.getString("open"));
	        	fms.setHigh(resultSet.getString("high"));
	        	fms.setLow(resultSet.getString("low"));
	        	fms.setClose(resultSet.getString("close"));
	        	fms.setVolumn(resultSet.getString("volumn"));
	        	fms.setAdjclose(resultSet.getString("adjclose"));
	        	
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

	public void deleteData() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="DELETE FROM StockDataYahoo WHERE 1 ";

             sStat=connection.prepareStatement(qzSql);
               
             sStat.executeUpdate();
             
        }finally
  	    {
  	    	try
  	        {
  	            if (connection != null)
  	            {
  	            	connection.close();
  	            }
  	            if (sStat != null)
  	            {
  	            	sStat.close();
  	            }
  	        }
  	        catch (SQLException e)
  	        {
  	            e.printStackTrace();
  	        }
  	    }
	}

}
