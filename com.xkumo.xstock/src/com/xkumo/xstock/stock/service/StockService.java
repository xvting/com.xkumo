package com.xkumo.xstock.stock.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xkumo.vendor.IStockExchangeInfo;
import com.xkumo.vendor.StockExchangeFactory;
import com.xkumo.xstock.application.App;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.sina.service.StockDataSinaService;
import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.stock.domain.StockExchangeDo;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.xstock.system.SQLFactory;
import com.xkumo.xstock.task.TaskManager;



public class StockService {

	public int countStock(String stockcode, String stockname,
			String stockexchangecode) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//count(1) as count
		
		int result = -1;
		
		StringBuilder sql = new StringBuilder();
        sql.append("select count(1) as count from Stock t where 1=1");

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
        
        if(stockexchangecode == null || stockexchangecode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockexchangecode like '%" + stockexchangecode + "%'");
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
	
	public List<StockDo> listStock(String stockcode, String stockname,
			String stockexchangecode) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from Stock t where 1=1");

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
        
        if(stockexchangecode == null || stockexchangecode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockexchangecode like '%" + stockexchangecode + "%'");
        }
        
        
     
        sql.append(" order by t.stockexchangecode, t.stockcode");
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<StockDo> result = new ArrayList<StockDo>();

        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(sql.toString());
	        while (resultSet.next())
	        {
	        	StockDo  fms= new StockDo();
	        	fms.setStockname(resultSet.getString("STOCKNAME"));
	        	fms.setStockcode(resultSet.getString("STOCKCODE"));
	        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
	        	fms.setStockremark(resultSet.getString("STOCKREMARK"));
	        	
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
	
	public List<StockDo> listStockByPage(String stockcode, String stockname,
			String stockexchangecode, int pageIndex , int limit) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from Stock t where 1=1");

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
        
        if(stockexchangecode == null || stockexchangecode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append("and t.stockexchangecode like '%" + stockexchangecode + "%'");
        }
        
        
     
        sql.append(" order by t.createtime desc");
        
        String pageSQL = SQLFactory.getPageSql(sql.toString(), pageIndex, limit);
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<StockDo> result = new ArrayList<StockDo>();

        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(pageSQL);
	        while (resultSet.next())
	        {
	        	StockDo  fms= new StockDo();
	        	fms.setStockname(resultSet.getString("STOCKNAME"));
	        	fms.setStockcode(resultSet.getString("STOCKCODE"));
	        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
	        	fms.setStockremark(resultSet.getString("STOCKREMARK"));
	        	
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

	public void refreshStockAll() throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		App.getTaskManager().StartTaskByID(TaskEnum.UpdateStockInfo.getID());
	}
	
	public void deleteStockAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		int i=0;

		Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        connection = DBFactory.CreateConnection();
   
   	 	try
        {

   		 	String sql = "DELETE FROM `Stock` WHERE 1";
	 		sSta = connection.createStatement();
	 		i = sSta.executeUpdate(sql);
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
	}

}
