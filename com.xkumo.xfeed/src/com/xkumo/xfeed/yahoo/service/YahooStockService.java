package com.xkumo.xfeed.yahoo.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xkumo.xfeed.yahoo.domain.YahooDailyStockDataDo;

public class YahooStockService {
	private static int BUFFER_SIZE = 8096;
	
	/***
	 * ������˵ĵ�ǰ���
	 * @param stockcode ��Ʊ����
	 * @param stockexchangecode ��Ʊ���
	 * @return
	 * @throws IOException 
	 */
	public static List<YahooDailyStockDataDo> getDailyStockData(String stockcode, String stockname, String stockexchangecode) throws IOException
	{
		String exchangecode = stockexchangecode;
		if(stockexchangecode.equalsIgnoreCase("SH"))
		{
			exchangecode = "ss";
		}
		else
		{
			//Do Nothing
		}
		
		String url = "http://table.finance.yahoo.com/table.csv?s="+ stockcode +"."+ exchangecode ;
		String content = getContent(url);
		
		List<YahooDailyStockDataDo> result = CreateYahooDailyStockDataDo(content,stockcode, stockname, stockexchangecode);
		
		return result;
	}
	
	/***
	 * ��sina���ù�Ʊ���
	 * @param destUrl
	 * @return
	 * @throws IOException
	 */
	private static String getContent(String destUrl) throws IOException {
		
		  HttpURLConnection httpUrl = null;
		  URL url = null;
		  StringBuffer resultString = new StringBuffer();
		  byte[] buf = new byte[BUFFER_SIZE];
		  int size = 0;
		
		  url = new URL(destUrl);
		  httpUrl = (HttpURLConnection) url.openConnection();
		  httpUrl.connect();
		  
		  try
		  {
			  BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
			  BufferedReader reader = new BufferedReader (new InputStreamReader(bis));
			  
			  try
			  {
				  
				  String valueString = null;
				  resultString = new StringBuffer();
			        while (reader.ready()) {
			        	while ((valueString=reader.readLine())!=null){
			        		resultString.append(valueString + "\r\n");
			        	}
			        	
			       }

			  }
			  
			  finally
			  {
				  reader.close();
			  }
		  }
		  catch(FileNotFoundException ex)
		  {
			  return "";
		  }
		  finally
		  {
			  httpUrl.disconnect();
		  }
		  
		  return resultString.toString();
	}
	
	private static List<YahooDailyStockDataDo> CreateYahooDailyStockDataDo(String content,String stockcode,String stockname, String stockexchangecode) throws IOException
	{
		//var hq_str_sh000001="��ָ֤��,2051.582,2054.948,2051.713,2057.105,2045.962,0,0,81711186,69306421819,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2014-06-12,15:03:03,00";
		List<YahooDailyStockDataDo> result = new ArrayList<YahooDailyStockDataDo>();
		
		YahooDailyStockDataDo item = null;
		
		if (content == null || content.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			//Do Nothing
		}
		
		BufferedReader br = new BufferedReader(new StringReader(content));
		
		String line = ""; 
		while ((line = br.readLine()) != null) 
		{ 
			if (line == null || line.equalsIgnoreCase(""))
			{
				return null;
			}
			else
			{
				//Do Nothing
			}
			
			String[] dataList = line.split(",");
			if(dataList.length < 6) continue;
			if(dataList[0].equalsIgnoreCase("DATE")) continue;
			
			item = new YahooDailyStockDataDo();
			
			item.setStockname(stockname); //股票名字
			item.setStockcode(stockcode);
			item.setStockexchangecode(stockexchangecode);
			item.setDate(dataList[0]); //日期
			item.setOpen(dataList[1]); //开盘价
			item.setHigh(dataList[2]); //最高价
			item.setLow(dataList[3]); //最低价
			item.setClose(dataList[4]); //收盘价
			item.setVolumn(dataList[5]); //交易量
			item.setAdjclose(dataList[6]); //调整收盘价
			
			result.add(item);
			
		} 
		br.close();

		
		return result;
	}

	public static List<String> getStockDataDayList(String stockcode,  String stockexchangecode, Connection connection) throws SQLException
	{
		List<String> result = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
        sql.append("select distinct t.date from StockDataYahoo t where 1=1 ");

        if(stockcode == null || stockcode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.stockcode = '" + stockcode + "'");
        }
        
        
        if(stockexchangecode == null || stockexchangecode.equalsIgnoreCase(""))
        {
        	//Do Nothing
        }
        else
        {
        	sql.append(" and t.stockexchangecode = '" + stockexchangecode + "'");
        }
        
        

        Statement sSta = null;
  	
        ResultSet resultSet =null;
        
        try
	    {
	        sSta = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        
	      
	         resultSet = sSta.executeQuery(sql.toString());
	         
	        while (resultSet.next())
	        {
	        	result.add(resultSet.getString("date"));
	        }
	    }finally
	    {
	    	try
	        {
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
