package com.xkumo.vendor.sina;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.xkumo.util.DateFactory;
import com.xkumo.vendor.IVendor;


public class SinaVendor extends IVendor {
	
	private int BUFFER_SIZE = 8096;
	
	/***
	 * ������˵ĵ�ǰ���
	 * @param stockcode ��Ʊ����
	 * @param stockexchangecode ��Ʊ���
	 * @return
	 * @throws IOException 
	 */
	public SinaDailyStockData getDailyStockData(String stockcode, String stockexchangecode) throws IOException
	{
		String url = "http://hq.sinajs.cn/list=" + stockexchangecode + stockcode ;
		String content = getContent(url);
		
		SinaDailyStockData result = CreateSinaDailyStockDataDo(content,stockcode,stockexchangecode);
		
		return result;
	}
	
	/***
	 * ������˵ĵ�ǰ���
	 * @param stockcode ��Ʊ����
	 * @param stockexchangecode ��Ʊ���
	 * @return
	 * @throws IOException 
	 */
	public SinaAG1512Data getDailyAGData(String agCode) throws IOException
	{
		String url = "http://hq.sinajs.cn/list=" + agCode ;
		String content = getContent(url);
		
		SinaAG1512Data result = CreateSinaAgDataDo(content,agCode);
		
		return result;
	}
	
	/***
	 * ��sina���ù�Ʊ���
	 * @param destUrl
	 * @return
	 * @throws IOException
	 */
	private String getContent(String destUrl) throws IOException {
		
		  HttpURLConnection httpUrl = null;
		  URL url = null;
		  StringBuffer resultString;
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
				  
				  resultString = new StringBuffer();
			        while (reader.ready()) {
			        	resultString.append((char)reader.read());
			       }

			  }
			  finally
			  {
				  reader.close();
			  }
		  }
		  finally
		  {
			  httpUrl.disconnect();
		  }
		  
		  return resultString.toString();
	}
	
	private SinaDailyStockData CreateSinaDailyStockDataDo(String content,String stockcode, String stockexchangecode)
	{
		//var hq_str_sh000001="��ָ֤��,2051.582,2054.948,2051.713,2057.105,2045.962,0,0,81711186,69306421819,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2014-06-12,15:03:03,00";
		SinaDailyStockData result = new SinaDailyStockData();
		
		if (content == null || content.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			//Do Nothing
		}
		
		int startIndex = content.indexOf("\"");
		int lastIndex = content.lastIndexOf("\"");
		content = content.substring(startIndex + 1, lastIndex);
		
		if (content == null || content.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			//Do Nothing
		}
		result.setContent(content);
		
		
		String[] dataList = content.split(",");
		if(dataList.length < 30) return null;
		
		//result.setStockname("这是中文的测试" + count++);
		result.setStockcode(stockcode);
		result.setStockexchangecode(stockexchangecode);
		result.setStockname(dataList[0]); //股票名字
		result.setOpenprice(dataList[1]);	//今日开盘价
		result.setLastcloseprice(dataList[2]); //昨日收盘价
		result.setCurrentprice(dataList[3]); //当前价格
		result.setTopprice(dataList[4]); //今日最高价
		result.setBottomprice(dataList[5]); //今日最低价
		result.setBuyprice(dataList[6]); //竞买价
		result.setSellprice(dataList[7]); //竞卖价
		result.setTransactionnumber(dataList[8]); //成交的股票数
		result.setTransactionamount(dataList[9]); //成交金额
		
		result.setBuy1number(dataList[10]); //买一
		result.setBuy1price(dataList[11]); //“买一”报价
		result.setBuy2number(dataList[12]); //买二
		result.setBuy2price(dataList[13]); //“买二”报价
		result.setBuy3number(dataList[14]); //买三
		result.setBuy3price(dataList[15]); //“买三”报价
		result.setBuy4number(dataList[16]); //买四
		result.setBuy4price(dataList[17]); //“买四”报价
		result.setBuy5number(dataList[18]); //买五
		result.setBuy5price(dataList[19]); //“买五”报价
		
		result.setSell1number(dataList[20]); //卖一
		result.setSell1price(dataList[21]); //“卖一”报价
		result.setSell2number(dataList[22]); //卖二
		result.setSell2price(dataList[23]); //“卖二”报价
		result.setSell3number(dataList[24]); //卖三
		result.setSell3price(dataList[25]); //“卖三”报价
		result.setSell4number(dataList[26]); //卖四
		result.setSell4price(dataList[27]); //“卖四”报价
		result.setSell5number(dataList[28]); //卖五
		result.setSell5price(dataList[29]); //“卖五”报价
		
		Date dayDate = null;
		
		try
		{
			dayDate = DateFactory.ConvertToData(dataList[30]);
		}
		catch(Exception ex)
		{
			dayDate = null;
		}
		
		result.setDataday(dayDate); //日期
		result.setDatatime(dataList[31]); //时间
		
		return result;
	}
	
	private SinaAG1512Data CreateSinaAgDataDo(String content,String agcode)
	{
		//var hq_str_sh000001="��ָ֤��,2051.582,2054.948,2051.713,2057.105,2045.962,0,0,81711186,69306421819,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2014-06-12,15:03:03,00";
		SinaAG1512Data result = new SinaAG1512Data();
		
		if (content == null || content.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			//Do Nothing
		}
		
		int startIndex = content.indexOf("\"");
		int lastIndex = content.lastIndexOf("\"");
		content = content.substring(startIndex + 1, lastIndex);
		
		if (content == null || content.equalsIgnoreCase(""))
		{
			return null;
		}
		else
		{
			//Do Nothing
		}
		result.setContent(content);
		
		
		String[] dataList = content.split(",");
		if(dataList.length < 15) return null;
		
		//result.setStockname("这是中文的测试" + count++);
		//期货合约名
		result.setName(dataList[0]);
		//数据时间
		result.setDatatime(dataList[1]);
		//开盘价
		result.setOpenprice(dataList[2]);
		//今日最高价
		result.setTopprice(dataList[3]);
		//今日最低价
		result.setBottomprice(dataList[4]);
		//昨算价
		result.setLastchangeprice(dataList[5]);
		//竞买价
		result.setBuyprice(dataList[6]);
		//竞卖价
		result.setSellprice(dataList[7]);
		//当前价格
		result.setCurrentprice(dataList[8]);
		//今算价
		result.setChangeprice(dataList[9]);
		//[10]
		//买量
		result.setBuynumber(dataList[11]);
		//卖量
		result.setSellnumber(dataList[12]);
		//持仓量
		result.setHoldamount(dataList[13]);
		//成交量
		result.setChangevolumn(dataList[14]);
		//域
		result.setMarket(dataList[15]);
		//商品
		result.setProduct(dataList[16]);
		//日期  
		Date dayDate = null;
		
		try
		{
			dayDate = DateFactory.ConvertToData(dataList[17]);
		}
		catch(Exception ex)
		{
			dayDate = null;
		}
		result.setDataday(dayDate); //日期
		
		
		return result;
	}
}
