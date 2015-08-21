package com.xkumo.xfeed.yahoo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xfeed.application.App;
import com.xkumo.xfeed.system.DBFactory;
import com.xkumo.xfeed.system.SQLFactory;
import com.xkumo.xfeed.task.TaskEnum;
import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataFlagDo;

public class StockDataYahooFlagService {

	public void updateStockInfoYahooFlag() {
		
		//App.getTaskManager().StartTaskByID(TaskEnum.UpdateStockDataYahooFlag.getID());
		
	}

	public void deleteData() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="DELETE FROM StockDataYahooFlag WHERE 1 ";

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

	public int countStock(String stockcode, String stockname) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int result = -1;
		
		StringBuilder sql = new StringBuilder();
        sql.append("select count(1) as count from StockDataYahooFlag t where 1=1 ");

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

	public List<YahooDailyStockDataFlagDo> listDate(String stockcode,
			String stockname,  int pageIndex, int limit) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from StockDataYahooFlag t where 1=1 ");

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
        
     
        sql.append(" order by updatetime desc");
        
        String pageSQL = SQLFactory.getPageSql(sql.toString(), pageIndex, limit);
        
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<YahooDailyStockDataFlagDo> result = new ArrayList<YahooDailyStockDataFlagDo>();

        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(pageSQL);
	        while (resultSet.next())
	        {
	        	YahooDailyStockDataFlagDo  fms= new YahooDailyStockDataFlagDo();
	        	fms.setSeq(resultSet.getInt("seq"));
	        	fms.setStockname(resultSet.getString("STOCKNAME"));
	        	fms.setStockcode(resultSet.getString("STOCKCODE"));
	        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
	        	
	        	fms.setDataloadcount(resultSet.getInt("dataloadcount"));
	        	
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
