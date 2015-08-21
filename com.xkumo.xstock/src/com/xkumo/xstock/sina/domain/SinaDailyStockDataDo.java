package com.xkumo.xstock.sina.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.xkumo.vendor.sina.SinaDailyStockData;

/**
*
* @author xvting
*/
public class SinaDailyStockDataDo {
private SinaDailyStockData m_sinaDailyStockData;

	private int sinaseq;

	public SinaDailyStockDataDo(
			SinaDailyStockData sinadailystockdata)
	{
		sinaseq = 0;
		m_sinaDailyStockData = sinadailystockdata;
	}

	public SinaDailyStockDataDo()
	{
		sinaseq = 0;
		m_sinaDailyStockData = new SinaDailyStockData();
	}

	public int getSinaseq() {
		return sinaseq;
	}
	public void setSinaseq(int sinaseq) {
		this.sinaseq = sinaseq;
	}
	public String getStockname() {
		return m_sinaDailyStockData.getStockname();
	}
	public void setStockname(String stockname) {
		m_sinaDailyStockData.setStockname(stockname);
	}
	public String getStockcode() {
		return m_sinaDailyStockData.getStockcode();
	}
	public void setStockcode(String stockcode) {
		m_sinaDailyStockData.setStockcode(stockcode);
	}
	public String getStockexchangecode() {
		return m_sinaDailyStockData.getStockexchangecode();
	}
	public void setStockexchangecode(String stockexchangecode) {
		m_sinaDailyStockData.setStockexchangecode(stockexchangecode);
	}
	public String getOpenprice() {
		return m_sinaDailyStockData.getOpenprice();
	}
   public float getOpenpricefloat()
	{
		String value = getOpenprice();

		if (value == null)
		{
			return 0.0f;
		}
		else
		{
			try
			{
				return Float.parseFloat(value);
			}
			catch(Exception ex)
			{
				return 0.0f;
			}
		}
	}
   
	public void setOpenprice(String openprice) {
		m_sinaDailyStockData.setOpenprice(openprice);
	}
   


	public String getLastcloseprice() {
		return m_sinaDailyStockData.getLastcloseprice();
	}

   public float getLastclosepricefloat()
	{
		String value = getLastcloseprice();

		if (value == null)
		{
			return 0.0f;
		}
		else
		{
			try
			{
				return Float.parseFloat(value);
			}
			catch(Exception ex)
			{
				return 0.0f;
			}
		}
	}

	public void setLastcloseprice(String lastcloseprice) {
		m_sinaDailyStockData.setLastcloseprice(lastcloseprice);
	}
	public String getCurrentprice() {
		return m_sinaDailyStockData.getCurrentprice();
	}

	public float Currentpricefloat()
	{
		String value = getCurrentprice();

		if (value == null)
		{
			return 0.0f;
		}
		else
		{
			try
			{
				return Float.parseFloat(value);
			}
			catch(Exception ex)
			{
				return 0.0f;
			}
		}
	}

	public void setCurrentprice(String currentprice) {
		m_sinaDailyStockData.setCurrentprice(currentprice);
	}
	public String getTopprice() {
		return m_sinaDailyStockData.getTopprice();
	}
	public void setTopprice(String topprice) {
		m_sinaDailyStockData.setTopprice(topprice);
	}
	public String getBottomprice() {
		return m_sinaDailyStockData.getBottomprice();
	}
	public void setBottomprice(String bottomprice) {
		m_sinaDailyStockData.setBottomprice(bottomprice);
	}
	public String getBuyprice() {
		return m_sinaDailyStockData.getBuyprice();
	}
	public void setBuyprice(String buyprice) {
		m_sinaDailyStockData.setBuyprice(buyprice);
	}
	public String getSellprice() {
		return m_sinaDailyStockData.getSellprice();
	}
	public void setSellprice(String sellprice) {
		m_sinaDailyStockData.setSellprice(sellprice);
	}
	public String getTransactionamount() {
		return m_sinaDailyStockData.getTransactionamount();
	}
	public void setTransactionamount(String transactionamount) {
		m_sinaDailyStockData.setTransactionamount(transactionamount);
	}
	public String getTransactionnumber() {
		return m_sinaDailyStockData.getTransactionnumber();
	}
	public void setTransactionnumber(String transactionnumber) {
		m_sinaDailyStockData.setTransactionnumber(transactionnumber);
	}
	public String getBuy1price() {
		return m_sinaDailyStockData.getBuy1price();
	}
	public void setBuy1price(String buy1price) {
		m_sinaDailyStockData.setBuy1price(buy1price);
	}
	public String getBuy1number() {
		return m_sinaDailyStockData.getBuy1number();
	}

   public int getBuy1numberInt()
	{
		String value = getBuy1number();

		if (value == null)
		{
			return 0;
		}
		else
		{
			try
			{
				return Integer.parseInt(value);
			}
			catch(Exception ex)
			{
				return 0;
			}
		}
	}

	public void setBuy1number(String buy1number) {
		m_sinaDailyStockData.setBuy1number(buy1number);
	}
	public String getBuy2price() {
		return m_sinaDailyStockData.getBuy2price();
	}
	public void setBuy2price(String buy2price) {
		m_sinaDailyStockData.setBuy2price(buy2price);
	}
	public String getBuy2number() {
		return m_sinaDailyStockData.getBuy2number();
	}
	public void setBuy2number(String buy2number) {
		m_sinaDailyStockData.setBuy2number(buy2number);
	}
	public String getBuy3price() {
		return m_sinaDailyStockData.getBuy3price();
	}
	public void setBuy3price(String buy3price) {
		m_sinaDailyStockData.setBuy3price(buy3price);
	}
	public String getBuy3number() {
		return m_sinaDailyStockData.getBuy3number();
	}
	public void setBuy3number(String buy3number) {
		m_sinaDailyStockData.setBuy3number(buy3number);
	}
	public String getBuy4price() {
		return m_sinaDailyStockData.getBuy4price();
	}
	public void setBuy4price(String buy4price) {
		m_sinaDailyStockData.setBuy4price(buy4price);
	}
	public String getBuy4number() {
		return m_sinaDailyStockData.getBuy4number();
	}
	public void setBuy4number(String buy4number) {
		m_sinaDailyStockData.setBuy4number(buy4number);
	}
	public String getBuy5price() {
		return m_sinaDailyStockData.getBuy5price();
	}
	public void setBuy5price(String buy5price) {
		m_sinaDailyStockData.setBuy5price(buy5price);
	}
	public String getBuy5number() {
		return m_sinaDailyStockData.getBuy5number();
	}
	public void setBuy5number(String buy5number) {
		m_sinaDailyStockData.setBuy5number(buy5number);
	}
	public String getSell1price() {
		return m_sinaDailyStockData.getSell1price();
	}
	public void setSell1price(String sell1price) {
		m_sinaDailyStockData.setSell1price(sell1price);
	}
	public String getSell1number() {
		return m_sinaDailyStockData.getSell1number();
	}
	public void setSell1number(String sell1number) {
		m_sinaDailyStockData.setSell1number(sell1number);
	}
	public String getSell2price() {
		return m_sinaDailyStockData.getSell2price();
	}
	public void setSell2price(String sell2price) {
		m_sinaDailyStockData.setSell2price(sell2price);
	}
	public String getSell2number() {
		return m_sinaDailyStockData.getSell2number();
	}
	public void setSell2number(String sell2number) {
		m_sinaDailyStockData.setSell2number(sell2number);
	}
	public String getSell3price() {
		return m_sinaDailyStockData.getSell3price();
	}
	public void setSell3price(String sell3price) {
		m_sinaDailyStockData.setSell3price(sell3price);
	}
	public String getSell3number() {
		return m_sinaDailyStockData.getSell3number();
	}
	public void setSell3number(String sell3number) {
		m_sinaDailyStockData.setSell3number(sell3number);
	}
	public String getSell4price() {
		return m_sinaDailyStockData.getSell4price();
	}
	public void setSell4price(String sell4price) {
		m_sinaDailyStockData.setSell4price(sell4price);
	}
	public String getSell4number() {
		return m_sinaDailyStockData.getSell4number();
	}
	public void setSell4number(String sell4number) {
		m_sinaDailyStockData.setSell4number(sell4number);
	}
	public String getSell5price() {
		return m_sinaDailyStockData.getSell5price();
	}
	public void setSell5price(String sell5price) {
		m_sinaDailyStockData.setSell5price(sell5price);
	}
	public String getSell5number() {
		return m_sinaDailyStockData.getSell5number();
	}
	public void setSell5number(String sell5number) {
		m_sinaDailyStockData.setSell5number(sell5number);
	}
	public Date getDataday() {
		return m_sinaDailyStockData.getDataday();
	}
	public void setDataday(Date dataday) {
		m_sinaDailyStockData.setDataday(dataday);
	}
	public String getDatatime() {
		return m_sinaDailyStockData.getDatatime();
	}
	public void setDatatime(String datatime) {
		m_sinaDailyStockData.setDatatime(datatime);
	}

	public float getMa5() {
		return m_sinaDailyStockData.getMa5();
	}
	public void setMa5(float ma) {
		m_sinaDailyStockData.setMa5(ma);
	}

	public float getMa10() {
		return m_sinaDailyStockData.getMa10();
	}
	public void setMa10(float ma) {
		m_sinaDailyStockData.setMa10(ma);
	}

	public float getMa20() {
		return m_sinaDailyStockData.getMa20();
	}
	public void setMa20(float ma) {
		m_sinaDailyStockData.setMa20(ma);
	}


	public float getMa30() {
		return m_sinaDailyStockData.getMa30();
	}
	public void setMa30(float ma) {
		m_sinaDailyStockData.setMa30(ma);
	}

	public float getMa60() {
		return m_sinaDailyStockData.getMa60();
	}
	public void setMa60(float ma) {
		m_sinaDailyStockData.setMa60(ma);
	}

	public float getMa120() {
		return m_sinaDailyStockData.getMa120();
	}
	public void setMa120(float ma) {
		m_sinaDailyStockData.setMa120(ma);
	}

	public float getMa240() {
		return m_sinaDailyStockData.getMa240();
	}
	public void setMa240(float ma) {
		m_sinaDailyStockData.setMa240(ma);
	}

	public boolean isDown()
	{
		float openPrice = Float.parseFloat(this.getOpenprice());
		float currentPrice = Float.parseFloat(this.getCurrentprice());

		if(openPrice < currentPrice)
		{
			return false;
		}
		else
		{
			return true;
		}

	}

	public static SinaDailyStockDataDo create(ResultSet resultSet) throws SQLException
	{
		SinaDailyStockDataDo result = null;

		if(resultSet == null)
		{
			return result;
		}
		else
		{
			//Do Nothing
		}

		result = new SinaDailyStockDataDo();
		result.setSinaseq(resultSet.getInt("SINASEQ"));
		result.setStockname(resultSet.getString("STOCKNAME"));
		result.setStockcode(resultSet.getString("STOCKCODE"));
		result.setStockexchangecode(resultSet.getString("STOCKEXCHANGECODE"));
		result.setOpenprice(resultSet.getString("OPENPRICE"));
		result.setLastcloseprice(resultSet.getString("LASTCLOSEPRICE"));
		result.setCurrentprice(resultSet.getString("CURRENTPRICE"));
		result.setTopprice(resultSet.getString("TOPPRICE"));
		result.setBottomprice(resultSet.getString("BOTTOMPRICE"));
		result.setBuyprice(resultSet.getString("BUYPRICE"));
   	result.setSellprice(resultSet.getString("SELLPRICE"));
   	result.setTransactionamount(resultSet.getString("TRANSACTIONAMOUNT"));
   	result.setTransactionnumber(resultSet.getString("TRANSACTIONNUMBER"));
   	result.setBuy1price(resultSet.getString("BUY1PRICE"));
   	result.setBuy1number(resultSet.getString("BUY1NUMBER"));
   	result.setBuy2price(resultSet.getString("BUY2PRICE"));
   	result.setBuy2number(resultSet.getString("BUY2NUMBER"));
   	result.setBuy3price(resultSet.getString("BUY3PRICE"));
   	result.setBuy3number(resultSet.getString("BUY3NUMBER"));
   	result.setBuy4price(resultSet.getString("BUY4PRICE"));
   	result.setBuy4number(resultSet.getString("BUY4NUMBER"));
   	result.setBuy5price(resultSet.getString("BUY5PRICE"));
   	result.setBuy5number(resultSet.getString("BUY5NUMBER"));
   	result.setSell1price(resultSet.getString("SELL1PRICE"));
   	result.setSell1number(resultSet.getString("SELL1NUMBER"));
   	result.setSell2price(resultSet.getString("SELL2PRICE"));
   	result.setSell2number(resultSet.getString("SELL2NUMBER"));
   	result.setSell3price(resultSet.getString("SELL3PRICE"));
   	result.setSell3number(resultSet.getString("SELL3NUMBER"));
   	result.setSell4price(resultSet.getString("SELL4PRICE"));
   	result.setSell4number(resultSet.getString("SELL4NUMBER"));
   	result.setSell5price(resultSet.getString("SELL5PRICE"));
   	result.setSell5number(resultSet.getString("SELL5NUMBER"));
   	result.setDataday(resultSet.getDate("DATADAY"));
   	result.setDatatime(resultSet.getString("DATATIME"));
   	result.setMa5(resultSet.getFloat("ma5"));
   	result.setMa10(resultSet.getFloat("ma10"));
   	result.setMa20(resultSet.getFloat("ma20"));
   	result.setMa30(resultSet.getFloat("ma30"));
   	result.setMa60(resultSet.getFloat("ma60"));
   	result.setMa120(resultSet.getFloat("ma120"));
   	result.setMa240(resultSet.getFloat("ma240"));

       return result;
	}
}
