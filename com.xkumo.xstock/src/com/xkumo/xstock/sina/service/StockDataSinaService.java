package com.xkumo.xstock.sina.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xstock.application.App;
import com.xkumo.xstock.core.TaskEnum;
import com.xkumo.xstock.sina.domain.SinaDailyStockDataDo;
import com.xkumo.xstock.stock.domain.StockDo;
import com.xkumo.xstock.system.DBFactory;
import com.xkumo.xstock.system.SQLFactory;
import com.xkumo.xstock.task.TaskManager;
import com.xkumo.util.DateFactory;



public class StockDataSinaService {
	
	public int countStock(String stockcode, String stockname,
			String stockexchangecode) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int result = -1;
		
		StringBuilder sql = new StringBuilder();
        sql.append("select count(1) as count from StockDataSina t where 1=1 ");

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

	public List<SinaDailyStockDataDo> listStockByPage(String stockcode, String stockname,
			String stockexchangecode, int pageIndex, int limit) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder();
        sql.append("select t.* from StockDataSina t where 1=1 ");

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
        
        String pageSQL = SQLFactory.getPageSql(sql.toString(), pageIndex, limit);
        
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        List<SinaDailyStockDataDo> result = new ArrayList<SinaDailyStockDataDo>();

        connection = DBFactory.CreateConnection();
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(pageSQL);
	        while (resultSet.next())
	        {
	        	SinaDailyStockDataDo  fms= SinaDailyStockDataDo.create(resultSet);

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

	/***
	 * 更新当日新浪数据
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public void updateStockDataSinaByDay() throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		App.getTaskManager().StartTaskByID(TaskEnum.UpdateStockDataSina.getID());
		
//		StringBuilder sql = new StringBuilder();
//        sql.append("select t.* from Stock t where 1=1 ");
//       // sql.append(" order by t.updatetime");
//        
//   	 	Connection connection = null;
//
//        Statement sSta = null;
//  	
//        ResultSet resultSet =null;
//        List<StockDo> result = new ArrayList<StockDo>();
//
//        connection = DBFactory.CreateConnection();
//        
//        try
//	    {
//	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//	        
//	      
//	         resultSet = sSta.executeQuery(sql.toString());
//	         
//	        while (resultSet.next())
//	        {
//	        	SinaDailyStockDataDo sinaData = getDailyStockData(resultSet.getString("STOCKCODE"),resultSet.getString("STOCKEXCHANGECODE"));
//	        	
//	        	if(sinaData == null)
//	        	{
//	        		//Do Nothing
//	        	}
//	        	else
//	        	{
//	        		InsertOrUpdateSinaDailyStockDataDo(sinaData);
//	        	}
//	        }
//	    }finally
//	    {
//	    	try
//	        {
//	            if (connection != null)
//	            {
//	            	connection.close();
//	            }
//	            if (sSta != null)
//	            {
//	            	sSta.close();
//	            }
//	           
//	            if (resultSet != null)
//	            {
//	            	resultSet.close();
//	            }
//	        }
//	        catch (SQLException e)
//	        {
//	            e.printStackTrace();
//	        }
//	    }
       
	}
	
	private void InsertOrUpdateSinaDailyStockDataDo(SinaDailyStockDataDo dataDo) throws ClassNotFoundException, SQLException
	{
		
        if(dataDo == null)
        {
        	return;
        }
        else
        {
        	//Do Nothing
        }
        
       
        
        String sql = "select * from StockDataSina  where  stockCode='" + dataDo.getStockcode() 
        + "' and stockExchangeCode = '" + dataDo.getStockexchangecode()
        + "' and DataDay = '" + DateFactory.ConvertToDatetimeString(dataDo.getDataday())+"'" ;
        
   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;

        connection = DBFactory.CreateConnection();
        
        try
	    {
        	  sSta = connection.createStatement();
   	        
	      
	         resultSet = sSta.executeQuery(sql);
	         
	         boolean hasValue = false;
	         int currentsinaseq = 0;
	         
	        while (resultSet.next())
	        {
	        	
	        	hasValue = true;
	        	currentsinaseq = resultSet.getInt("sinaseq");
	        	break;
	        	
	        }
	        
	        if(hasValue)
	        {
	        	//����
	        	 String updateSql = "update StockDataSina set "  
	        		 +" stockCode = '"+ dataDo.getStockcode() 
	        		 +"' , stockName = '"+ dataDo.getStockname()
	        		 +"' , StockExchangeCode = '"+ dataDo.getStockexchangecode()
	        		 +"' , OpenPrice = '"+ dataDo.getOpenprice()
	        		 +"' , LastClosePrice = '"+ dataDo.getLastcloseprice()
	        		 +"' , CurrentPrice = '"+ dataDo.getCurrentprice()
	        		 +"' , TopPrice = '"+ dataDo.getTopprice()
	        		 +"' , BottomPrice = '"+ dataDo.getBottomprice()
	        		 +"' , BuyPrice = '"+ dataDo.getBuyprice()
	        		 +"' , SellPrice = '"+ dataDo.getSellprice()
	        		 +"' , TransactionAmount = '"+ dataDo.getTransactionamount()
	        		 +"' , TransactionNumber = '"+ dataDo.getTransactionnumber()
	        		 
	        		 +"' , Buy1Price = '"+ dataDo.getBuy1price()
	        		 +"' , Buy1Number = '"+ dataDo.getBuy1number()
	        		 +"' , Buy2Price = '"+ dataDo.getBuy2price()
	        		 +"' , Buy2Number = '"+ dataDo.getBuy2number()
	        		 +"' , Buy3Price = '"+ dataDo.getBuy3price()
	        		 +"' , Buy3Number = '"+ dataDo.getBuy3number()
	        		 +"' , Buy4Price = '"+ dataDo.getBuy4price()
	        		 +"' , Buy4Number = '"+ dataDo.getBuy4number()
	        		 +"' , Buy5Price = '"+ dataDo.getBuy5price()
	        		 +"' , Buy5Number = '"+ dataDo.getBuy5number()
	        		 
	        		 +"' , Sell1Price = '"+ dataDo.getSell1price()
	        		 +"' , Sell1Number = '"+ dataDo.getSell1number()
	        		 +"' , Sell2Price = '"+ dataDo.getSell2price()
	        		 +"' , Sell2Number = '"+ dataDo.getSell2number()
	        		 +"' , Sell3Price = '"+ dataDo.getSell3price()
	        		 +"' , Sell3Number = '"+ dataDo.getSell3number()
	        		 +"' , Sell4Price = '"+ dataDo.getSell4price()
	        		 +"' , Sell4Number = '"+ dataDo.getSell4number()
	        		 +"' , Sell5Price = '"+ dataDo.getSell5price()
	        		 +"' , Sell5Number = '"+ dataDo.getSell5number()
	        		 
	        		 +"' , DataDay = '"+ DateFactory.ConvertToDatetimeString(dataDo.getDataday())
	        		 +"' , DataTime = '"+ dataDo.getDatatime()
	        		 
	        		 +"' , updatetime = now() where  sinaseq = '" + currentsinaseq + "' ";
	        	  int updateResultCount = sSta.executeUpdate(updateSql); 
	        }
	        else
	        {
	        	
	        	//����
	        	 String insertSql = "insert into StockDataSina " 
	        		 + " (StockCode, stockName,stockExchangeCode, " 
	        		 + " OpenPrice, LastClosePrice, CurrentPrice, TopPrice, BottomPrice, BuyPrice, SellPrice, TransactionAmount, TransactionNumber, "
	        		 + " Buy1Price, Buy1Number, Buy2Price, Buy2Number, Buy3Price, Buy3Number, Buy4Price, Buy4Number, Buy5Price, Buy5Number, "
	        		 + " Sell1Price, Sell1Number, Sell2Price, Sell2Number, Sell3Price, Sell3Number, Sell4Price, Sell4Number, Sell5Price, Sell5Number, "
	        		 + " DataDay, DataTime, "
	        		 + " createtime,updatetime) " 
	        		 + " values (" 
	        		 + " '" + dataDo.getStockcode() + "' "
	        		 + ",  '" + dataDo.getStockname() + "' "
	        		 + ",  '" + dataDo.getStockexchangecode() + "' "
	        		 
	        		 + ",  '" + dataDo.getOpenprice() + "' "
	        		 + ",  '" + dataDo.getLastcloseprice() + "' "
	        		 + ",  '" + dataDo.getCurrentprice() + "' "
	        		 + ",  '" + dataDo.getTopprice() + "' "
	        		 + ",  '" + dataDo.getBottomprice()+ "' "
	        		 + ",  '" + dataDo.getBuyprice() + "' "
	        		 + ",  '" + dataDo.getSellprice() + "' "
	        		 + ",  '" + dataDo.getTransactionamount() + "' "
	        		 + ",  '" + dataDo.getTransactionnumber() + "' "
	        		 
	        		 + ",  '" + dataDo.getBuy1price() + "' "
	        		 + ",  '" + dataDo.getBuy1number() + "' "
	        		 + ",  '" + dataDo.getBuy2price() + "' "
	        		 + ",  '" + dataDo.getBuy2number() + "' "
	        		 + ",  '" + dataDo.getBuy3price() + "' "
	        		 + ",  '" + dataDo.getBuy3number() + "' "
	        		 + ",  '" + dataDo.getBuy4price() + "' "
	        		 + ",  '" + dataDo.getBuy4number() + "' "
	        		 + ",  '" + dataDo.getBuy5price() + "' "
	        		 + ",  '" + dataDo.getBuy5number() + "' "
	        		 
	        		 + ",  '" + dataDo.getSell1price() + "' "
	        		 + ",  '" + dataDo.getSell1number() + "' "
	        		 + ",  '" + dataDo.getSell2price() + "' "
	        		 + ",  '" + dataDo.getSell2number() + "' "
	        		 + ",  '" + dataDo.getSell3price() + "' "
	        		 + ",  '" + dataDo.getSell3number() + "' "
	        		 + ",  '" + dataDo.getSell4price() + "' "
	        		 + ",  '" + dataDo.getSell4number() + "' "
	        		 + ",  '" + dataDo.getSell5price() + "' "
	        		 + ",  '" + dataDo.getSell5number() + "' "
	        		 
	        		 + ",  '" + DateFactory.ConvertToDatetimeString(dataDo.getDataday()) + "' "
	        		 + ",  '" + dataDo.getDatatime() + "' "
	        		 
	        		 +", now(), now())" ;
	        	  int insertResultCount = sSta.executeUpdate(insertSql); 
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
        
	}

	public void deleteStockDataSinaAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int i=0;

		Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        connection = DBFactory.CreateConnection();
   
   	 	try
        {

   		 	String sql = "DELETE FROM `StockDataSina` WHERE 1";
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

	/**
	 * 更新MA数据
	 * @param string
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int  updateDataMAByID(String seq) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
        String sql = "select * from StockDataSina  where sinaseq ='" + seq + "'";

   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        
        connection = DBFactory.CreateConnection();
        
        try
        {
        
		    //获得当前的详细数据
	        SinaDailyStockDataDo currentData = null;
	        sSta = connection.createStatement();
	        
	        try
		    {

		        resultSet = sSta.executeQuery(sql);
		        while (resultSet.next())
		        {
		        	currentData = new SinaDailyStockDataDo();
		        	currentData.setSinaseq(resultSet.getInt("SINASEQ"));
		        	currentData.setStockname(resultSet.getString("STOCKNAME"));
		        	currentData.setStockcode(resultSet.getString("STOCKCODE"));
		        	currentData.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
		        	currentData.setOpenprice(resultSet.getString("OPENPRICE"));
		        	currentData.setLastcloseprice(resultSet.getString("LASTCLOSEPRICE"));
		        	currentData.setCurrentprice(resultSet.getString("CURRENTPRICE"));
		        	currentData.setTopprice(resultSet.getString("TOPPRICE"));
		        	currentData.setBottomprice(resultSet.getString("BOTTOMPRICE"));
		        	currentData.setBuyprice(resultSet.getString("BUYPRICE"));
		        	currentData.setSellprice(resultSet.getString("SELLPRICE"));
		        	currentData.setTransactionamount(resultSet.getString("TRANSACTIONAMOUNT"));
		        	currentData.setTransactionnumber(resultSet.getString("TRANSACTIONNUMBER"));
		        	currentData.setDataday(resultSet.getDate("DATADAY"));
		        	currentData.setDatatime(resultSet.getString("DATATIME"));
		        	
		        	break;
		        }
		    }finally
		    {
		    	try
		        {
		           
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
		    
		    return updateMa(currentData, connection);
        }
        finally
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
	      
        	 }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
        }
	    
	}
	
	private float getMAValue(int count , float[] dataList)
	{
		if(dataList == null || dataList.length <= 0)
		{
			return 0.0f;
		}
		else
		{
			//Do Nothing
		}
			
		 	float ma = 0.0f;
		    float matotal = 0.0f;
		    int macount = 0;
		    
		    for(int i = 0; i < count && i <dataList.length ; i++)
		    {
		    	float curr = dataList[i];
		    	if (curr <= 0.0f)
		    	{
		    		//Do Nothing
		    	}
		    	else
		    	{
		    		matotal += curr;
		    		macount++;
		    	}
		    }
		    
		    if(macount == 0)
		    {
		    	ma = 0;
		    }
		    else
		    {
		    	ma = matotal / macount;
		    }
		    
		    return ma;
	}

	public int updateStockDataSinaAllMA() throws SQLException, ClassNotFoundException {
		
		String coutSql = "select count(1) as cnt from StockDataSina ";
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
        

   	 	Connection connection = null;

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        
        connection = DBFactory.CreateConnection();
        
        int result = 0;
        
        try
        {
        
		  

	        sSta = connection.createStatement();
	        int recordcount  = 0;
	        ResultSet resultCount =null;
	        try
		    {
	        	resultCount = sSta.executeQuery(coutSql);
		        while (resultCount.next())
		        {
		        	recordcount = resultCount.getInt("cnt");
		        	        	
		        	break;
		        }
		    }finally
		    {
		    	try
		        {
		           
		            if (resultCount != null)
		            {
		            	resultCount.close();
		            }
		        }
		        catch (SQLException e)
		        {
		            e.printStackTrace();
		        }
		    }
		    
		    int loopcount = recordcount % 500 + 1;
	        
		    for(int loopIndex = 0; loopIndex < loopcount; loopcount++)
		    {
		    	  //获得当前的详细数据
	        	List<SinaDailyStockDataDo> currentDataList = new ArrayList<SinaDailyStockDataDo>();
	        	
		        try
			    {
	
		        	String sql = "select * from StockDataSina order by updatetime asc limit 0, 500";
		        	
			        resultSet = sSta.executeQuery(sql);
			        
			        while (resultSet.next())
			        {
			        	SinaDailyStockDataDo  currentData = new SinaDailyStockDataDo();
			        	currentData.setSinaseq(resultSet.getInt("SINASEQ"));
			        	currentData.setStockname(resultSet.getString("STOCKNAME"));
			        	currentData.setStockcode(resultSet.getString("STOCKCODE"));
			        	currentData.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
			        	currentData.setOpenprice(resultSet.getString("OPENPRICE"));
			        	currentData.setLastcloseprice(resultSet.getString("LASTCLOSEPRICE"));
			        	currentData.setCurrentprice(resultSet.getString("CURRENTPRICE"));
			        	currentData.setTopprice(resultSet.getString("TOPPRICE"));
			        	currentData.setBottomprice(resultSet.getString("BOTTOMPRICE"));
			        	currentData.setBuyprice(resultSet.getString("BUYPRICE"));
			        	currentData.setSellprice(resultSet.getString("SELLPRICE"));
			        	currentData.setTransactionamount(resultSet.getString("TRANSACTIONAMOUNT"));
			        	currentData.setTransactionnumber(resultSet.getString("TRANSACTIONNUMBER"));
			        	currentData.setDataday(resultSet.getDate("DATADAY"));
			        	currentData.setDatatime(resultSet.getString("DATATIME"));
			        	
			        	currentDataList.add(currentData);
			        }
			    }finally
			    {
			    	try
			        {
			           
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
			    
			    for(int i = 0; i < currentDataList.size(); i++)
			    {
			    	SinaDailyStockDataDo  currentData = currentDataList.get(i);
			    	
			    	updateMa(currentData, connection);
		
			    }
		    }
		    
		    return result;
        }
        finally
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
	      
        	 }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	        }
        }
        
        //return result;
	}
	
	private int updateMa(SinaDailyStockDataDo  currentData, Connection connection) throws SQLException
	{
		int result = 0;
		
        ResultSet resultSet =null;
        
		if (currentData == null)
	    {
	    	return 0 ;
	    }
	    else
	    {
	    	//Do Nothing
	    }
	    
		Statement sSta = connection.createStatement();
	  	

    	int seq =  currentData.getSinaseq();
	    
	    List<SinaDailyStockDataDo> pricelist = new ArrayList<SinaDailyStockDataDo>();
	    //获得最近的120个数据
	    String select120SQL = "SELECT SINASEQ, STOCKCODE, STOCKEXCHANGECODE, DATADAY, CURRENTPRICE "
	    	+ " FROM StockDataSina WHERE " 
	    	+ " STOCKCODE = '" + currentData.getStockcode() + "' "
	    	+ " AND STOCKEXCHANGECODE = '" +currentData.getStockexchangecode() + "' " 
	    	+ " AND STRCMP(DATADAY,'"+ DateFactory.ConvertToString(currentData.getDataday())+"') <= 0 "
	    	+ " order by DATADAY desc limit 0, 120 ";
	    try
	    {

	         resultSet = sSta.executeQuery(select120SQL);
	        while (resultSet.next())
	        {
	        	SinaDailyStockDataDo fms = new SinaDailyStockDataDo();
	        	fms.setSinaseq(resultSet.getInt("SINASEQ"));
	        	fms.setStockcode(resultSet.getString("STOCKCODE"));
	        	fms.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
	        	fms.setCurrentprice(resultSet.getString("CURRENTPRICE"));
	        	fms.setDataday(resultSet.getDate("DATADAY"));
	        	
	        	pricelist.add(fms);
	        }
	     
	    }finally
	    {
	    	try
	        {
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
	    
	    float[] pricefloatList = new float[120];
	    for(int j = 0; j < pricefloatList.length; j++)
	    {
	    	pricefloatList[j] = 0.0f;
	    }
	    
	    for(int j = 0; j < pricefloatList.length && j < pricelist.size(); j++)
	    {
	    	SinaDailyStockDataDo fms = pricelist.get(j);
	    	
	    	pricefloatList[j] = fms.Currentpricefloat();
	    }
	    
	    float ma5 = getMAValue(5, pricefloatList);
	    float ma10 = getMAValue(10, pricefloatList);
	    float ma20 = getMAValue(20, pricefloatList);
	    float ma30 = getMAValue(30, pricefloatList);
	    float ma60 = getMAValue(60, pricefloatList);
	    float ma120 = getMAValue(120, pricefloatList);
	    float ma240 = getMAValue(240, pricefloatList);
	    
	    String updateMASQL = "UPDATE StockDataSina SET MA5='"+ma5+"', MA10='"+ma10+"', MA20='"+ma20+"', MA30='"+ma30+"', MA60='"+ma60+"', MA120='"+ma120+"', MA240='"+ma240+"', updatetime=now() WHERE sinaseq ='" + seq + "'";
	
	    try
	    {
	         int updateCount = sSta.executeUpdate(updateMASQL);
	        
	         result += updateCount;
	     
	    }finally
	    {
	    	try
	        {
	             
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

	public void deleteDataByID(String id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement sStat= null;
        
        connection = DBFactory.CreateConnection();
        
        try{
            String qzSql ="DELETE FROM StockDataSina WHERE 1 AND SINASEQ='"+ id+"'";

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
