package com.xkumo.xstock.core.data;

/**
 * 基础数据 - 股票信息
 * @author xvting
 *
 */
public class StockInfo {
	
	public StockInfo(String pStockCode, String pStockExchangeCode)
	{
		StockCode = pStockCode;
		StockExchangeCode = pStockExchangeCode;
	}
	
	/**
	 * 股票名称
	 */
	private String StockName;
	
	/**
	 * 股票代码
	 */
	private String StockCode;
	/**
	 * 所属交易所代码
	 */
	private String StockExchangeCode;
	
	
	public String getStockCode() {
		return StockCode;
	}
	public void setStockCode(String stockCode) {
		StockCode = stockCode;
	}
	public String getStockExchangeCode() {
		return StockExchangeCode;
	}
	public void setStockExchangeCode(String stockExchangeCode) {
		StockExchangeCode = stockExchangeCode;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return StockName + StockExchangeCode;
	}
}
